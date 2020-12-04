package com.master8.kino.domain.entity

data class PortfolioPosition(
    val instrument: Instrument,
    val startAveragePrice: Usd,
    val endAveragePrice: Usd,
    val lots: Int
) {
    val expectedYield: Usd
        get() {
            return endAveragePrice * lots - startAveragePrice * lots
        }

    val expectedYieldInPercent: Double
        get() {
            val total = startAveragePrice * lots
            return (expectedYield / total).value
        }
}