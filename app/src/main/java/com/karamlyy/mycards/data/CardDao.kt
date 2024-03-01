package com.karamlyy.mycards.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.karamlyy.mycards.model.CardModel
import kotlinx.coroutines.flow.Flow

@Dao
interface CardDao {
    @Query("SELECT * FROM card_table")
    fun getAllCards(): Flow<List<CardModel>>

    @Query("SELECT * FROM card_table WHERE title LIKE :searchQuery OR holder LIKE :searchQuery")
    fun searchCard(searchQuery: String): Flow<List<CardModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCard(cardModel: CardModel)

    @Update
    suspend fun updateCard(cardModel: CardModel)

    @Query("SELECT * FROM card_table WHERE id=:cardId")
    suspend fun getCard(cardId: Int): CardModel

    @Delete
    suspend fun deleteCard(cardModel: CardModel)
}