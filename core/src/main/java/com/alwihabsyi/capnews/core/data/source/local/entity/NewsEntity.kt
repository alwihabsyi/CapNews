package com.alwihabsyi.capnews.core.data.source.local.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "news")
data class NewsEntity(
    val id: Int,
    val author: String?,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: Source,
    val title: String,
    @PrimaryKey
    val url: String,
    val urlToImage: String,
    var isFavorite: Boolean = false
)

@Parcelize
data class Source(
    val id: String,
    val name: String
): Parcelable