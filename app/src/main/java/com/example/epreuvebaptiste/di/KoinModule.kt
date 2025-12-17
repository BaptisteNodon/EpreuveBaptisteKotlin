package com.example.epreuvebaptiste.di

import com.example.epreuvebaptiste.data.repository.MovieRepositoryImpl
import com.example.epreuvebaptiste.domain.repository.MovieRepository
import com.example.epreuvebaptiste.manager.SoundManager
import com.example.epreuvebaptiste.ui.movies.MoviesViewModel
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

import io.ktor.client.plugins.HttpTimeout
import com.example.epreuvebaptiste.data.remote.ApiConfig


/**
 * Configuration de l'injection de dépendances (Koin).
 * Définit les Singletons (Client HTTP, Repository, Manager) et les ViewModels
 * nécessaires au fonctionnement de l'application.
 */

val appModule = module {

    // 1. Ktor Client
    single {
        HttpClient(Android) {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    prettyPrint = true
                    isLenient = true
                    coerceInputValues = true
                })
            }
            // Timeout config via ApiConfig
            install(HttpTimeout) {
                requestTimeoutMillis = ApiConfig.TIMEOUT
                connectTimeoutMillis = ApiConfig.TIMEOUT
                socketTimeoutMillis = ApiConfig.TIMEOUT
            }

        }
    }

    // 2. Repository
    single<MovieRepository> {
        MovieRepositoryImpl(client = get())
    }

    // 3. Manager
    single {
        SoundManager(androidContext())
    }

    // 4. ViewModel
    // faut passer le 'repository' ET le 'soundManager' pour que ca marche d'ou le 'get()'
    viewModel {
        MoviesViewModel(
            repository = get(),
            soundManager = get()
        )
    }
}