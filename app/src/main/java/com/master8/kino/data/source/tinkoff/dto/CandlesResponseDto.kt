package com.master8.kino.data.source.tinkoff.dto

data class CandlesResponseDto(
    var trackingId: String,
    val status: String,
    val payload: CandlesDto
)

data class CandlesDto(
    var figi: String,
    var interval: String,
    var candles: List<CandleDto>
)

data class CandleDto(
    var figi: String,
    var interval: String,
    var o: Double,
    var c: Double,
    var h: Double,
    var l: Double,
    var v: Double,
    var time: String
)