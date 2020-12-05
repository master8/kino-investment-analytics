package com.master8.kino.data.source.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.master8.kino.data.source.db.entities.InstrumentPriceEntity

@Dao
interface InstrumentPriceDao {

    @Insert
    suspend fun insert(usdToRub: InstrumentPriceEntity)

    @Query("SELECT * FROM instrumentPriceEntity WHERE date = :date AND figi = :figi")
    suspend fun getPriceAt(date: String, figi: String): InstrumentPriceEntity?
}