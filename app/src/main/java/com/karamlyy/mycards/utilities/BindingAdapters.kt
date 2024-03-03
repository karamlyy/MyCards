package com.karamlyy.mycards.utilities

import android.content.res.ColorStateList
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.karamlyy.mycards.R
import com.karamlyy.mycards.model.CardModel
import com.karamlyy.mycards.model.Type
import com.karamlyy.mycards.ui.home.CardCLickListener
import com.karamlyy.mycards.ui.home.HomeListAdapter


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