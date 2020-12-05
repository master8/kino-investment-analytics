package com.master8.kino.data.source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.master8.kino.data.source.db.dao.BuyOperationDao
import com.master8.kino.data.source.db.dao.InstrumentPriceDao
import com.master8.kino.data.source.db.entities.BuyOperationDbEntity
import com.master8.kino.data.source.db.entities.InstrumentPriceEntity

@Database(
    entities = arrayOf(
        BuyOperationDbEntity::class,
        InstrumentPriceEntity::class
    ),
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun buyOperationDao(): BuyOperationDao
    abstract fun usdToRubDao(): InstrumentPriceDao
}