package com.alwihabsyi.capnews.core.data.source.remote.network

import com.alwihabsyi.capnews.core.data.source.remote.response.NewsResponse
import com.alwihabsyi.capnews.core.utils.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("everything")
    suspend fun getNews(
        @Query("page") page: Int = 1,
        @Query("sources") sources: String = "bbc-news",
        @Query("apiKey") apiKey: String = API_KEY
    ): NewsResponse
}