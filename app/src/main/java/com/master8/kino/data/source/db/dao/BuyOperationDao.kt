package com.master8.kino.data.source.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.master8.kino.data.source.db.entities.BuyOperationDbEntity

@Dao
interface BuyOperationDao {

    @Insert
    suspend fun insert(operations: List<BuyOperationDbEntity>)

    @Delete
    suspend fun delete(operations: List<BuyOperationDbEntity>)


    @Query("SELECT * FROM buyOperationDbEntity")
    suspend fun getAll(): List<BuyOperationDbEntity>
}