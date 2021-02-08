package com.master8.kino.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.loadImageResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.master8.kino.domain.entity.Portfolio
import com.master8.kino.domain.entity.PortfolioPart
import com.master8.kino.domain.entity.PortfolioPosition
import com.master8.kino.ui.ext.image

@Composable
fun PositionsScreen(portfolio: Portfolio, onClick: () -> Unit) {
    val totalPortfolioPrice = remember { portfolio.endTotalPrice }
    val expectedYield = remember { portfolio.expectedYield }
    val expectedYieldInPercent = remember { portfolio.expectedYieldInPercent }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Surface(elevation = 4.dp) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .clickable(onClick = onClick, indication = null)
            ) {
                Text(
                    text = CURRENCY_FORMATTER.format(totalPortfolioPrice.value),
                    fontSize = 18.sp,
                    color = Color(0xFF131D2C),
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = "${CURRENCY_FORMATTER.format(expectedYield.value)} (${PERCENT_FORMATTER.format(expectedYieldInPercent)})",
                    fontSize = 12.sp,
                    color = if (expectedYield.value > 0) {
                        Color(0xFF56B355)
                    } else {
                        Color(0xFFCC4342)
                    }
                )
            }
        }

        ScrollableColumn(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {

            Spacer(modifier = Modifier.preferredHeight(16.dp))

            portfolio.parts.forEach {

                PortfolioPartBlock(it)

                Spacer(modifier = Modifier.preferredHeight(8.dp))
            }
        }
    }
}

@Composable
private fun PortfolioPartBlock(
    part: PortfolioPart
) {
    Column {
        Text(
            text = "${part.group.name} · ${CURRENCY_FORMATTER.format(part.endTotalPrice.value)} | ${CURRENCY_FORMATTER.format(part.expectedYield.value)} (${PERCENT_FORMATTER.format(part.expectedYieldInPercent)})",
            fontSize = 12.sp,
            color = Color(0xFF9299A2),
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.preferredHeight(8.dp))

        part.positions.forEach {
            PositionBlock(it)
        }

//        Spacer(modifier = Modifier.preferredWidth(16.dp))
    }
}

@Composable
private fun PositionBlock(
    position: PortfolioPosition
) {
    Row(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Surface(
            shape = CircleShape,
            modifier = Modifier.preferredSize(40.dp)
        ) {
            loadImageResource(position.instrument.image).resource.resource?.let {
                Image(
                    asset = it,
                    modifier = Modifier.preferredSize(40.dp)
                )
            }
        }

        Spacer(modifier = Modifier.preferredWidth(16.dp))

        Column(modifier = Modifier.fillMaxWidth()) {
            Row {
                Text(
                    text = position.instrument.humanName,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF131D2C),
                    modifier = Modifier.weight(1f)
                )

                Text(
                    text = CURRENCY_FORMATTER.format(position.endTotalPrice.value),
                    fontSize = 14.sp,
                    color = Color(0xFF131D2C)
                )
            }

            Row {
                Text(
                    text = "${position.lots} lots · ${CURRENCY_FORMATTER.format(position.endAveragePrice.value)}",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color(0xFF9299A2),
                    modifier = Modifier.weight(1f)
                )

                Text(
                    text = "${CURRENCY_FORMATTER.format(position.expectedYield.value)} (${PERCENT_FORMATTER.format(position.expectedYieldInPercent)})",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    color = if (position.expectedYield.value > 0) {
                        Color(0xFF56B355)
                    } else {
                        Color(0xFFCC4342)
                    },
                    textAlign = TextAlign.End
                )
            }
        }
    }
}