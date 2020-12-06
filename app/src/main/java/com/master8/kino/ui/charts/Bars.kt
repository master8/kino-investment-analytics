package com.master8.kino.ui.charts

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.preferredWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp
import com.master8.kino.domain.entity.PortfolioPart
import com.master8.kino.ui.ext.color

@Composable
fun WeightBar(part: PortfolioPart, maxWeightPrice: Double, modifier: Modifier = Modifier) {

    val partFillWeight = remember { part.endTotalPrice.value / (maxWeightPrice * part.group.weight) }

    Canvas(
        modifier = modifier
            .preferredWidth(140.dp)
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