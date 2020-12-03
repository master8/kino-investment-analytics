package com.master8.kino.domain.entity

data class Portfolio(
    val parts: List<PortfolioPart>
) {
    private val totalPrice: Double = parts.sumByDouble { it.totalPrice }
    val totalPriceNow: Double = parts.sumByDouble { it.totalPriceNow }

    val expectedYield: Double = totalPriceNow - totalPrice
    val expectedYieldInPercent: Double = expectedYield / totalPrice
}

data class PortfolioPart(
    val group: InstrumentsGroup,
    val positions: List<PortfolioPosition>
) {
    val totalPrice: Double = positions.sumByDouble {
        it.averagePositionPrice * it.lots
    }

    val totalPriceNow: Double = positions.sumByDouble {
        it.averagePositionPriceNow * it.lots
    }
}