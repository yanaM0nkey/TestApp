package com.example.myapplication.data.entities

import android.annotation.SuppressLint
import com.google.gson.annotations.SerializedName
import java.text.SimpleDateFormat


data class Article(
    val title: String,
    val urlToImage: String,
    @SerializedName("publishedAt")
    private val _publishedAt: String,
    val content: String){

    val publishedAt: Long
    @SuppressLint("SimpleDateFormat")
    get() {
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
        return simpleDateFormat.parse(_publishedAt)?.time ?: 0
    }
}