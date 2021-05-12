package com.master8.kino.domain.entity

enum class Instrument(
    val humanName: String,
    val figi: String,
    val type: Type,
    val currency: Currency
) {
    FXGD("FinEx Gold","BBG005DXDPK9", Type.ETF, Currency.RUB),
    FXUS("FinEx US Stock ETF","BBG005HLSZ23", Type.ETF, Currency.RUB),
    FXCN("FinEx China Stock ETF","BBG005VKB7D7", Type.ETF, Currency.RUB),
    FXIT("FinEx IT Stock ETF OLD","BBG005HLTYH9", Type.ETF, Currency.RUB),
    FXIM("FinEx IT Stock ETF","BBG00Y6D0N45", Type.ETF, Currency.USD),
    FXWO("FinEx Global Equity ETF","BBG00R9805F5", Type.ETF, Currency.RUB),
    FXDM("FinEx Developed Markrts ETF","TCS0BMDKNM37", Type.ETF, Currency.RUB),

    BAC("Bank of America Corp","BBG000BCTLF6", Type.STOCK, Currency.USD),
    PZZA("Papa John's International Inc","BBG000BFWF13", Type.STOCK, Currency.USD),
    AAPL("Apple","BBG000B9XRY4", Type.STOCK, Currency.USD),
    ATVI("Activision Blizzard","BBG000CVWGS6", Type.STOCK, Currency.USD),

    TECH("Tinkoff NASDAQ","BBG111111111", Type.ETF, Currency.USD),
    TGLD("Tinkoff Gold","BBG222222222", Type.ETF, Currency.USD),
    TUSD("Tinkoff All-Weather Index USD","BBG000000000", Type.ETF, Currency.USD),
    TSPX("Tinkoff S&P 500","TCS00A102EQ8", Type.ETF, Currency.USD),

    USD("USD", "BBG0013HGFT4", Type.CURRENCY, Currency.RUB);

    enum class Type {
        ETF,
        STOCK,
        CURRENCY
    }

    enum class Currency {
        USD,
        RUB
    }
}