package com.master8.kino.domain.entity

data class Portfolio(
    val parts: List<PortfolioPart>
) {
    private val startTotalPrice: Usd = parts.sumByUsd { it.startTotalPrice }
    val endTotalPrice: Usd = parts.sumByUsd { it.endTotalPrice }

    val expectedYield: Usd = endTotalPrice - startTotalPrice
    val expectedYieldInPercent: Double = (expectedYield / startTotalPrice).value
}

data class PortfolioPart(
    val group: InstrumentsGroup,
    val positions: List<PortfolioPosition>
) {
    val startTotalPrice: Usd = positions.sumByUsd {
        it.startAveragePrice * it.lots
    }

    val endTotalPrice: Usd = positions.sumByUsd {
        it.endAveragePrice * it.lots
    }
}