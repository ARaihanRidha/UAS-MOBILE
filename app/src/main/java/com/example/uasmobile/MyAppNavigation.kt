package com.example.uasmobile
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.uasmobile.data.Cat
import com.example.uasmobile.pages.CatDetailPage
import com.example.uasmobile.pages.HomePage
import com.example.uasmobile.pages.LoginPage
import com.example.uasmobile.pages.SignupPage
import com.example.uasmobile.pages.StartPages
import com.google.gson.Gson

@Composable
fun MyAppNavigation(modifier: Modifier = Modifier, authViewModel: AuthViewModel ){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "StartPage", builder = {
        composable("StartPage"){
            StartPages(navController)
        }
        composable("login"){
            LoginPage(modifier, navController, authViewModel)
        }
        composable("signup"){
            SignupPage(modifier, navController, authViewModel)
        }
        composable("home") { HomePage(navController = navController, authViewModel = authViewModel)}
        composable(
            route = "catDetail/{catJson}",
            arguments = listOf(navArgument("catJson") { type = NavType.StringType })
        ) { backStackEntry ->
            val catJson = backStackEntry.arguments?.getString("catJson")
            val cat = Gson().fromJson(catJson, Cat::class.java)
            if (cat != null) {
                CatDetailPage(item = cat, catId = cat.id, navController = navController)
            } else {
                //
            }
        }

    })
}


