package com.example.epreuvebaptiste

import android.app.Application
import com.example.epreuvebaptiste.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MoviesApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger() // Logs Koin pour le debug
            androidContext(this@MoviesApp)
            modules(appModule) // On charge ton module ici
        }
    }
}