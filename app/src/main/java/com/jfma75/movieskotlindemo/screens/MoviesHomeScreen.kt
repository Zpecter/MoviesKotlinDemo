package com.jfma75.movieskotlindemo.screens

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.jfma75.movieskotlindemo.models.Movie
import com.jfma75.movieskotlindemo.movies
import com.jfma75.movieskotlindemo.theme.lightThemeColors

@ExperimentalFoundationApi
@Composable
fun MoviesHomeScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Kotlin Movies",
                        style = MaterialTheme.typography.subtitle1,
                        color = LocalContentColor.current
                    )
                }
            )
        },
        content = {
            HomeScreenContent(navController)
        }
    )
}

@ExperimentalFoundationApi
@Composable
fun HomeScreenContent(navController: NavHostController) {
    LazyVerticalGrid(
        cells = GridCells.Fixed(2),
        content = {
            items(movies.size) { index ->
                val movie = movies[index] ?: return@items
                MovieView(movie, navController)
            }
        }
    )
}

@Composable
fun MovieView(movie: Movie, navController: NavHostController) {
    Column(modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Box(modifier = Modifier.size(width = 160.dp, height = 230.dp)) {
            Image(
                painterResource(movie.imageId),
                contentDescription = "",
                modifier = Modifier.clip(shape = RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Fit
            )
        }
        Spacer(Modifier.height(8.dp))
        Text(
            text = movie.name,
            style = MaterialTheme.typography.body1
        )
        Spacer(Modifier.height(8.dp))
        Button(
            modifier = Modifier.shadow(elevation = 12.dp, shape = RoundedCornerShape(8.dp), clip = true),
            onClick = { navController.navigate("BuyTickets/${movie.id}L") }
        ) {
            Text(
                text = "Buy Tickets",
                style = TextStyle(color = Color.White, fontWeight = FontWeight.Bold)
            )
        }
    }
}

@Composable
fun NavigateBackButton(navController: NavController) {
    if (navController.previousBackStackEntry != null) {
        Button(
            onClick = { navController.popBackStack() },
            colors = ButtonDefaults.buttonColors(backgroundColor = LightGray),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Go to Previous screen")
        }
    }
}

@Preview
@Composable
@ExperimentalFoundationApi
fun MoviesHomeScreen_Preview() {
    MaterialTheme(colors = lightThemeColors) {
        val navController = rememberNavController()
        MoviesHomeScreen(navController = navController)
    }
}