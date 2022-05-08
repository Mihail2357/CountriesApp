package com.example.countriesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.countriesapp.presentation.Screen
import com.example.countriesapp.presentation.bottomNav.BottomNavItem
import com.example.countriesapp.presentation.bottomNav.BottomNavigationBar
import com.example.countriesapp.presentation.country_detail.CountryDetailScreen
import com.example.countriesapp.presentation.country_list.CountryListScreen
import com.example.countriesapp.presentation.country_saved_list.SavedCountryListScreen
import com.example.countriesapp.ui.theme.CountriesAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CountriesAppTheme {
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()

                    Scaffold(
                        bottomBar = {
                            BottomNavigationBar(
                                items = listOf(
                                    BottomNavItem(
                                        name = "Home",
                                        route = Screen.CountryListScreen.route,
                                        icon = Icons.Default.Home
                                    ),
                                    BottomNavItem(
                                        name = "Saved",
                                        route = "Saved",
                                        icon = Icons.Default.Star,
                                        badgeCount = 23
                                    ),
                                ),
                                navController = navController,
                                onItemClick = {
                                    navController.navigate(it.route)
                                }
                            )
                        }
                    ) {
                        NavHost(
                            navController = navController,
                            startDestination = Screen.CountryListScreen.route
                        ) {
                            composable(
                                route = Screen.CountryListScreen.route
                            ) {
                                CountryListScreen(navController)
                            }
                            composable(
                                route = "Saved"
                            ) {
                                SavedCountryListScreen(navController)
                            }

                            composable(
                                route = Screen.CountryDetailScreen.route + "/{s}/{name}"
                            ) {
                                CountryDetailScreen(navController)
                            }
                        }
                    }


                }
            }
        }
    }
}