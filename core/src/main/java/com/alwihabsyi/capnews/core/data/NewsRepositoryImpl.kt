package com.alwihabsyi.capnews.core.data

import com.alwihabsyi.capnews.core.data.source.local.LocalDataSource
import com.alwihabsyi.capnews.core.data.source.remote.RemoteDataSource
import com.alwihabsyi.capnews.core.data.source.remote.network.ApiResponse
import com.alwihabsyi.capnews.core.domain.model.News
import com.alwihabsyi.capnews.core.domain.repository.NewsRepository
import com.alwihabsyi.capnews.core.utils.AppExecutors
import com.alwihabsyi.capnews.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class NewsRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : NewsRepository {
    override fun getAllNews(): Flow<Resource<List<News>>> = flow {
        emit(Resource.Loading())
        when (val apiResponse = remoteDataSource.getAllNews().first()) {
            is ApiResponse.Success -> {
                emit(Resource.Success(apiResponse.data.map { DataMapper.mapEntityToDomain(it) }))
            }

            ApiResponse.Empty -> {
                emit(Resource.Error("Tidak ada data"))
            }

            is ApiResponse.Error -> {
                emit(Resource.Error(apiResponse.errorMessage))
            }
        }
    }

    override fun getFavoriteNews(): Flow<List<News>> = localDataSource.getFavoriteNews().map {
        DataMapper.mapEntitiesToDomain(it)
    }

    override fun getNews(url: String): Flow<News?> =
        localDataSource.getNews(url).map {
            it?.let {
                DataMapper.mapEntityToDomain(it)
            }
        }

    override fun setFavoriteNews(news: News, state: Boolean) {
        val newsEntity = DataMapper.mapDomainToEntity(news)
        appExecutors.diskIO().execute { localDataSource.setFavoriteNews(newsEntity, state) }
    }

}