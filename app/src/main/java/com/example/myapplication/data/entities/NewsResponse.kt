package com.example.myapplication.data.entities

data class NewsResponse(
     val status: String,
     val totalResults: Int,
     val articles: List<Article>
)