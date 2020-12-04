package com.master8.kino.domain.entity

data class BuyOperation(
    val figi: String,
    val price: Currency,
    val date: Date,
    val quantityExecuted: Int
)