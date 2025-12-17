package com.example.epreuvebaptiste

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import com.example.epreuvebaptiste.ui.theme.EpreuveBaptisteTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EpreuveBaptisteTheme {
                EpreuveBaptisteApp()
            }
        }
    }
}

@PreviewScreenSizes
@Composable
fun EpreuveBaptisteApp() {
    //  retient écran actuel
    var currentDestination by rememberSaveable { mutableStateOf(AppDestinations.HOME) }

    NavigationSuiteScaffold(
        navigationSuiteItems = {
            AppDestinations.entries.forEach {
                item(
                    icon = { Icon(it.icon, contentDescription = it.label) },
                    label = { Text(it.label) },
                    selected = it == currentDestination,
                    onClick = { currentDestination = it } // Met à jour l'état au clic
                )
            }
        }
    ) {
        // Le contenu de NavigationSuiteScaffold remplit le reste de l'écran.

        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            // On utilise une Box pour appliquer le padding (marges) géré par le système
            Box(modifier = Modifier.padding(innerPadding)) {

                // VÉRIFICATION DE LA DESTINATION ACTUELLE
                when (currentDestination) {
                    AppDestinations.HOME -> VueHome()         // Affiche Vue 1
                    AppDestinations.FAVORITES -> VueFavorites() // Affiche Vue 2
                    AppDestinations.PROFILE -> VueProfile()     // Affiche Vue 3
                }
            }
        }
    }
}

// --- Tes Vues (Écrans) ---

@Composable
fun VueHome() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("Vue 1 : Accueil", style = MaterialTheme.typography.headlineLarge)
    }
}

@Composable
fun VueFavorites() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("Vue 2 : Favoris", style = MaterialTheme.typography.headlineLarge)
    }
}

@Composable
fun VueProfile() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("Vue 3 : Profil", style = MaterialTheme.typography.headlineLarge)
    }
}

enum class AppDestinations(
    val label: String,
    val icon: ImageVector,
) {
    HOME("Home", Icons.Default.Home),
    FAVORITES("Favorites", Icons.Default.Favorite),
    PROFILE("Profile", Icons.Default.AccountBox),
}