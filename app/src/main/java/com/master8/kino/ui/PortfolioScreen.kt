package com.master8.kino.ui

import android.icu.text.NumberFormat
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.master8.kino.domain.entity.Portfolio
import com.master8.kino.domain.entity.PortfolioPart
import java.util.*

@Composable
fun PortfolioScreen(portfolio: Portfolio) {
    val totalPortfolioPrice = remember { portfolio.totalPriceNow }
    val maxWeightPrice = remember {
        portfolio.parts.maxOf { it.totalPriceNow / it.group.weight}
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
            Text(
                text = CURRENCY_FORMATTER.format(totalPortfolioPrice),
                fontSize = 28.sp,
                fontWeight = FontWeight.Medium
            )

            Canvas(
                modifier = Modifier
                    .preferredSize(250.dp)

            ) {
                var startAngle = -90f
                var sweepAngle: Float

                portfolio.parts.forEach {

                    sweepAngle = (360 * (it.totalPriceNow / totalPortfolioPrice)).toFloat()

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
fun PortfolioPartBlock(
    part: PortfolioPart,
    totalPortfolioPrice: Double,
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
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF131D2C)
        )

        WeightBar(part, maxWeightPrice)

        Text(
            text = PERCENT_FORMATTER.format(part.totalPriceNow / totalPortfolioPrice),
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF131D2C),
            textAlign = TextAlign.End,
            modifier = Modifier
                .preferredWidth(50.dp)
        )
    }
}

@Composable
fun WeightBar(part: PortfolioPart, maxWeightPrice: Double) {

    val partFillWeight = remember { part.totalPriceNow / (maxWeightPrice * part.group.weight) }

    Canvas(
        modifier = Modifier
            .width(140.dp)
    ) {
        drawLine(
            Color(0xFFEBECED),
            Offset(
                16.dp.toPx(),
                0.dp.toPx()
            ),
            Offset(
                120.dp.toPx(),
                0.dp.toPx()
            ),
            8.dp.toPx(),
            StrokeCap.Round
        )

        drawLine(
            part.group.color,
            Offset(
                16.dp.toPx(),
                0.dp.toPx()
            ),
            Offset(
                (16 + 104 * partFillWeight).dp.toPx(),
                0.dp.toPx()
            ),
            8.dp.toPx(),
            cap = StrokeCap.Round
        )
    }
}

val CURRENCY_FORMATTER = NumberFormat.getInstance(Locale.US, NumberFormat.CURRENCYSTYLE)
val PERCENT_FORMATTER = NumberFormat.getInstance(Locale.US, NumberFormat.PERCENTSTYLE)
    .apply {
        maximumFractionDigits = 2
    }