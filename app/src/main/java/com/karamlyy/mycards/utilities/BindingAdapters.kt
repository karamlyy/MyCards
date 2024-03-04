package com.karamlyy.mycards.utilities
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
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


@BindingAdapter("cardList", "setOnClickListener")
fun  setHomeRecyclerViewAdapter(
    recyclerView: RecyclerView,
    list: List<CardModel>?,
    cardCLickListener: CardCLickListener
) {
    recyclerView.apply {
        if (this.adapter == null) {
            adapter = HomeListAdapter(cardCLickListener).apply { submitList(list) }
        }
        else {
            (this.adapter as HomeListAdapter).submitList(list)
        }
    }
}

@BindingAdapter("formattedCardNumber")
fun formatCardNumber(textView: TextView, cardNumber: String?) {
    textView.text = cardNumber?.chunked(4)?.joinToString(" ")
}

