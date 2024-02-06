package com.nathan.movieapp.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.nathan.movieapp.model.Movie
import com.nathan.movieapp.model.getMovies
import com.nathan.movieapp.navigation.MovieScreens
import com.nathan.movieapp.widgets.MovieCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(topBar = {
        Surface(shadowElevation = 0.dp) {
            TopAppBar(
                title = {
                    Text(text = "Movies")
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent),
            )
        }
    }) {
        Column(modifier = Modifier.padding(it)) {
            MainContent(navController)
        }
    }
}


@Composable
fun MainContent(
    navController: NavController,
    movieList: List<Movie> = getMovies()) {
    Column(modifier = Modifier.padding(12.dp)) {
        LazyColumn() {
            items(items = movieList) {
                MovieCard(movie = it) { movie ->
//                    Log.d("TAG", "MainContent: $movie")
                    navController.navigate(route = MovieScreens.DetailsScreen.name+"/$movie")
                }
            }
        }
    }
}

