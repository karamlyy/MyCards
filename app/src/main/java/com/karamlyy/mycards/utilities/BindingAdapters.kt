package com.karamlyy.mycards.utilities
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Query
import com.karamlyy.mycards.R
import com.karamlyy.mycards.model.CardModel
import com.karamlyy.mycards.model.Type
import com.karamlyy.mycards.ui.home.CardCLickListener
import com.karamlyy.mycards.ui.home.HomeListAdapter
import java.util.*


@BindingAdapter("setItemCardTypeImage")
fun setItemCardTypeImage(imageView: ImageView, type: Type?) {
    val imageRes = when (type) {
        Type.MASTER -> R.drawable.master
        Type.VISA -> R.drawable.visa
        else -> R.drawable.ic_launcher_foreground
    }

    imageView.setImageResource(imageRes)
}


@BindingAdapter("cardList", "setOnClickListener", "searchQuery", "searchCardList")
fun  setHomeRecyclerViewAdapter(
    recyclerView: RecyclerView,
    list: List<CardModel>?,
    cardCLickListener: CardCLickListener,
    searchQuery: String,
    searchList: List<CardModel>?
) {
    recyclerView.apply {
        if (this.adapter == null) {
            adapter = HomeListAdapter(cardCLickListener).apply { submitList(
                if(searchQuery.isEmpty()) {
                    list
                } else {
                    searchList
                }
            ) }
        }
        else {
            (this.adapter as HomeListAdapter).submitList(
                if(searchQuery.isEmpty()) {
                    list
                } else {
                    searchList
                }
            )
        }
    }
}

@BindingAdapter("formattedCardNumber")
fun formatCardNumber(textView: TextView, cardNumber: String?) {
    textView.text = cardNumber?.chunked(4)?.joinToString(" ")
}


