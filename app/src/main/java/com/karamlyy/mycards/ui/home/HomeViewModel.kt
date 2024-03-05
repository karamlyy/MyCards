package com.karamlyy.mycards.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Query
import com.karamlyy.mycards.data.Repository
import com.karamlyy.mycards.model.CardModel
import com.karamlyy.mycards.model.Type
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    application: Application, private val repository: Repository
): AndroidViewModel(application){

    val cardList = repository.localDataSource.getALlCards().asLiveData()
    var searchCardList: LiveData<List<CardModel>> = MutableLiveData()
    val searchQuery = MutableLiveData("")

    fun updateCard(cardModel: CardModel) {
        val updatedCardModel = cardModel.copy(isFavorite = cardModel.isFavorite?.not())
        viewModelScope.launch {
            repository.localDataSource.updateCard(updatedCardModel)
        }
    }

    fun searchCard(searchQuery: String) {
        searchCardList = repository.localDataSource.searchCard("%$searchQuery%").asLiveData()
        this.searchQuery.value = searchQuery
    }

}