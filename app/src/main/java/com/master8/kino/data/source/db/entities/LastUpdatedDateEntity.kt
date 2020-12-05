package com.master8.kino.data.source.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LastUpdatedDateEntity(

    val figi: String,
    val date: String,

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0
)