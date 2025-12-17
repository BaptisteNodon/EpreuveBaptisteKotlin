package com.example.epreuvebaptiste.domain.usecase

import com.example.epreuvebaptiste.domain.models.Movie
import com.example.epreuvebaptiste.domain.repository.MovieRepository

/**
 * UseCase (Cas d'utilisation) : Récupérer les films populaires.
 *
 * RÔLE :
 * Cette classe appartient à la couche DOMAIN. Elle encapsule une règle métier unique.
 * Ici, la règle est simple (pass-through), mais on pourrait imaginer filtrer les films
 * interdits aux moins de 18 ans ou trier par date avant de les renvoyer à l'UI.
 */
class GetPopularMoviesUseCase(private val repository: MovieRepository) {

    // L'opérateur "invoke" permet d'appeler la classe comme une fonction
    suspend operator fun invoke(): List<Movie> {
        return repository.getPopularMovies()
    }
}