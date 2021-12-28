package com.example.myapplication.ui.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.myapplication.R
import com.example.myapplication.data.entities.Article
import com.example.myapplication.databinding.NewsItemBinding
import java.text.DateFormat
import java.util.*

class NewsPaginationAdapter: PagingDataAdapter<Article, NewsPaginationAdapter.NewsPaginationViewHolder>(ArticleComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsPaginationViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = NewsItemBinding.inflate(inflater, parent, false)
        return NewsPaginationViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsPaginationViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    inner class NewsPaginationViewHolder(private val binding: NewsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Article) = with(binding) {
            binding.title.text = item.title
            binding.content.text = item.content

            Glide.with(itemView.context).load(item.urlToImage).diskCacheStrategy(
                DiskCacheStrategy.AUTOMATIC).placeholder(R.drawable.ic_baseline_photo_camera_400)
                .into(binding.image)

            val dateFormat = DateFormat.getDateTimeInstance()
            binding.publishedDate.text = dateFormat.format(Date(item.publishedAt))
        }
    }

    object ArticleComparator : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article) =
            oldItem.title == newItem.title

        override fun areContentsTheSame(oldItem: Article, newItem: Article) =
            oldItem == newItem
    }
}