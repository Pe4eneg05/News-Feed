package com.example.newsproject.api

import com.example.newsproject.entity.ArticlesList
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface NewsApi {

    @GET("v2/everything?domains=wsj.com&apiKey=$API_KEY")
    suspend fun articleList(): ArticlesList

    companion object {
        private const val API_KEY = "125c9c41799b4988a0524193d4d908d9"
    }
}

val retrofit = Retrofit.Builder()
    .baseUrl("https://newsapi.org/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()
    .create(NewsApi::class.java)