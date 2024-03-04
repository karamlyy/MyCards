package com.karamlyy.mycards.ui.newAndEdit

import android.app.Application
import android.icu.text.CaseMap.Title
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.karamlyy.mycards.data.Repository
import com.karamlyy.mycards.model.CardModel
import com.karamlyy.mycards.model.Type
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class NewAndEditViewModel @Inject constructor(
    application: Application,
    private val repository: Repository

): AndroidViewModel (application){

    val cardModel = MutableLiveData<CardModel>()

    fun insertCard(title: String, number: String, expiredMonth:String, expiredYear: String, cvv: String, holder: String, type: Type, isFavorite: Boolean){
        viewModelScope.launch {
            repository.localDataSource.insertCard(
                CardModel(
                    title = title,
                    number = number,
                    expiredMonth = expiredMonth,
                    expiredYear = expiredYear,
                    cvv = cvv,
                    holder = holder,
                    type = type,
                    isFavorite = isFavorite
                )
            )
        }
    }


    fun getCard(cardId: Int) {
        viewModelScope.launch {
            val card = repository.localDataSource.getCard(cardId)
            cardModel.value = card
        }
    }

    fun updateCard(title: String, number: String, expiredMonth:String, expiredYear: String, cvv: String, holder: String, type: Type, isFavorite: Boolean) {
        viewModelScope.launch {
            repository.localDataSource.updateCard(CardModel(
                id = cardModel.value?.id ?: 0,
                title = title,
                number = number,
                expiredMonth = expiredMonth,
                expiredYear = expiredYear,
                cvv = cvv,
                holder = holder,
                type = type,
                isFavorite = isFavorite
            ))
        }
    }

    fun deleteCard() {
        viewModelScope.launch {
            cardModel.value?.let {
                repository.localDataSource.deleteCard(it)
            }
        }
    }
}