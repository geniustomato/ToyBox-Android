package com.clooy.toybox.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun rememberToyBoxAppState(
    navController: NavHostController = rememberNavController()
): ToyBoxAppState {
    return remember( // TODO Wouldn't it be redundant to call remember again for navController when rememberNavController() was already called?
        navController
    ) {
        ToyBoxAppState(navController = navController)
    }
}

class ToyBoxAppState(
    val navController: NavHostController
)
