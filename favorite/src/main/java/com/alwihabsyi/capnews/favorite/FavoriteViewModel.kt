package com.alwihabsyi.capnews.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.alwihabsyi.capnews.core.domain.usecase.NewsUseCase

class FavoriteViewModel(newsUseCase: NewsUseCase): ViewModel() {
    val favoriteList = newsUseCase.getFavoriteNews().asLiveData()
}