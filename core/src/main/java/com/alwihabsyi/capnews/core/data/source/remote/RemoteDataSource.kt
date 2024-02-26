package com.alwihabsyi.capnews.core.data.source.remote

import android.util.Log
import com.alwihabsyi.capnews.core.data.source.local.entity.NewsEntity
import com.alwihabsyi.capnews.core.data.source.remote.network.ApiResponse
import com.alwihabsyi.capnews.core.data.source.remote.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {
    suspend fun getAllNews(): Flow<ApiResponse<List<NewsEntity>>> {
        return flow {
            try {
                val response = apiService.getNews()
                val data = response.articles
                if (data.isNotEmpty()) {
                    emit(ApiResponse.Success(response.articles))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}