package com.master8.kino.ui.ext

import androidx.compose.ui.graphics.Color
import com.master8.kino.domain.entity.InstrumentsGroup

val InstrumentsGroup.color: Color
    get() {
        return Color(
            when (this) {
                InstrumentsGroup.Stocks -> 0xFFC3E78E
                InstrumentsGroup.USStockETFs -> 0xFFC59BE8
                InstrumentsGroup.GoldETFs -> 0xFFF9D44A
                InstrumentsGroup.ITStockETFs -> 0xFF84AEF5
                InstrumentsGroup.AllWeatherIndex -> 0xFF83D4C3
                InstrumentsGroup.ChinaStockETFs -> 0xFFF08593
            }
        )
    }