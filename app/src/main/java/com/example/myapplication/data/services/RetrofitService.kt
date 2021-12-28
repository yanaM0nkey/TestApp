package com.example.myapplication.data.services

import com.example.myapplication.data.entities.NewsResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RetrofitService {

    @GET("everything?q=tesla&from=2021-12-27&sortBy=publishedAt&apiKey=5ea67fd504e84cbeb81a4121cb812335")
    suspend fun getNews() : Response<NewsResponse>

    companion object {

        var retrofitService: RetrofitService? = null

        fun getInstance() : RetrofitService {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://newsapi.org/v2/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(RetrofitService::class.java)
            }
            return retrofitService!!
        }

    }
}