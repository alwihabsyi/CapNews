package com.alwihabsyi.capnews.core.data.source.local

import com.alwihabsyi.capnews.core.data.source.local.entity.NewsEntity
import com.alwihabsyi.capnews.core.data.source.local.room.NewsDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val newsDao: NewsDao) {
    fun getFavoriteNews(): Flow<List<NewsEntity>> = newsDao.getFavoriteNews()
    fun setFavoriteNews(newsEntity: NewsEntity, state: Boolean) {
        newsEntity.isFavorite = state
        if (state) {
            newsDao.setFavoriteNews(newsEntity)
        } else {
            newsDao.deleteFavoriteNews(newsEntity)
        }
    }
    fun getNews(url: String): Flow<NewsEntity?> = newsDao.getNews(url)
}