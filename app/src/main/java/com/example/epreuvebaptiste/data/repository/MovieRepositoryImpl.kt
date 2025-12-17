package com.example.epreuvebaptiste.data.repository

import android.util.Log
import com.example.epreuvebaptiste.data.remote.ApiConfig
import com.example.epreuvebaptiste.data.remote.dto.TmdbResult
import com.example.epreuvebaptiste.data.remote.dto.toDomain
import com.example.epreuvebaptiste.domain.models.Movie
import com.example.epreuvebaptiste.domain.repository.MovieRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.isSuccess


/** Implementation du repository **/
class MovieRepositoryImpl(
    private val client: HttpClient
) : MovieRepository {

    private val apiUrl = "${ApiConfig.BASE_URL}/movie/popular"      // affichage des films populaire

    override suspend fun getPopularMovies(): List<Movie> {
        return try {
            val response = client.get(apiUrl) {
                parameter("api_key", ApiConfig.API_KEY)
                parameter("language", "fr-FR") // titres en français
            }

            if (response.status.isSuccess()) {
                val tmdbResult = response.body<TmdbResult>()
                tmdbResult.results.map { it.toDomain() }
            } else {    // gestion erreur sur le retour de l'api
                Log.e("REPO", "Erreur API : ${response.status}")
                generateMockData() // j' affiche les données mockées si ça plante
            }
            // gestion erreur de connection
        } catch (e: Exception) {
            Log.e("REPO", "Erreur Réseau : ${e.localizedMessage}")
            generateMockData()
        }
    }

    // mock de quand ca marchais pas
    private fun generateMockData(): List<Movie> {
        return listOf(
            Movie(1, "Mode Secours", "https://via.placeholder.com/150", "L'API n'a pas répondu")
        )
    }
}