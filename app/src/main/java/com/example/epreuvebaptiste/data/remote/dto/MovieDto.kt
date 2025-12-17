package com.example.epreuvebaptiste.data.remote.dto

import com.example.epreuvebaptiste.data.remote.ApiConfig
import com.example.epreuvebaptiste.domain.models.Movie
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Convertit un [MovieDto] (Data Transfer Object) en modèle [Movie] du Domain.
 *
 * Cette fonction d'extension assure l'indépendance de la couche Domain vis-à-vis
 * de la structure JSON de l'API. Elle gère notamment la construction de l'URL
 * complète de l'affiche et fournit des valeurs par défaut si certaines données sont nulles.
 *
 * @receiver MovieDto L'objet DTO provenant de la désérialisation JSON.
 * @return Movie Une instance propre du modèle métier, prête à être utilisée par l'UI.
 * @see com.example.epreuvebaptiste.domain.models.Movie
 *
 * CE COMMENTAIRE A ETE GENERE AVEC L'UTILISATION DE GEMINI 3.0
 * (c'est mort je fais pas des pavés comme ca moi)
 * */

@Serializable
data class TmdbResult(
    @SerialName("results") val results: List<MovieDto>
)

@Serializable
data class MovieDto(
    @SerialName("id") val id: Int,
    @SerialName("title") val title: String,
    // exemple de lien pour l'image: "/xyz.jpg"
    @SerialName("poster_path") val posterPath: String? = null,
    @SerialName("overview") val overview: String? = null,
    @SerialName("vote_average") val rating: Double? = null
)

fun MovieDto.toDomain(): Movie {
    // construction de l'URL complète de l'image
    val fullImagePath = if (this.posterPath != null) {
        "${ApiConfig.IMAGE_BASE_URL}${this.posterPath}"
    } else {
        "https://via.placeholder.com/300x450?text=No+Image"
    }

    return Movie(
        id = this.id,
        title = this.title,
        posterPath = fullImagePath,
        overview = this.overview ?: "Aucune description."
    )
}