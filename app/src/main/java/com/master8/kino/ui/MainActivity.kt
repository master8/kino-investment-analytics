package com.master8.kino.ui

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawStyle
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.master8.kino.R
import com.master8.kino.data.source.tinkoff.createInvestApiService
import com.master8.kino.domain.entity.Instrument
import com.master8.kino.domain.entity.Portfolio
import com.master8.kino.domain.entity.PortfolioPart
import com.master8.kino.domain.entity.PortfolioPosition
import com.master8.kino.domain.usecase.GetPortfolioUseCase

class MainActivity : AppCompatActivity() {

    val service by lazy {
        createInvestApiService()
    }

    val portfolio = GetPortfolioUseCase().invoke()

    private val temp = MutableLiveData<List<PortfolioPosition>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PortfolioScreen(portfolio)
        }

//        Handler().postDelayed({
//            temp.value = positions
//        }, 3000)
    }
}

@Composable
fun PositionsScreen(livePositions: LiveData<List<PortfolioPosition>>) {
    val positions by livePositions.observeAsState(emptyList())

    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
//        Image(
//            imageResource(R.drawable.im_fxit),
//            modifier = Modifier
//                .preferredSize(48.dp),
//            contentScale = ContentScale.Crop
//        )
//        Spacer(modifier = Modifier.preferredHeight(16.dp))

        LazyColumnFor(items = positions) {
            val expectedYield = remember { "%.2f".format(it.expectedYield) }
            val expectedYieldInPercent = remember { "%.2f".format(it.expectedYieldInPercent) }

            Text("${it.instrument.humanName} $$expectedYield $expectedYieldInPercent%")
        }

        Spacer(modifier = Modifier.preferredHeight(16.dp))

        Canvas(
            modifier = Modifier
                .preferredSize(240.dp)
        ) {
            drawArc(
                Color(0xFF83D4C3),
                181f,
                43f,
                false,
                style = Stroke(
                    24.dp.toPx()
                )
            )

            drawArc(
                Color(0xFF84AEF5),
                269f,
                -43f,
                false,
                style = Stroke(
                    24.dp.toPx()
                )
            )

            drawArc(
                Color(0xFFC59BE8),
                271f,
                43f,
                false,
                style = Stroke(
                    24.dp.toPx()
                )
            )

            drawArc(
                Color(0xFFC3E78E),
                -44f,
                43f,
                false,
                style = Stroke(
                    24.dp.toPx()
                )
            )

            drawArc(
                Color(0xFFF9D44A),
                1f,
                43f,
                false,
                style = Stroke(
                    24.dp.toPx()
                )
            )

            drawArc(
                Color(0xFFF08593),
                46f,
                43f,
                false,
                style = Stroke(
                    24.dp.toPx()
                )
            )

            drawArc(
                Color(0xFF869DE4),
                91f,
                43f,
                false,
                style = Stroke(
                    24.dp.toPx()
                )
            )

            drawArc(
                Color(0xFFEE8131),
                136f,
                43f,
                false,
                style = Stroke(
                    24.dp.toPx()
                )
            )
        }

        Spacer(modifier = Modifier.preferredHeight(16.dp))

        Canvas(
            modifier = Modifier
                .preferredHeight(200.dp)
                .fillMaxWidth()
        ) {
            drawLine(
                Color(0xFFEBECED),
                Offset(
                    24.dp.toPx(),
                    24.dp.toPx()
                ),
                Offset(
                    124.dp.toPx(),
                    24.dp.toPx()
                ),
                8.dp.toPx(),
                StrokeCap.Round
            )

            drawLine(
                Color(0xFF83D4C3),
                Offset(
                    24.dp.toPx(),
                    24.dp.toPx()
                ),
                Offset(
                    36.5.dp.toPx(),
                    24.dp.toPx()
                ),
                8.dp.toPx(),
                StrokeCap.Round
            )
        }
    }
}