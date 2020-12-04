package com.master8.kino.domain.entity

data class PortfolioPosition(
    val instrument: Instrument,
    val startAveragePositionPrice: Usd,
    val endAveragePositionPrice: Usd,
    val lots: Int
) {
    val expectedYield: Usd
        get() {
            return endAveragePositionPrice * lots - startAveragePositionPrice * lots
        }

    val expectedYieldInPercent: Double
        get() {
            val total = startAveragePositionPrice * lots
            return (expectedYield / total).value
        }
}