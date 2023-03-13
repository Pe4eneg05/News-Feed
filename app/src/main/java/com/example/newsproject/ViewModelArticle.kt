package com.example.newsproject

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsproject.entity.Articles
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ViewModelArticle private constructor(
    private val repository: RepositoryArticle
) : ViewModel() {
    constructor() : this(RepositoryArticle())


    private val _articles = MutableStateFlow<List<Articles>>(emptyList())
    val articles = _articles.asStateFlow()

    init {
        loadArticles()
    }

    private fun loadArticles() {
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                repository.getArticleList()
            }.fold(
                onSuccess = { _articles.value = it },
                onFailure = { Log.d("articles", it.message ?: "") }
            )
        }
    }
}

