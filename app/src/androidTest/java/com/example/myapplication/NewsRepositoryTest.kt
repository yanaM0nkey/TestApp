package com.example.myapplication

import com.example.myapplication.data.entities.Article
import com.example.myapplication.data.entities.NewsResponse
import com.example.myapplication.data.repositories.NewsRepository
import com.example.myapplication.data.services.RetrofitService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Assert.assertThrows
import org.junit.Rule
import org.junit.Test
import retrofit2.Response

@ExperimentalCoroutinesApi
class NewsRepositoryTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private val article1 = Article(
        title = "TestTitle",
        urlToImage = "https://habr.com/share/publication/596353/9e1a8e10e73ba1d7da3a08ab36f67a38/",
        content = "TestContent",
        _publishedAt = "2021-12-28T10:09:23Z"
    )
    private val newsResponse =
        NewsResponse(status = "ok", totalResults = 1, articles = listOf(article1))

    @Test
    fun getNewsSuccessfully() = mainCoroutineRule.runBlockingTest {

        val mockRetrofitService = object : RetrofitService {
            override suspend fun getNews(): Response<NewsResponse> {
                return Response.success(newsResponse)
            }
        }
        val sutNewsRepository = NewsRepository(mockRetrofitService)

        val resultResponse = sutNewsRepository.getNews()

        assertThat(resultResponse.isSuccessful, `is`(true))
        assertThat(resultResponse.body(), `is`(newsResponse))
    }

    @Test
    fun getNewsThrowsException() {

        val errorMessage = "Test exception";
        val mockRetrofitService = object : RetrofitService {
            override suspend fun getNews(): Response<NewsResponse> {
                throw RuntimeException(errorMessage)
            }
        }
        val sutNewsRepository = NewsRepository(mockRetrofitService)

        assertThrows(errorMessage, RuntimeException::class.java) {
            mainCoroutineRule.runBlockingTest {
                sutNewsRepository.getNews()
            }
        }
    }
}