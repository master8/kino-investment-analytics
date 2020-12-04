package com.master8.kino.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.platform.setContent
import androidx.lifecycle.MutableLiveData
import com.master8.kino.data.source.tinkoff.createInvestApiService
import com.master8.kino.domain.entity.PortfolioPosition
import com.master8.kino.domain.usecase.GetPortfolioNowUseCase

class MainActivity : AppCompatActivity() {

    val service by lazy {
        createInvestApiService()
    }

    val portfolio = GetPortfolioNowUseCase().invoke()

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