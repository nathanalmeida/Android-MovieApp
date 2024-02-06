package com.nathan.movieapp.screens.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.nathan.movieapp.model.Movie
import com.nathan.movieapp.model.getMovies
import com.nathan.movieapp.widgets.MovieCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(navController: NavController, movieId: String?) {
    val movie = getMovies().first { movie -> movie.id == movieId }
    Scaffold(topBar = {
        Surface(shadowElevation = 0.dp) {
            TopAppBar(
                title = {
                    Row {
                        Spacer(modifier = Modifier.width(30.dp))
                        Text(text = "Movies")
                    }
                },
                navigationIcon = {
                    Icon(imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Arrow Back",
                        modifier = Modifier.clickable {
                            navController.popBackStack()
                        })
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent),
            )
        }
    }) {
        Column(modifier = Modifier.padding(it)) {
            Surface(modifier = Modifier.fillMaxSize()) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    MovieCard(movie = movie)
                    Spacer(modifier = Modifier.height(8.dp))
                    Divider()
                    Text(text = "Movie Images")
                    HorizontalScrollableImageView(movie)
                }
            }
        }
    }
}

@Composable
private fun HorizontalScrollableImageView(movie: Movie) {
    LazyRow {
        items(movie.images) { image ->
            Card(
                modifier = Modifier
                    .padding(12.dp)
                    .size(240.dp),
                elevation = CardDefaults.cardElevation(5.dp)
            ) {
                Image(
                    painter = rememberImagePainter(data = image),
                    contentDescription = "Movie Images"
                )
            }
        }
    }
}