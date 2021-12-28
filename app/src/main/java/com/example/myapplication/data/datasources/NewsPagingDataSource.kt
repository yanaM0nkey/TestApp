package com.example.myapplication.data.datasources

import android.net.Uri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.myapplication.data.entities.Article
import com.example.myapplication.data.services.RetrofitService

//class NewsPagingDataSource(private val service: RetrofitService): PagingSource<Int, Article>() {
//
//    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
//
//        val pageNumber = params.key ?: 1
//
//        return try {
//            val response = service.getNews(pageNumber)
//            val pagedResponse = response.body()
//            val data = pagedResponse?.articles
//
//            var nextPageNumber: Int? = null
//            if (data?.size ?: 0 < pagedResponse?.totalResults ?: 0) {
//                nextPageNumber = pageNumber + 1
//            }
//
//            LoadResult.Page(
//                data = data.orEmpty(),
//                prevKey = null,
//                nextKey = nextPageNumber
//            )
//        } catch (e: Exception) {
//            LoadResult.Error(e)
//        }
//    }
//
//    override fun getRefreshKey(state: PagingState<Int, Article>): Int = 1
//}