package com.example.myapplication.data.repositories

import com.example.myapplication.data.services.RetrofitService

class NewsRepository constructor(private val retrofitService: RetrofitService) {

    suspend fun getNews() = retrofitService.getNews()

}