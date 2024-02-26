package com.alwihabsyi.capnews.di

import com.alwihabsyi.capnews.core.domain.usecase.NewsInteractor
import com.alwihabsyi.capnews.core.domain.usecase.NewsUseCase
import com.alwihabsyi.capnews.detail.DetailViewModel
import com.alwihabsyi.capnews.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<NewsUseCase> { NewsInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}