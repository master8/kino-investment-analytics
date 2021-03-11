package com.master8.kino.domain

import com.master8.kino.domain.entity.*

interface PortfolioRepository {

    suspend fun getBuyOperations(
        instrument: Instrument,
        from: Date,
        to: Date
    ): List<BuyOperation>

    suspend fun getPriceAt(date: Date, instrument: Instrument): Currency
    suspend fun convertToUsdAt(date: Date, value: Rub): Usd

    suspend fun getStartDate(instrument: Instrument): Date
    suspend fun getLastAvailableDate(): Date
}