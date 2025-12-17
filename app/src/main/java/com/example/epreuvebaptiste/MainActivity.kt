package com.example.epreuvebaptiste

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.epreuvebaptiste.ui.movies.MoviesScreen
import com.example.epreuvebaptiste.ui.theme.EpreuveBaptisteTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /**
         * EXPLICATION CYCLE DE VIE (LIFECYCLE) :
         * L'utilisation de Compose via setContent lie l'UI au cycle de vie de l'Activity.
         * Cependant, grâce à l'architecture, les données sont stockées dans le ViewModel.
         * Le ViewModel survit aux changements de configuration (rotation), garantissant
         * que l'utilisateur ne perd pas sa liste de films si l'activité est recréée.
         *
         * COMMENTAIRE EN PARTIE GENERE PAR IA
         */

        setContent {
            EpreuveBaptisteTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // affiche l'écran
                    MoviesScreen()
                }
            }
        }
    }
}