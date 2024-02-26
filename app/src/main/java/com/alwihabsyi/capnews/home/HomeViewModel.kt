package com.alwihabsyi.capnews.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.alwihabsyi.capnews.core.domain.usecase.NewsUseCase

class HomeViewModel(newsUseCase: NewsUseCase): ViewModel() {
    val news = newsUseCase.getAllNews().asLiveData()
}