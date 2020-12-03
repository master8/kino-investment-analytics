package com.master8.kino.domain.entity

import androidx.compose.ui.graphics.Color

sealed class InstrumentsGroup(
    val name: String,
    val weight: Int,
    val color: Color,
    val instruments: List<Instrument>
) {
    object Stocks : InstrumentsGroup(
        "Stocks",
        2,
        Color(0xFFC3E78E),
        listOf(
            Instrument.AAPL,
            Instrument.ATVI,
            Instrument.BAC,
            Instrument.PZZA
        )
    )

    object USStockETFs : InstrumentsGroup(
        "US Stock ETFs",
        2,
        Color(0xFFC59BE8),
        listOf(Instrument.FXUS)
    )

    object GoldETFs : InstrumentsGroup(
        "Gold ETFs",
        2,
        Color(0xFFF9D44A),
        listOf(
            Instrument.FXGD,
            Instrument.TGLD
        )
    )

    object ITStockETFs : InstrumentsGroup(
        "IT Stock ETFs",
        2,
        Color(0xFF84AEF5),
        listOf(
            Instrument.FXIT,
            Instrument.TECH
        )
    )

    object AllWeatherIndex : InstrumentsGroup(
        "All-Weather Index",
        2,
        Color(0xFF83D4C3),
        listOf(Instrument.TUSD)
    )

    object ChinaStockETFs : InstrumentsGroup(
        "China Stock ETFs",
        2,
        Color(0xFFF08593),
        listOf(Instrument.FXCN)
    )

    companion object {
        val all = listOf(
            ITStockETFs,
            USStockETFs,
            Stocks,
            GoldETFs,
            ChinaStockETFs,
            AllWeatherIndex
        )
    }
}