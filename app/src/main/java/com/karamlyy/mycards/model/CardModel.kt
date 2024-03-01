package com.karamlyy.mycards.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "card_table")
data class CardModel(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val number: String,
    val expiredDate: String,
    val cvv: String,
    val holder: String,
    val type: Type?,
    val isFavorite: Boolean
)
