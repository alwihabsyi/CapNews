package com.alwihabsyi.capnews.core.di

import androidx.room.Room
import com.alwihabsyi.capnews.core.data.NewsRepositoryImpl
import com.alwihabsyi.capnews.core.data.source.local.LocalDataSource
import com.alwihabsyi.capnews.core.data.source.local.room.NewsDatabase
import com.alwihabsyi.capnews.core.data.source.local.room.NewsTypeConverter
import com.alwihabsyi.capnews.core.data.source.remote.RemoteDataSource
import com.alwihabsyi.capnews.core.data.source.remote.network.ApiService
import com.alwihabsyi.capnews.core.domain.repository.NewsRepository
import com.alwihabsyi.capnews.core.utils.AppExecutors
import com.alwihabsyi.capnews.core.utils.Constants.BASE_URL
import com.alwihabsyi.capnews.core.utils.Constants.NEWS_DATABASE
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<NewsDatabase>().newsDao }
    single {
        Room.databaseBuilder(
            androidContext(),
            NewsDatabase::class.java, NEWS_DATABASE
        ).addTypeConverter(NewsTypeConverter()).fallbackToDestructiveMigration().build()
    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<NewsRepository> {
        NewsRepositoryImpl(
            get(),
            get(),
            get()
        )
    }
}