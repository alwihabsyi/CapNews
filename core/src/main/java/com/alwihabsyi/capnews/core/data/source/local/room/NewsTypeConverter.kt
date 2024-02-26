package com.alwihabsyi.capnews.core.data.source.local.room

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.alwihabsyi.capnews.core.data.source.local.entity.Source

@ProvidedTypeConverter
class NewsTypeConverter {
    @TypeConverter
    fun sourceToString(source: Source): String {
        return "${source.id},${source.name}"
    }

    @TypeConverter
    fun stringToSource(source: String): Source {
        return source.split(",").let { sourceArray ->
            Source(sourceArray[0], sourceArray[1])
        }
    }
}