package com.master8.kino

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.master8.kino.data.repository.PortfolioRepositoryImpl
import com.master8.kino.data.source.tinkoff.createInvestApiService
import com.master8.kino.domain.entity.Instrument
import com.master8.kino.domain.entity.PortfolioPosition
import com.master8.kino.domain.usecase.GetAllPortfolioPositions
import kotlinx.coroutines.launch

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
        setContentView(R.layout.activity_main)

//        lifecycleScope.launch {
//            GetAllPortfolioPositions(
//                PortfolioRepositoryImpl(service)
//            ).invoke().forEach {
//                Log.e("mv8", "result $it ${it.expectedYield} ${it.expectedYieldInPercent}")
//            }
//        }

        positions.forEach {
            Log.e("mv8", "result $it ${it.expectedYield} ${it.expectedYieldInPercent}")
        }
    }
}