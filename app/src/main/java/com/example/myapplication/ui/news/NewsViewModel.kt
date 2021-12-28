package com.example.myapplication.ui.news

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.data.entities.Article
import com.example.myapplication.data.repositories.NewsRepository
import kotlinx.coroutines.*

class NewsViewModel constructor(private val repository: NewsRepository): ViewModel() {

    val errorMessage = MutableLiveData<String>()

    val articles = MutableLiveData<List<Article>>()

    var job: Job? = null

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }

    val loading = MutableLiveData<Boolean>()

//    private lateinit var _articlesFlow: Flow<PagingData<Article>>
//    val articlesFlow: Flow<PagingData<Article>>
//        get() = _articlesFlow
//
//    init {
//        getArticles()
//    }
//
//    private fun getArticles() = launchPagingAsync({
//        repository.getNews().cachedIn(viewModelScope)
//    }, {
//        _articlesFlow = it
//    })

    fun getArticles() {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = repository.getNews()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    articles.postValue(response.body()?.articles)
                    loading.value = false
                } else {
                    onError("Error : ${response.message()} ")
                }
            }
        }

    }

    private fun onError(message: String) {
        errorMessage.postValue(message)
        loading.postValue(false)
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}