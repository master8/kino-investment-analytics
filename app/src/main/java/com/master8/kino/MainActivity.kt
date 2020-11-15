package com.master8.kino

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.master8.kino.data.repository.PortfolioRepositoryImpl
import com.master8.kino.data.source.tinkoff.createInvestApiService
import com.master8.kino.domain.usecase.GetAllPortfolioPositions
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    val service by lazy {
        createInvestApiService()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifecycleScope.launch {
            GetAllPortfolioPositions(
                PortfolioRepositoryImpl(service)
            ).invoke().forEach {
                Log.e("mv8", "result $it ${it.expectedYield} ${it.expectedYieldInPercent}")
            }
        }
    }
}