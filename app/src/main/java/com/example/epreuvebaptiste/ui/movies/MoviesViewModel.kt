package com.example.epreuvebaptiste.ui.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.epreuvebaptiste.domain.usecase.GetPopularMoviesUseCase
import com.example.epreuvebaptiste.manager.SoundManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * ViewModel gérant la logique de l'écran des films.
 */
class MoviesViewModel(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val soundManager: SoundManager
) : ViewModel() {

    // état observable par l'UI (Backing Property)
    private val _state = MutableStateFlow(MoviesState())
    val state: StateFlow<MoviesState> = _state.asStateFlow()

    init {
        // charge les films au démarrage du VM
        handleIntent(MoviesIntent.LoadMovies)
    }

    /**
     * Point d'entrée unique pour toutes les actions de l'UI.
     */
    fun handleIntent(intent: MoviesIntent) {
        when (intent) {
            is MoviesIntent.LoadMovies -> loadMovies()
            is MoviesIntent.OnMovieClicked -> {
                // action Hardware : Jouer le son
                soundManager.playClickSound()
            }
        }
    }

    private fun loadMovies() {
        viewModelScope.launch {
            // 1. on passe l'état en Loading
            _state.update { it.copy(isLoading = true, error = null) }

            try {
                // 2. appel le UseCase comme une fonction
                val moviesList = getPopularMoviesUseCase()

                // 3. Mise à jour de l'état avec les données
                _state.update {
                    it.copy(
                        isLoading = false,
                        movies = moviesList
                    )
                }
            } catch (e: Exception) {
                // 4. gestion erreur
                _state.update {
                    it.copy(
                        isLoading = false,
                        error = "Erreur lors du chargement : ${e.localizedMessage}"
                    )
                }
            }
        }
    }
}