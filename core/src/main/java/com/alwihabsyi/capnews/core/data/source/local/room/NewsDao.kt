package com.alwihabsyi.capnews.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.alwihabsyi.capnews.core.data.source.local.entity.NewsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {

    @Query("SELECT * FROM News where isFavorite = 1")
    fun getFavoriteNews(): Flow<List<NewsEntity>>

    @Query("SELECT * FROM News where url = :url limit 1")
    fun getNews(url: String): Flow<NewsEntity?>

    @Insert
    fun setFavoriteNews(news: NewsEntity)

    @Delete
    fun deleteFavoriteNews(news: NewsEntity)
}