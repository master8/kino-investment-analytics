package com.master8.kino.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.setContent
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.master8.kino.data.repository.PortfolioRepositoryImpl
import com.master8.kino.data.source.db.AppDatabase
import com.master8.kino.data.source.tinkoff.createInvestApiService
import com.master8.kino.domain.usecase.GetPortfolioNowUseCase
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "main_db"
        ).build()
    }

    val getPortfolioNowUseCase by lazy {
        GetPortfolioNowUseCase(
            PortfolioRepositoryImpl(
                createInvestApiService(),
                db.buyOperationDao(),
                db.usdToRubDao(),
                db.lastUpdatedDateDao()
            )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            val portfolio = getPortfolioNowUseCase()

            var selectedPage by mutableStateOf(Page.Portfolio)

            setContent {
                when (selectedPage) {
                    Page.Portfolio -> PortfolioScreen(portfolio, {
                        selectedPage = Page.Positions
                    }) {
                        selectedPage = Page.Recommendations
                    }
                    Page.Positions -> PositionsScreen(portfolio) {
                        selectedPage = Page.Portfolio
                    }
                    Page.Recommendations -> RecommendationsScreen(portfolio) {
                        selectedPage = Page.Portfolio
                    }
                }
            }
        }
    }
}

enum class Page {
    Portfolio,
    Positions,
    Recommendations
}