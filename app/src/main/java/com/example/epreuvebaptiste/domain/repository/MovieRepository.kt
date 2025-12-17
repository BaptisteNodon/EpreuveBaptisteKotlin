package com.example.epreuvebaptiste.domain.repository

import com.example.epreuvebaptiste.domain.models.Movie

/**
 * Interface du Repository.
 * Sert de contrat entre la couche Data et la couche Domain.
 */
interface MovieRepository {

    suspend fun getPopularMovies(): List<Movie> // récupère la liste des films
}