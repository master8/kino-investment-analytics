package com.master8.kino.data.repository

import com.master8.kino.data.source.db.dao.BuyOperationDao
import com.master8.kino.data.source.db.dao.InstrumentPriceDao
import com.master8.kino.data.source.db.entities.BuyOperationDbEntity
import com.master8.kino.data.source.db.entities.InstrumentPriceEntity
import com.master8.kino.data.source.tinkoff.InvestApiService
import com.master8.kino.domain.PortfolioRepository
import com.master8.kino.domain.entity.*
import com.master8.kino.domain.entity.Currency
import com.master8.kino.domain.entity.Date
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PortfolioRepositoryImpl(
    private val api: InvestApiService,
    private val buyOperationDao: BuyOperationDao,
    private val instrumentPriceDao: InstrumentPriceDao
): PortfolioRepository {

    override suspend fun getAllBuyOperations(
        instrument: Instrument
    ): List<BuyOperation> = withContext(Dispatchers.IO) {

        return@withContext buyOperationDao.getAll(instrument.figi)
            .map {
                BuyOperation(
                    instrument.figi,
                    when (it.currencyName) {
                        "usd" -> Usd(it.price)
                        "rub" -> Rub(it.price)
                        else -> throw RuntimeException("Unsupported currency!")
                    },
                    Date(it.date),
                    it.quantityExecuted
                )
            }

//        return@withContext api.getOperations(instrument.figi)
//            .payload
//            .operations
//            .filter { it.operationType == "Buy" || it.operationType == "BuyCard" }
//            .map {
//                BuyOperation(
//                    instrument.figi,
//                    when (instrument.currency) {
//                        Instrument.Currency.USD -> Usd(it.price)
//                        Instrument.Currency.RUB -> Rub(it.price)
//                    },
//                    Date(it.date),
//                    it.quantityExecuted
//                )
//            }
//            .also {  operations ->
//                buyOperationDao.insert(operations.map {
//                    BuyOperationDbEntity(
//                        it.figi,
//                        it.price.value,
//                        it.price.name,
//                        it.date.toString(),
//                        it.quantityExecuted
//                    )
//                })
//            }
    }

    override suspend fun getPriceAt(
        date: Date, instrument: Instrument
    ): Currency = withContext(Dispatchers.IO) {

        getPriceFromDbAt(date, instrument)
            ?: getPriceFromApiAt(date, instrument)
    }

    private suspend fun getPriceFromDbAt(
        date: Date, instrument: Instrument
    ): Currency? = withContext(Dispatchers.IO) {

        instrumentPriceDao.getPriceAt(date.toString(), instrument.figi)
            ?.price
            ?.convertTo(instrument.currency)
    }

    private suspend fun getPriceFromApiAt(
        date: Date, instrument: Instrument
    ): Currency = withContext(Dispatchers.IO) {

        api.getCandles(
            instrument.figi,
            date.toString(),
            date.nextMinute.toString(),
            "1min"
        ).payload
            .candles
            .first()
            .c
            .convertTo(instrument.currency)
            .also {
                instrumentPriceDao.insert(
                    InstrumentPriceEntity(
                        instrument.figi,
                        it.value,
                        it.name,
                        date.toString()
                    )
                )
            }
    }

    override suspend fun getLastAvailableDate(): Date {
        return Date("2020-12-04T18:29:00.0+03:00")//TODO
    }

    override suspend fun convertToUsdAt(date: Date, value: Rub): Usd {
        return Usd(value.value / getPriceAt(date, Instrument.USD).value)
    }
}

private val Currency.name get() = when (this) {
    is Usd -> "usd"
    is Rub -> "rub"
}

private fun Double.convertTo(currency: Instrument.Currency) = when (currency) {
    Instrument.Currency.USD -> Usd(this)
    Instrument.Currency.RUB -> Rub(this)
}