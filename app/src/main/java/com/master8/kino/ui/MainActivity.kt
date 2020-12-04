package com.master8.kino.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.platform.setContent
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import com.master8.kino.data.repository.PortfolioRepositoryImpl
import com.master8.kino.data.source.tinkoff.createInvestApiService
import com.master8.kino.domain.entity.PortfolioPosition
import com.master8.kino.domain.usecase.GetPortfolioNowUseCase
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    val service by lazy {
        createInvestApiService()
    }

    val getPortfolioNowUseCase = GetPortfolioNowUseCase(PortfolioRepositoryImpl(service))

    private val temp = MutableLiveData<List<PortfolioPosition>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            val portfolio = getPortfolioNowUseCase()
            setContent {
                PortfolioScreen(portfolio)
            }
        }
    }
}