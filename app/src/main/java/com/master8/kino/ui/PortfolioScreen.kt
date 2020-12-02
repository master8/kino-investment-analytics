package com.master8.kino.ui

import android.icu.text.NumberFormat
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.*

@Composable
fun PortfolioScreen() {
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
                text = CURRENCY_FORMATTER.format(2360.87),
                fontSize = 28.sp,
                fontWeight = FontWeight.Medium
            )

            Canvas(
                modifier = Modifier
                    .preferredSize(250.dp)

            ) {
                drawArc(
                    Color(0xFF83D4C3),
                    0f,
                    360f,
                    false,
                    style = Stroke(
                        24.dp.toPx()
                    )
                )
            }
        }

        Spacer(modifier = Modifier.preferredHeight(48.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "FinEx Gold",
                fontSize = 12.sp,
                color = Color(0xFF9299A2),
                modifier = Modifier.weight(1f)
            )

            Text(
                text = "2x",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF131D2C)
            )

            WeightBar()

            Text(
                text = PERCENT_FORMATTER.format(0.2636),
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF131D2C)
            )
        }


    }
}

@Composable
fun WeightBar() {
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
            Color(0xFF83D4C3),
            Offset(
                16.dp.toPx(),
                0.dp.toPx()
            ),
            Offset(
                26.5.dp.toPx(),
                0.dp.toPx()
            ),
            8.dp.toPx(),
            StrokeCap.Round
        )
    }
}

val CURRENCY_FORMATTER = NumberFormat.getInstance(Locale.US, NumberFormat.CURRENCYSTYLE)
val PERCENT_FORMATTER = NumberFormat.getInstance(Locale.US, NumberFormat.PERCENTSTYLE)
    .apply {
        maximumFractionDigits = 2
    }