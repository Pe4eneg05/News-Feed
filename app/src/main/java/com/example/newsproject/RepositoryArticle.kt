package com.example.newsproject

import com.example.newsproject.api.retrofit
import com.example.newsproject.entity.Articles

class RepositoryArticle {

    suspend fun getArticleList(): List<Articles> {
        return retrofit.articleList().articles.shuffled()
    }
}