package com.example.myapplication.ui.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.myapplication.R
import com.example.myapplication.data.entities.Article
import com.example.myapplication.databinding.NewsItemBinding
import java.text.DateFormat
import java.util.*

class NewsAdapter : RecyclerView.Adapter<NewsViewHolder>() {

    var articlesList = mutableListOf<Article>()

    fun setArticles(movies: List<Article>) {
        this.articlesList = movies.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = NewsItemBinding.inflate(inflater, parent, false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val article = articlesList[position]
        holder.binding.title.text = article.title
        holder.binding.content.text = article.content

        Glide.with(holder.itemView.context).load(article.urlToImage).diskCacheStrategy(
            DiskCacheStrategy.AUTOMATIC).placeholder(R.drawable.ic_baseline_photo_camera_400)
            .into(holder.binding.image)

        val dateFormat = DateFormat.getDateTimeInstance()
        holder.binding.publishedDate.text = dateFormat.format(Date(article.publishedAt))
    }

    override fun getItemCount(): Int {
        return articlesList.size
    }
}

class NewsViewHolder(val binding: NewsItemBinding) : RecyclerView.ViewHolder(binding.root)