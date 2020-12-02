package com.master8.kino.data.repository

import com.master8.kino.data.source.tinkoff.InvestApiService
import com.master8.kino.data.source.tinkoff.dto.OperationDto
import com.master8.kino.domain.PortfolioRepository
import com.master8.kino.domain.entity.Instrument
import com.master8.kino.domain.entity.PortfolioPosition
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*

class PortfolioRepositoryImpl(
    private val api: InvestApiService
): PortfolioRepository {

    private val calendar = Calendar.getInstance()

    private val dateFormatter by lazy { SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSSXXX") }

    override suspend fun getPortfolioPositions(vararg instruments: Instrument): List<PortfolioPosition> {
        return instruments.map { getPosition(it) }
    }

    private suspend fun getPosition(instrument: Instrument): PortfolioPosition {
        val operations = getBuyOperations(instrument)
        val totalPrice = operations.sumByDouble { it.price * it.quantityExecuted }
        val lots = operations.sumBy { it.quantityExecuted }

        val priceNow = getPriceForDateTime(
            instrument,
            TARGET_DATE
        )

        val usd = getPriceForDateTime(
            Instrument.USD,
            TARGET_DATE
        )

        val nowPriceInUsd = priceNow / usd

        return PortfolioPosition(
            instrument,
            totalPrice/lots,
            nowPriceInUsd,
            lots
        )
    }

    private suspend fun getBuyOperations(instrument: Instrument): List<OperationDto> = withContext(
        Dispatchers.IO) {
        return@withContext api.getOperations(
            instrument.figi
        ).payload.operations
            .filter { it.operationType == "Buy" || it.operationType == "BuyCard" }
            .map {
                it.copy(
                    price = it.price / getPriceForDateTime(Instrument.USD, it.date)
                )
            }
    }

    private suspend fun getPriceForDateTime(instrument: Instrument, fromDateTime: String): Double = withContext(
        Dispatchers.IO) {
        calendar.time = dateFormatter.parse(fromDateTime)!!
        calendar.add(Calendar.MINUTE, 1)

        val toDateTime = dateFormatter.format(calendar.time)

        val usd = api.getCandles(
            instrument.figi,
            fromDateTime,
            toDateTime,
            "1min"
        )

        return@withContext usd.payload.candles.first().c
    }

    companion object {
        const val TARGET_DATE = "2020-11-13T18:29:00.0+03:00"
    }
}