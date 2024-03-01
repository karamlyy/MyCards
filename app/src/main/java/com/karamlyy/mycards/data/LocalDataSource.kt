package com.karamlyy.mycards.data

import android.icu.text.StringSearch
import com.karamlyy.mycards.model.CardModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val cardDao: CardDao
) {
    fun getALlCards(): Flow<List<CardModel>> {
        return cardDao.getAllCards()
    }
    fun searchCard(searchQuery: String): Flow<List<CardModel>> {
        return cardDao.searchCard(searchQuery)
    }

    suspend fun insertCard(cardModel: CardModel) {
        cardDao.insertCard(cardModel)
    }

    suspend fun getCard(cardId: Int): CardModel {
        return cardDao.getCard(cardId)
    }

    suspend fun updateCard(cardModel: CardModel) {
        cardDao.updateCard(cardModel)
    }

    suspend fun deleteCard(cardModel: CardModel) {
        cardDao.deleteCard(cardModel)
    }
}