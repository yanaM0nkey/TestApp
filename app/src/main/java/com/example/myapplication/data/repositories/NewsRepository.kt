package com.example.myapplication.data.repositories

import com.example.myapplication.data.services.RetrofitService

class NewsRepository constructor(private val retrofitService: RetrofitService) {

    suspend fun getNews() = retrofitService.getNews()

//    fun getNews(): Flow<PagingData<Article>> = Pager(
//        config = PagingConfig(pageSize = 10, prefetchDistance = 2),
//        pagingSourceFactory = { NewsPagingDataSource(retrofitService) }
//    ).flow
}