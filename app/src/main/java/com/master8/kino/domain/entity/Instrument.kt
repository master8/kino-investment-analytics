package com.master8.kino.domain.entity

enum class Instrument(
    val humanName: String,
    val figi: String,
    val type: Type
) {
    FXGD("FinEx Gold","BBG005DXDPK9", Type.ETF),
    FXUS("FinEx US Stock ETF","BBG005HLSZ23", Type.ETF),
    FXCN("FinEx China Stock ETF","BBG005VKB7D7", Type.ETF),
    FXIT("FinEx IT Stock ETF","BBG005HLTYH9", Type.ETF),

    BAC("Bank of America Corp","BBG000BCTLF6", Type.STOCK),
    PZZA("Papa John's International Inc","BBG000BFWF13", Type.STOCK),
    AAPL("Apple","BBG000B9XRY4", Type.STOCK),
    ATVI("Activision Blizzard","BBG000CVWGS6", Type.STOCK),

    TECH("Tinkoff NASDAQ","BBG111111111", Type.ETF),
    TGLD("Tinkoff Gold","BBG222222222", Type.ETF),
    TUSD("Tinkoff All-Weather Index USD","BBG000000000", Type.ETF),

    USD("USD", "BBG0013HGFT4", Type.CURRENCY);

    enum class Type {
        ETF,
        STOCK,
        CURRENCY
    }
}