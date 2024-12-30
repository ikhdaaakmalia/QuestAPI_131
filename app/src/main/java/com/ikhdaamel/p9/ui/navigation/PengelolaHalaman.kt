package com.ikhdaamel.p9.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ikhdaamel.p9.ui.view.DestinasiEntry
import com.ikhdaamel.p9.ui.view.DestinasiHome
import com.ikhdaamel.p9.ui.view.DestinasiUpdate
import com.ikhdaamel.p9.ui.view.DestinasiDetail
import com.ikhdaamel.p9.ui.view.EntryMhsScreen
import com.ikhdaamel.p9.ui.view.HomeScreen
import com.ikhdaamel.p9.ui.view.UpdateScreen
import com.ikhdaamel.p9.ui.view.DetailScreen

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
                onDetailClick = { nim ->
                    navController.navigate("$[Destinasidetail.route]/$nim")
                }
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
        composable(DestinasiUpdate.routesWithArg,
            arguments = listOf(
                navArgument(DestinasiUpdate.nim){
                    type = NavType.StringType
                }
            )
        ){
            val nim = it.arguments?.getString(DestinasiUpdate.nim)
            nim?.let { nim ->
                UpdateScreen(
                    onBack = {navController.popBackStack()},
                    onNavigate = {navController.popBackStack()}
                )
            }
        }
        composable(DestinasiDetail.routesWithArg,
            arguments = listOf(
                navArgument(DestinasiDetail.NIM){
                    type = NavType.StringType
                }
            )
        ){
            val nim = it.arguments?.getString(DestinasiDetail.NIM)
            nim?.let { nim ->
                DetailScreen(
                    navigateToUpdate ={
                        navController.navigate("${DestinasiUpdate}/$nim")
                    },
                    navigateBack = {
                        navController.navigate(DestinasiHome.route){
                            popUpTo(DestinasiHome.route){
                                inclusive = true
                            }
                        }
                    }
                )
            }
        }
    }
}