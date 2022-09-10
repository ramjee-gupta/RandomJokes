package com.ram.jokes.utils

import android.app.Application
import com.ram.jokes.db.dbModule
import com.ram.jokes.koinmodules.retrofitModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class BaseApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@BaseApp)
            modules(retrofitModule, dbModule)
        }
    }
}