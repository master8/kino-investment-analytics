package com.master8.kino.data.repository

import com.master8.kino.data.source.db.dao.BuyOperationDao
import com.master8.kino.data.source.db.dao.InstrumentPriceDao
import com.master8.kino.data.source.db.dao.LastUpdatedDateDao
import com.master8.kino.data.source.db.entities.BuyOperationDbEntity
import com.master8.kino.data.source.db.entities.InstrumentPriceEntity
import com.master8.kino.data.source.db.entities.LastUpdatedDateEntity
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
    private val instrumentPriceDao: InstrumentPriceDao,
    private val lastUpdatedDateDao: LastUpdatedDateDao
): PortfolioRepository {

    override suspend fun getBuyOperations(
        instrument: Instrument,
        from: Date,
        to: Date
    ): List<BuyOperation> = withContext(Dispatchers.IO) {

        val lastUpdatedDate = lastUpdatedDateDao.get(instrument.figi)

        return@withContext when {
            lastUpdatedDate == null -> {
                getBuyOperationsFromApi(
                    instrument,
                    LastUpdatedDateEntity(
                        instrument.figi,
                        from.toString()
                    ),
                    to
                )
            }
            Date(lastUpdatedDate.date) >= to -> {
                getBuyOperationsFromDb(instrument, from, to)
            }
            else -> {
                getBuyOperationsFromDb(
                    instrument, from, to
                ) + getBuyOperationsFromApi(
                    instrument,
                    lastUpdatedDate,
                    to
                )
            }
        }
    }

    private suspend fun getBuyOperationsFromDb(
        instrument: Instrument,
        from: Date,
        to: Date
    ): List<BuyOperation> = withContext(Dispatchers.IO) {

        buyOperationDao.getAll(instrument.figi)
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
            .filter { it.date >= from && it.date <= to }
    }

    private suspend fun getBuyOperationsFromApi(
        instrument: Instrument,
        lastUpdatedDate: LastUpdatedDateEntity,
        to: Date
    ): List<BuyOperation> = withContext(Dispatchers.IO) {
        try {
            api.getOperations(
                instrument.figi,
                lastUpdatedDate.date,
                to.toString()
            )
        } catch (e: Exception) {
            return@withContext listOf()
        }.payload
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
            .also {  operations ->
                lastUpdatedDateDao.insert(
                    lastUpdatedDate.copy(date = to.toString())
                )
                buyOperationDao.insert(operations.map {
                    BuyOperationDbEntity(
                        it.figi,
                        it.price.value,
                        it.price.name,
                        it.date.toString(),
                        it.quantityExecuted
                    )
                })
            }
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
        val now = Date.now
        val targetTime = START_DATE.copyDayFrom(now)

        return when {
            targetTime.isSaturday -> targetTime.minus(1)
            targetTime.isSunday -> targetTime.minus(2)
            now >= targetTime -> targetTime
            targetTime.isMonday -> targetTime.minus(3)
            else -> targetTime.minus(1)
        }
    }

    override suspend fun getStartDate(): Date {
        return START_DATE
    }

    override suspend fun convertToUsdAt(date: Date, value: Rub): Usd {
        return Usd(value.value / getPriceAt(date, Instrument.USD).value)
    }

    private companion object {
        val START_DATE = Date("2020-01-01T18:29:00.0+03:00")
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