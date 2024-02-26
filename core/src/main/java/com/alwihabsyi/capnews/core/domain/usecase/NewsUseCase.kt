package com.alwihabsyi.capnews.core.domain.usecase

import com.alwihabsyi.capnews.core.data.Resource
import com.alwihabsyi.capnews.core.domain.model.News
import kotlinx.coroutines.flow.Flow

interface NewsUseCase {
    fun getAllNews(): Flow<Resource<List<News>>>
    fun getFavoriteNews(): Flow<List<News>>
    fun getNews(url: String): Flow<News?>
    fun setFavoriteNews(news: News, state: Boolean)
}