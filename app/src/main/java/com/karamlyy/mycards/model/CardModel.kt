package com.karamlyy.mycards.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "card_table")
data class CardModel(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val number: String,
    val expiredMonth: String,
    val expiredYear: String,
    val cvv: String,
    val holder: String,
    val type: Type?,
    val isFavorite: Boolean?
) {
    fun areContentsTheSame(newItem: CardModel): Boolean {
        return this.title == newItem.title &&
                this.number == newItem.number &&
                this.cvv == newItem.cvv && this.holder == newItem.holder &&
                this.type == newItem.type && this.isFavorite == newItem.isFavorite
    }
}
