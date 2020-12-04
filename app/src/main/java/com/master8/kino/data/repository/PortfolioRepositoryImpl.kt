package com.master8.kino.data.repository

import com.master8.kino.data.source.tinkoff.InvestApiService
import com.master8.kino.domain.PortfolioRepository
import com.master8.kino.domain.entity.*
import com.master8.kino.domain.entity.Currency
import com.master8.kino.domain.entity.Date
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PortfolioRepositoryImpl(
    private val api: InvestApiService
): PortfolioRepository {

    override suspend fun getAllBuyOperations(
        instrument: Instrument
    ): List<BuyOperation> = withContext(Dispatchers.IO) {

        return@withContext api.getOperations(instrument.figi)
            .payload
            .operations
            .filter { it.operationType == "Buy" || it.operationType == "BuyCard" }
            .map {
                BuyOperation(
                    instrument.figi,
                    when (instrument.currency) {
                        Instrument.Currency.USD -> Usd(it.price)
                        Instrument.Currency.RUB -> Rub(it.price)
                    },
                    Date(it.date),
                    it.quantityExecuted
                )
            }
    }

    override suspend fun getPriceAt(
        date: Date, instrument: Instrument
    ): Currency = withContext(Dispatchers.IO) {

        return@withContext api.getCandles(
            instrument.figi,
            date.toString(),
            date.nextMinute.toString(),
            "1min"
        ).payload
            .candles
            .first()
            .c
            .let {
                when (instrument.currency) {
                    Instrument.Currency.USD -> Usd(it)
                    Instrument.Currency.RUB -> Rub(it)
                }
            }
    }

    override suspend fun getLastAvailableDate(): Date {
        return Date("2020-12-04T18:29:00.0+03:00")//TODO
    }

    override suspend fun convertToUsdAt(date: Date, value: Rub): Usd {
        return Usd(value.value / getPriceAt(date, Instrument.USD).value)
    }
}