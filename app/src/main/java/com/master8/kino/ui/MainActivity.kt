package com.master8.kino.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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
            setContent {
                PortfolioScreen(portfolio)
            }
        }
    }
}