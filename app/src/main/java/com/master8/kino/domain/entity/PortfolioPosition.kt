package com.master8.kino.domain.entity

data class PortfolioPosition(
    val instrument: Instrument,
    val averagePositionPrice: Double,
    val averagePositionPriceNow: Double,
    val lots: Int
) {
    val expectedYield: Double
        get() {
            return averagePositionPriceNow * lots - averagePositionPrice * lots
        }

    val expectedYieldInPercent: Double
        get() {
            val total = averagePositionPrice * lots
            return expectedYield / total
        }
}