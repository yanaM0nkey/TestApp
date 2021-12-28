package com.example.myapplication.data.services

import com.example.myapplication.data.entities.NewsResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RetrofitService {

    //todo apikey in buildConfig
    @GET("everything?q=tesla&from=2021-12-27&sortBy=publishedAt&apiKey=5ea67fd504e84cbeb81a4121cb812335")
    suspend fun getNews() : Response<NewsResponse>

//    @GET("everything?q=tesla&from=2021-12-27&sortBy=publishedAt&apiKey=5ea67fd504e84cbeb81a4121cb812335&pageSize=20&page={page}")
//    suspend fun getNews(@Path("page") page: Int) : Response<NewsResponse>

    companion object {

        var retrofitService: RetrofitService? = null

        fun getInstance() : RetrofitService {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://newsapi.org/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(RetrofitService::class.java)
        }

    }
}