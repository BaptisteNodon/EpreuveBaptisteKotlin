package com.example.epreuvebaptiste.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.epreuvebaptiste.ui.movies.MoviesScreen

/**
 * Gestionnaire de Navigation (Single Activity Architecture).
 *
 * RÔLE :
 * Orchestre les transitions entre les différents écrans de l'application via le [NavHost].
 */
@Composable
fun MoviesNavigation() {
    // créé le contrôleur de navigation
    val navController = rememberNavController()

    // définit le graphe de navigation
    NavHost(
        navController = navController,
        startDestination = "movie_list" // L'écran de démarrage
    ) {
        // Route vers la liste des films
        composable("movie_list") {
            MoviesScreen()
        }

        // C'est ici qu'on ajouterait un composable("details") { ... }
    }
}