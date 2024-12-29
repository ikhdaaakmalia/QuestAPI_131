package com.ikhdaamel.p9.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ikhdaamel.p9.ui.view.DestinasiEntry
import com.ikhdaamel.p9.ui.view.DestinasiHome
import com.ikhdaamel.p9.ui.view.EntryMhsScreen
import com.ikhdaamel.p9.ui.view.HomeScreen


@Composable
fun PengelolaHalaman(
    navController: NavHostController = rememberNavController()){
    NavHost(
      navController = navController,
        startDestination = DestinasiHome.route,
        modifier = Modifier
    ){
        composable(DestinasiHome.route){
            HomeScreen(
                navigateToItemEntry = {navController.navigate(DestinasiEntry.route)},
                onDetailClick = {}
            )
        }
        composable(DestinasiEntry.route){
            EntryMhsScreen(
                navigateBack = {
                navController.navigate(DestinasiHome.route){
                    popUpTo(DestinasiHome.route){
                        inclusive = true
                    }
                }
            })
        }
    }
}