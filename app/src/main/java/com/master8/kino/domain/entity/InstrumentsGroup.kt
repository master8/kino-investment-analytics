package com.master8.kino.domain.entity

sealed class InstrumentsGroup(
    val name: String,
    val weight: Int,
    val instruments: List<Instrument>
) {
    object Stocks : InstrumentsGroup(
        "Stocks",
        3,
        listOf(
            Instrument.FXDM,
            Instrument.FXWO,
            Instrument.AAPL,
            Instrument.ATVI,
            Instrument.BAC,
            Instrument.PZZA
        )
    )

    object USStockETFs : InstrumentsGroup(
        "US Stock ETFs",
        2,
        listOf(
            Instrument.FXUS,
            Instrument.TSPX
        )
    )

    object GoldETFs : InstrumentsGroup(
        "Gold ETFs",
        3,
        listOf(
            Instrument.FXGD,
            Instrument.TGLD
        )
    )

    object ITStockETFs : InstrumentsGroup(
        "IT Stock ETFs",
        2,
        listOf(
            Instrument.FXIM,
            Instrument.TECH,
            Instrument.FXIT
        )
    )

    object AllWeatherIndex : InstrumentsGroup(
        "All-Weather Index",
        3,
        listOf(Instrument.TUSD)
    )

    object ChinaStockETFs : InstrumentsGroup(
        "China Stock ETFs",
        3,

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