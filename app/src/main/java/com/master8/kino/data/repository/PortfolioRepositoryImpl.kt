package com.master8.kino.data.repository

import android.util.Log
import com.master8.kino.data.source.db.dao.BuyOperationDao
import com.master8.kino.data.source.db.dao.UsdToRubDao
import com.master8.kino.data.source.db.entities.BuyOperationDbEntity
import com.master8.kino.data.source.db.entities.UsdToRubDbEntity
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
    private val usdToRubDao: UsdToRubDao
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

        val usdAt = usdToRubDao.getUsdPriceAt(date.toString())
            ?.price
            ?: run {
                Log.e("mv8", "ERROR USD $date")
                getPriceAt(date, Instrument.USD)
                    .value
                    .also {
                        usdToRubDao.insert(
                            UsdToRubDbEntity(
                                it,
                                date.toString()
                            )
                        )
                    }
            }

        return Usd(value.value / usdAt)
    }
}

private val Usd.name get() = "usd"
private val Rub.name get() = "rub"

private val Currency.name get() = when (this) {
    is Usd -> this.name
    is Rub -> this.name
}
