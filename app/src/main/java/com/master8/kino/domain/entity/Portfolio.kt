package com.master8.kino.domain.entity

data class Portfolio(
    val parts: List<PortfolioPart>
) {
    private val startTotalPrice: Double = parts.sumByDouble { it.startTotalPrice }
    val endTotalPrice: Double = parts.sumByDouble { it.endTotalPrice }

    val expectedYield: Double = endTotalPrice - startTotalPrice
    val expectedYieldInPercent: Double = expectedYield / startTotalPrice
}

data class PortfolioPart(
    val group: InstrumentsGroup,
    val positions: List<PortfolioPosition>
) {
    val startTotalPrice: Double = positions.sumByDouble {
        it.startAveragePositionPrice * it.lots
    }

    val endTotalPrice: Double = positions.sumByDouble {
        it.endAveragePositionPrice * it.lots
    }
}