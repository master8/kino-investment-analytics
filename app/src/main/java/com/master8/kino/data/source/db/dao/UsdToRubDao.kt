package com.master8.kino.data.source.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.master8.kino.data.source.db.entities.BuyOperationDbEntity
import com.master8.kino.data.source.db.entities.UsdToRubDbEntity

@Dao
interface UsdToRubDao {

    @Insert
    suspend fun insert(usdToRub: UsdToRubDbEntity)

    @Query("SELECT * FROM usdToRubDbEntity WHERE date = :date")
    suspend fun getUsdPriceAt(date: String): UsdToRubDbEntity?
}