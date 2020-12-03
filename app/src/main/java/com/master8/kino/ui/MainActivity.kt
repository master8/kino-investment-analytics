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