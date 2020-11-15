package com.master8.kino.domain.entity

enum class Instrument(
    val humanName: String,
    val figi: String,
    val type: Type
) {
    FXGD("FinEx Gold","BBG005DXDPK9", Type.ETF),
    FXUS("FinEx US","BBG005HLSZ23", Type.ETF),
    FXCN("FinEx China","BBG005VKB7D7", Type.ETF),
    FXIT("FinEx IT","BBG005HLTYH9", Type.ETF),
    USD("USD", "BBG0013HGFT4", Type.CURRENCY);

    enum class Type {
        ETF,
        STOCK,
        CURRENCY
    }
}