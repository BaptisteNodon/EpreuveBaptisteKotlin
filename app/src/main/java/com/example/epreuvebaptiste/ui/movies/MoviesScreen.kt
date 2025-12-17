package com.example.epreuvebaptiste.ui.movies

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel
import androidx.compose.ui.res.stringResource
import com.example.epreuvebaptiste.ui.components.MovieCard

/**
 * L'écran principal affichant la liste des films.
 * Injecte le ViewModel via Koin.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoviesScreen(
    viewModel: MoviesViewModel = koinViewModel()
) {
    // écoute l'état du ViewModel (Pattern MVI)
    val state by viewModel.state.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Ciné Baptiste") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { paddingValues ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            when {
                // Cas 1 : chargement
                state.isLoading -> {
                    CircularProgressIndicator()
                    Text(stringResource(com.example.epreuvebaptiste.R.string.loading))

                }

                // Cas 2 : erreur
                state.error != null -> {
                    Text(
                        text = stringResource(com.example.epreuvebaptiste.R.string.error_prefix) + state.error,
                        color = Color.Red,
                        modifier = Modifier.padding(16.dp)
                    )
                }

                // Cas 3 : liste vide
                state.movies.isEmpty() -> {
                    Text(stringResource(com.example.epreuvebaptiste.R.string.no_movies))
                }

                // Cas 4 : affichage de la liste
                else -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(state.movies) { movie ->
                            MovieCard(
                                movie = movie,
                                onClick = {
                                    // MVI : envoie l'intention de clic
                                    viewModel.handleIntent(MoviesIntent.OnMovieClicked(movie))
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

