package com.wonjong.eventdispatcher.presenter.mvvm_intent.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.wonjong.eventdispatcher.databinding.ItemPostBinding
import com.wonjong.eventdispatcher.domain.entity.PostEntity
import com.wonjong.eventdispatcher.presenter.utils.IdBasedDiffCallback

/**
 * Created by leewonjong@29cm.co.kr on 2023-01-06
 */
class MvvmIntentAdapter(
    private val onItemClick: ((PostEntity) -> Unit)? = null
) : ListAdapter<PostEntity, MvvmIntentAdapter.Holder>(IdBasedDiffCallback<PostEntity> { it.id }) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder = Holder(
        ItemPostBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    ) { position ->
        onItemClick?.invoke(currentList[position])
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(position) ?: return)
    }

    inner class Holder(
        private val binding: ItemPostBinding,
        onItemClick: ((Int) -> Unit)? = null
    ) : ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(bindingAdapterPosition)
            }
        }

        fun bind(item: PostEntity) {
            binding.id.text = item.id.toString()
            binding.image.setBackgroundColor(item.colorInt)
            binding.title.text = item.title
            binding.description.text = item.body
        }
    }
}