package com.master8.kino.domain.entity

data class PortfolioPosition(
    val instrument: Instrument,
    val startAveragePositionPrice: Double,
    val endAveragePositionPrice: Double,
    val lots: Int
) {
    val expectedYield: Double
        get() {
            return endAveragePositionPrice * lots - startAveragePositionPrice * lots
        }

    val expectedYieldInPercent: Double
        get() {
            val total = startAveragePositionPrice * lots
            return expectedYield / total
        }
}