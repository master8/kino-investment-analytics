package com.master8.kino.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import com.master8.kino.R
import com.master8.kino.data.source.tinkoff.createInvestApiService
import com.master8.kino.domain.entity.Instrument
import com.master8.kino.domain.entity.PortfolioPosition

class MainActivity : AppCompatActivity() {

    val service by lazy {
        createInvestApiService()
    }

    val positions = listOf(
        PortfolioPosition(
            Instrument.FXCN,
            50.7032649127352,
            53.54878166543277,
            7
        ),
        PortfolioPosition(
            Instrument.FXUS,
            59.5873603288523,
            64.69887662149547,
            2
        ),
        PortfolioPosition(
            Instrument.FXIT,
            105.99351487047174,
            120.55235458847008,
            1
        ),
        PortfolioPosition(
            Instrument.FXGD,
            12.353537112048155,
            12.83419705797148,
            10
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Image(
                    imageResource(R.drawable.im_fxit),
                    modifier = Modifier
                        .preferredSize(48.dp),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.preferredHeight(16.dp))

                positions.forEach {
                    val expectedYield = "%.2f".format(it.expectedYield)
                    val expectedYieldInPercent = "%.2f".format(it.expectedYieldInPercent)

                    Text("${it.instrument.humanName} $$expectedYield $expectedYieldInPercent%")
                }
            }
        }
    }
}