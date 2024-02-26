package com.alwihabsyi.capnews.core.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
data class News(
    val id: Int = 0,
    val author: String?,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: String,
    val title: String,
    val url: String,
    val urlToImage: String,
    val isFavorite: Boolean = false
): Parcelable
