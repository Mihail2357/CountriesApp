package com.example.countriesapp.presentation.bottomNav

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.material.icons.filled.Star

data class BottomNavItem(
    val route: String,
    val name: String,
    val icon: ImageVector,
    val badgeCount: Int = 0

)