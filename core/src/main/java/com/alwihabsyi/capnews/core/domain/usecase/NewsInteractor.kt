package com.alwihabsyi.capnews.core.domain.usecase

import com.alwihabsyi.capnews.core.data.Resource
import com.alwihabsyi.capnews.core.domain.model.News
import com.alwihabsyi.capnews.core.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class NewsInteractor(private val newsRepository: NewsRepository): NewsUseCase {
    override fun getAllNews(): Flow<Resource<List<News>>> =
        newsRepository.getAllNews()

    override fun getFavoriteNews(): Flow<List<News>> =
        newsRepository.getFavoriteNews()

    override fun getNews(url: String): Flow<News?> =
        newsRepository.getNews(url)

    override fun setFavoriteNews(news: News, state: Boolean) =
        newsRepository.setFavoriteNews(news, state)
}