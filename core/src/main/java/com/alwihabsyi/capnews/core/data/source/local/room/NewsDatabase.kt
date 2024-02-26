package com.alwihabsyi.capnews.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.alwihabsyi.capnews.core.data.source.local.entity.NewsEntity

@Database([NewsEntity::class], version = 1, exportSchema = false)
@TypeConverters(NewsTypeConverter::class)
abstract class NewsDatabase: RoomDatabase() {
    abstract val newsDao: NewsDao
}