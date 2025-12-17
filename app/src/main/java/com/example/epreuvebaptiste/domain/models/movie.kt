package com.example.epreuvebaptiste.domain.models

/**
 * Modèle métier représentant un Film.
 */
data class Movie(
    val id: Int,            // id unique
    val title: String,      // titre
    val posterPath: String, // URL de l'image
    val overview: String    // synopsis
)