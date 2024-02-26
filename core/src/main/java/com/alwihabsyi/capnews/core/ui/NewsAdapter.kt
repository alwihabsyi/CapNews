package com.alwihabsyi.capnews.core.ui

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.alwihabsyi.capnews.core.databinding.ItemNewsBinding
import com.alwihabsyi.capnews.core.domain.model.News
import com.alwihabsyi.capnews.core.utils.glide

class NewsAdapter: RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {
    inner class NewsViewHolder(private val binding: ItemNewsBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: News) {
            binding.apply {
                ivNews.glide(item.urlToImage)
                tvNewsContent.text = item.title
            }
        }
    }

    private val diffUtil = object : DiffUtil.ItemCallback<News>() {
        override fun areItemsTheSame(oldItem: News, newItem: News): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: News, newItem: News): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, diffUtil)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(
            ItemNewsBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int = differ.currentList.size

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val item = differ.currentList[position]
        holder.bind(item)

        holder.itemView.setOnClickListener {
            onClick?.invoke(item)
        }
    }

    var onClick: ((News) -> Unit)? = null
}