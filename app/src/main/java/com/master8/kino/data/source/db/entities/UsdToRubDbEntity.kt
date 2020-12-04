package com.master8.kino.data.source.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UsdToRubDbEntity(

    val price: Double,
    val date: String,

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0
)