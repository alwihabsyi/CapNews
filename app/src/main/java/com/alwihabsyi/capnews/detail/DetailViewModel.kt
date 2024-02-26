package com.alwihabsyi.capnews.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.alwihabsyi.capnews.core.domain.model.News
import com.alwihabsyi.capnews.core.domain.usecase.NewsUseCase

class DetailViewModel(private val newsUseCase: NewsUseCase): ViewModel() {
    fun setFavoriteNews(news: News, newStatus: Boolean) =
        newsUseCase.setFavoriteNews(news, newStatus)

    fun getNews(url: String) =
        newsUseCase.getNews(url).asLiveData()
}