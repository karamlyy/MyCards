package com.karamlyy.mycards.ui.home


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.karamlyy.mycards.databinding.SingleCardItemBinding
import com.karamlyy.mycards.model.CardModel

class HomeListAdapter(private val cardCLickListener: CardCLickListener): ListAdapter<CardModel, HomeListAdapter.ViewHolder>(CardCallback()) {
    class ViewHolder(private val binding: SingleCardItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(cardCLickListener: CardCLickListener, cardModel: CardModel) {
            binding.cardModel = cardModel
            binding.cardClickListener = cardCLickListener
            binding.executePendingBindings()
        }



        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val binding = SingleCardItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return ViewHolder(binding)
            }
        }
    }

    private class CardCallback: DiffUtil.ItemCallback<CardModel>() {
        override fun areItemsTheSame(oldItem: CardModel, newItem: CardModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CardModel, newItem: CardModel): Boolean {
            return oldItem.areContentsTheSame(newItem)
        }


    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder.from(parent)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(cardCLickListener, currentList[position])
    }
}

interface CardCLickListener {
    fun onCardClick(id: Int)
    fun onCardChecked(cardModel: CardModel)
}