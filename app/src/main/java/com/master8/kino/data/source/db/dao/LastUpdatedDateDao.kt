package com.master8.kino.data.source.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.master8.kino.data.source.db.entities.LastUpdatedDateEntity

@Dao
interface LastUpdatedDateDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(date: LastUpdatedDateEntity)

    @Query("SELECT * FROM lastUpdatedDateEntity WHERE figi = :figi")
    suspend fun get(figi: String): LastUpdatedDateEntity?
}