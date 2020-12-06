package com.master8.kino.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ScrollableColumn
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
import com.master8.kino.ui.charts.WeightBar
import com.master8.kino.ui.ext.color

@Composable
fun RecommendationsScreen(portfolio: Portfolio, onClick: () -> Unit) {
    val maxWeightPrice = remember {
        portfolio.parts.maxOf { it.endTotalPrice.value / it.group.weight}
    }

    val totalWeight = remember { portfolio.parts.sumBy { it.group.weight } }

    val totalPortfolioPrice = remember { portfolio.endTotalPrice }
    val recommendedTotalPrice = remember {
        maxWeightPrice * totalWeight
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            alignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = CURRENCY_FORMATTER.format(recommendedTotalPrice),
                    fontSize = 28.sp,
                    color = Color(0xFF131D2C),
                    fontWeight = FontWeight.Medium
                )

                Text(
                    text = "${CURRENCY_FORMATTER.format(recommendedTotalPrice - totalPortfolioPrice.value)}",
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

                    sweepAngle = (360 * (it.group.weight.toFloat()/totalWeight)).toFloat()

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

        Spacer(modifier = Modifier.preferredHeight(36.dp))

        ScrollableColumn(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = onClick, indication = null),
            verticalArrangement = Arrangement.Center
        ) {

            Spacer(modifier = Modifier.preferredHeight(16.dp))

            portfolio.parts.forEach {

                PortfolioPartBlock(it, maxWeightPrice)

                Spacer(modifier = Modifier.preferredHeight(16.dp))
            }
        }
    }
}

@Composable
private fun PortfolioPartBlock(
    part: PortfolioPart,
    maxWeightPrice: Double
) {
    val partFillWeight = remember { part.endTotalPrice.value / (maxWeightPrice * part.group.weight) }
    val requiredPrice = remember { (maxWeightPrice * part.group.weight) - part.endTotalPrice.value }

    Column {

        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = "${part.group.name} · ${CURRENCY_FORMATTER.format(part.endTotalPrice.value)}",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF131D2C),
                modifier = Modifier.weight(1f)
            )

            Text(
                text = CURRENCY_FORMATTER.format(requiredPrice),
                fontSize = 14.sp,
                color = Color(0xFF131D2C)
            )
        }

        Row {

            WeightBar(part, maxWeightPrice,
                modifier = Modifier.weight(1f)
                    .padding(top = 8.dp)
            )

            Text(
                text = "${
                    PERCENT_FORMATTER.format(
                        1 - partFillWeight
                    )
                }",
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF9299A2),
                textAlign = TextAlign.End,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
            )
        }
    }
}