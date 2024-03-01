package com.karamlyy.mycards.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.karamlyy.mycards.model.CardModel


@Database(entities = [CardModel::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun cardDao(): CardDao
}