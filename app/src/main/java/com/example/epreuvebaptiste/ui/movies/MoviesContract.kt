package com.example.epreuvebaptiste.ui.movies

import com.example.epreuvebaptiste.domain.models.Movie

/**
 * Définit le contrat MVI de l'écran Films.
 */

// 1. STATE : L'état de l'écran à un instant T
data class MoviesState(
    val isLoading: Boolean = false,
    val movies: List<Movie> = emptyList(),
    val error: String? = null
)

// 2. INTENT : Les actions que l'utilisateur peut faire
sealed class MoviesIntent {
    // demande le chargement
    data object LoadMovies : MoviesIntent()

    // clique sur un film
    data class OnMovieClicked(val movie: Movie) : MoviesIntent()
}