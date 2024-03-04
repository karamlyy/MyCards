package com.karamlyy.mycards.ui.newAndEdit

import android.app.Application
import android.icu.text.CaseMap.Title
import androidx.lifecycle.AndroidViewModel
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

    fun insertCard(title: String, number: String, expiredDate: String, cvv: String, holder: String, type: Type, isFavorite: Boolean){
        viewModelScope.launch {
            repository.localDataSource.insertCard(
                CardModel(
                    title = title,
                    number = number,
                    expiredDate = expiredDate,
                    cvv = cvv,
                    holder = holder,
                    type = type,
                    isFavorite = isFavorite
                )
            )
        }
    }
}