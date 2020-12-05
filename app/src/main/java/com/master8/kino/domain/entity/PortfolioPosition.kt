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
            if (lots == 0) {
                return .0
            }

            val total = startAveragePrice * lots
            return (expectedYield / total).value
        }

    val startTotalPrice: Usd = startAveragePrice * lots
    val endTotalPrice: Usd = endAveragePrice * lots
}