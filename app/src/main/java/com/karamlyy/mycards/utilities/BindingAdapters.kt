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


@BindingAdapter("setItemCardType")
fun setItemCardType(imageView: ImageView, type: Type?) {
    val context = imageView.context

    val type = when(type) {
        Type.MASTER -> R.color.priority_high
        Type.VISA -> R.color.md_theme_light_secondary
        else -> R.color.seed
    }

    ImageViewCompat.setImageTintList(imageView, ColorStateList.valueOf(ContextCompat.getColor(context, type)))
}

@BindingAdapter("cardList", "setOnClickListener")
fun  setHomeRecyclerViewAdapter(
    recyclerView: RecyclerView,
    list: List<CardModel>,
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