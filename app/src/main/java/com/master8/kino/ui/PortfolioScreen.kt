package com.master8.kino.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.master8.kino.domain.entity.Portfolio
import com.master8.kino.domain.entity.PortfolioPart
import com.master8.kino.domain.entity.Usd
import com.master8.kino.domain.entity.times
import com.master8.kino.ui.charts.WeightBar
import com.master8.kino.ui.ext.color

@Composable
fun PortfolioScreen(portfolio: Portfolio, onClick: () -> Unit) {
    val totalPortfolioPrice = remember { portfolio.endTotalPrice }
    val expectedYield = remember { portfolio.expectedYield }
    val expectedYieldInPercent = remember { portfolio.expectedYieldInPercent }
    val maxWeightPrice = remember {
        portfolio.parts.maxOf { it.endTotalPrice.value / it.group.weight}
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            alignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.End,
                modifier = Modifier
                    .clickable(onClick = onClick, indication = null)
            ) {
                Text(
                    text = CURRENCY_FORMATTER.format(totalPortfolioPrice.value),
                    fontSize = 28.sp,
                    color = Color(0xFF131D2C),
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = "${CURRENCY_FORMATTER.format(expectedYield.value)} (${PERCENT_FORMATTER.format(expectedYieldInPercent)})",
                    fontSize = 12.sp,
                    color = Color(0xFF9299A2)
                )
            }

            Canvas(
                modifier = Modifier
                    .preferredSize(250.dp)

            ) {
                var startAngle = -90f
                var sweepAngle: Float

                portfolio.parts.forEach {

                    sweepAngle = (360 * (it.endTotalPrice / totalPortfolioPrice)).toFloat()

                    drawArc(
                        it.group.color,
                        startAngle + 1,
                         sweepAngle - 2,
                        false,
                        style = Stroke(
                            24.dp.toPx()
                        )
                    )

                    startAngle += sweepAngle
                }
            }
        }

        Spacer(modifier = Modifier.preferredHeight(48.dp))

        portfolio.parts.forEach {
            PortfolioPartBlock(
                it,
                totalPortfolioPrice,
                maxWeightPrice
            )

            Spacer(modifier = Modifier.preferredHeight(8.dp))
        }
    }
}

@Composable
private fun PortfolioPartBlock(
    part: PortfolioPart,
    totalPortfolioPrice: Usd,
    maxWeightPrice: Double
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = part.group.name,
            fontSize = 12.sp,
            color = Color(0xFF9299A2),
            modifier = Modifier.weight(1f)
        )

        Text(
            text = "${part.group.weight}x",
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFFD1D2D5)
        )

        WeightBar(part, maxWeightPrice)

        Text(
            text = PERCENT_FORMATTER.format((part.endTotalPrice / totalPortfolioPrice).value),
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF131D2C),
            textAlign = TextAlign.End,
            modifier = Modifier
                .preferredWidth(50.dp)
        )
    }
}