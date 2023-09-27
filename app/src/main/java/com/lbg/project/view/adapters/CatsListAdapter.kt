package com.lbg.project.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lbg.project.R
import com.lbg.project.databinding.AdapterCatsListBinding
import com.lbg.project.models.CatsDataModel

class CatsListAdapter(val context: Context, private val catsCallBack: CatDetailCallBack) :
    ListAdapter<CatsDataModel, CatsListAdapter.ItemViewHolder>(CatsDiffCallBack()) {
    private class CatsDiffCallBack : DiffUtil.ItemCallback<CatsDataModel>() {
        override fun areItemsTheSame(
            oldItem: CatsDataModel,
            newItem: CatsDataModel
        ): Boolean {
            return (oldItem.id == newItem.id)

        }

        override fun areContentsTheSame(
            oldItem: CatsDataModel,
            newItem: CatsDataModel
        ): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = AdapterCatsListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ItemViewHolder(binding)
    }

    inner class ItemViewHolder(val binding: AdapterCatsListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CatsDataModel) {

            Glide.with(context)
                .load(item.url)
                .placeholder(R.drawable.placeholder) // Optional: Placeholder image
                .into(binding.ivCats)


        }
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.binding.root.setOnClickListener {
            catsCallBack.callForCatDetails(currentList[position])
        }

    }

    interface CatDetailCallBack {
        fun callForCatDetails(cats: CatsDataModel)
    }
}