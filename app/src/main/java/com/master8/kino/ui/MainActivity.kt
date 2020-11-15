package com.master8.kino.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Text
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.setContent
import androidx.ui.tooling.preview.Preview
import com.master8.kino.data.source.tinkoff.createInvestApiService
import com.master8.kino.domain.entity.Instrument
import com.master8.kino.domain.entity.PortfolioPosition
import com.master8.kino.ui.ui.キノTheme

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
            キノTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Greeting("Android")
                }
            }
        }

        positions.forEach {
            Log.e("mv8", "result $it ${it.expectedYield} ${it.expectedYieldInPercent}")
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    キノTheme {
        Greeting("Android")
    }
}