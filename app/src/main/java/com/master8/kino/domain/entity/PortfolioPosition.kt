package com.master8.kino.domain.entity

data class PortfolioPosition(
    val instrument: Instrument,
    val averagePositionPrice: Double,
    val nowAveragePositionPrice: Double,
    val lots: Int
) {
    val expectedYield: Double
        get() {
            return nowAveragePositionPrice * lots - averagePositionPrice * lots
        }

    val expectedYieldInPercent: Double
        get() {
            val total = averagePositionPrice * lots
            return expectedYield / total * 100
        }
}