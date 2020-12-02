package com.master8.kino.domain.entity

import androidx.compose.ui.graphics.Color

data class Portfolio(
    val parts: List<PortfolioPart>
) {
    val totalPriceNow: Double = parts.sumByDouble { it.totalPriceNow }
}

data class PortfolioPart(
    val name: String,
    val weight: Int,
    val color: Color,
    val positions: List<PortfolioPosition>
) {
    val totalPriceNow: Double = positions.sumByDouble {
        it.averagePositionPriceNow * it.lots
    }
}