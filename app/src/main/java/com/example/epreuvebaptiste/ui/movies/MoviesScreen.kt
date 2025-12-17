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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.epreuvebaptiste.R
import com.example.epreuvebaptiste.domain.models.Movie
import com.example.epreuvebaptiste.ui.components.MovieCard
import com.example.epreuvebaptiste.util.showToast
import org.koin.androidx.compose.koinViewModel

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

    // On récupère le contexte pour pouvoir afficher le Toast
    val context = LocalContext.current

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
                state.isLoading -> LoadingView()

                // Cas 2 : erreur
                state.error != null -> ErrorView(message = state.error!!)

                // Cas 3 : liste vide
                state.movies.isEmpty() -> EmptyView()

                // Cas 4 : affichage de la liste
                else -> MovieListView(
                    movies = state.movies,
                    onMovieClick = { movie ->
                        // Extension Toast
                        context.showToast("Film sélectionné : ${movie.title}")

                        // MVI : envoie l'intention de clic
                        viewModel.handleIntent(MoviesIntent.OnMovieClicked(movie))
                    }
                )
            }
        }
    }
}

// =========================================================================
// SOUS-COMPOSANTS (Pour valider le critère "LEGO" / Découpage)
// =========================================================================

@Composable
private fun MovieListView(movies: List<Movie>, onMovieClick: (Movie) -> Unit) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(movies) { movie ->
            MovieCard(
                movie = movie,
                onClick = { onMovieClick(movie) }
            )
        }
    }
}

@Composable
private fun LoadingView() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        CircularProgressIndicator()
        Spacer(modifier = Modifier.height(8.dp))
        Text(stringResource(R.string.loading))
    }
}

@Composable
private fun ErrorView(message: String) {
    Text(
        text = stringResource(R.string.error_prefix) + message,
        color = MaterialTheme.colorScheme.error,
        modifier = Modifier.padding(16.dp)
    )
}

@Composable
private fun EmptyView() {
    Text(stringResource(R.string.no_movies))
}
