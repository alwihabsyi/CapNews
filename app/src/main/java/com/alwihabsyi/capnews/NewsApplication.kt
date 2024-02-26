package com.alwihabsyi.capnews

import android.app.Application
import com.alwihabsyi.capnews.core.di.databaseModule
import com.alwihabsyi.capnews.core.di.networkModule
import com.alwihabsyi.capnews.core.di.repositoryModule
import com.alwihabsyi.capnews.di.useCaseModule
import com.alwihabsyi.capnews.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class NewsApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@NewsApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule,
                )
            )
        }
    }
}