package com.master8.kino.domain

import com.master8.kino.domain.entity.*

interface PortfolioRepository {

    suspend fun getAllBuyOperations(instrument: Instrument): List<BuyOperation>
    suspend fun getPriceAt(date: Date, instrument: Instrument): Currency
    suspend fun getLastAvailableDate(): Date
    suspend fun convertToUsdAt(date: Date, value: Rub): Usd
}