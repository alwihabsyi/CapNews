package com.alwihabsyi.capnews.core.utils

import com.alwihabsyi.capnews.core.data.source.local.entity.NewsEntity
import com.alwihabsyi.capnews.core.data.source.local.entity.Source
import com.alwihabsyi.capnews.core.domain.model.News

object DataMapper {
    fun mapEntitiesToDomain(newsEntities: List<NewsEntity>): List<News> =
        newsEntities.map { input ->
            News(
                id = input.id,
                author = input.author,
                content = input.content,
                description = input.description,
                publishedAt = input.publishedAt,
                source = input.source.name,
                title = input.title,
                url = input.url,
                urlToImage = input.urlToImage,
                isFavorite = input.isFavorite
            )
        }

    fun mapEntityToDomain(input: NewsEntity): News =
        News(
            id = input.id,
            author = input.author,
            content = input.content,
            description = input.description,
            publishedAt = input.publishedAt,
            source = input.source.name,
            title = input.title,
            url = input.url,
            urlToImage = input.urlToImage,
            isFavorite = input.isFavorite
        )

    fun mapDomainToEntity(input: News): NewsEntity =
        NewsEntity(
            id = input.id,
            author = input.author,
            content = input.content,
            description = input.description,
            publishedAt = input.publishedAt,
            source = Source((0).toString(), input.source),
            title = input.title,
            url = input.url,
            urlToImage = input.urlToImage,
            isFavorite = input.isFavorite
        )
}