package com.alwihabsyi.capnews.core.data.source.remote.response

import com.alwihabsyi.capnews.core.data.source.local.entity.NewsEntity


data class NewsResponse(
    val articles: List<NewsEntity>,
    val status: String,
    val totalResults: Int
)