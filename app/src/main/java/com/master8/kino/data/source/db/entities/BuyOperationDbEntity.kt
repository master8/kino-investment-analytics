package com.master8.kino.data.source.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BuyOperationDbEntity(

    val figi: String,
    val price: Double,
    val currencyName: String,
    val date: String,
    val quantityExecuted: Int,

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0
)