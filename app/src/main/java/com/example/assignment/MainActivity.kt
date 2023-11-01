package com.example.assignment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.assignment.data.model.Product
import com.example.assignment.ui.screen.CartScreen
import com.example.assignment.ui.screen.Favourite
import com.example.assignment.ui.screen.MainScreen
import com.example.assignment.ui.theme.AssignmentTheme
import com.example.assignment.viewModel.CategoryViewModel
import com.example.assignment.viewModel.CategoryViewModelFactory
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel: CategoryViewModel by viewModels { CategoryViewModelFactory(this) }
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.categorieState.collect { categories ->
                    setContent {
                        AssignmentTheme {
                            // A surface container using the 'background' color from the theme
                            Surface(
                                modifier = Modifier.fillMaxSize(),
                                color = MaterialTheme.colorScheme.background
                            ) {
                                if (categories != null) {
//                                    MainScreen(categories = categories)
                                    App(categories = categories, viewModel)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun App(categories: List<Product>, viewModel: CategoryViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "mainScreen") {
        composable("mainScreen") {
            MainScreen(categories = categories, viewmodel = viewModel, onFavouriteClicked = {
                navController.navigate("favouriteScreen")
            }) {
                navController.navigate("cartScreen")
            }
        }
        composable("favouriteScreen") {
            Favourite(
                viewModel,
                onBackPressed = { navController.navigate("mainScreen") }
            )

        }
        composable("cartScreen") {
            CartScreen(viewModel.cartList, viewModel) {
                navController.navigate("mainScreen")
            }
        }

    }
}