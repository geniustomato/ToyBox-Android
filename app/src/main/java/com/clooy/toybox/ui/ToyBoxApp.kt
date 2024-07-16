package com.clooy.toybox.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.clooy.toybox.core.designsystem.theme.ToyBoxTheme
import com.clooy.toybox.navigation.ToyBoxNavHost

@Composable
internal fun ToyBoxApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(), // TODO Where should I initialize this?
) {
    // TODO Why again do I have a Surface wrapper here?
    Surface(
        modifier = modifier, color = MaterialTheme.colorScheme.background
    ) {
        ToyBoxNavHost(navController = navController)
    }
}

@Preview(showBackground = true, widthDp = 320)
@Composable
fun ToyBoxAppPreview() {
    ToyBoxTheme {
        ToyBoxApp(modifier = Modifier.fillMaxSize())
    }
}
