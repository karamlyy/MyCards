package com.karamlyy.mycards.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
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

    fun insertCard() {
        viewModelScope.launch {
            repository.localDataSource.insertCard(CardModel(title = "Unibank", number = "5243754410952210", expiredDate = "12/24", cvv = "832", holder = "Karam Afandi", type = Type.MASTER, isFavorite = true))
        }
    }
}