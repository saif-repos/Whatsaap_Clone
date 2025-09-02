package com.example.whatsapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.whatsapp.presentation.Profile.UserProfileSetScreen
import com.example.whatsapp.presentation.callscreen.CallScreen
import com.example.whatsapp.presentation.communitiesscreen.CommunityScreen
import com.example.whatsapp.presentation.homescreen.HomeScreen
import com.example.whatsapp.presentation.splahscreen.SplashScreen
import com.example.whatsapp.presentation.updatedscreen.UpdateScreen
import com.example.whatsapp.presentation.userregistrationscreen.UserRegistrationScreen
import com.example.whatsapp.presentation.viewmodel.BaseViewModel
import com.example.whatsapp.presentation.welcomescreen.WelcomeScreen

// Import other screens as needed

@Composable
fun WhatsAppNavigationSystem() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.SplashScreen.route
    ) {
        composable(Routes.SplashScreen.route) {
            SplashScreen(navController)
        }
        composable(Routes.WellcomeScreen.route) {
            WelcomeScreen(navController)
        }
        composable(Routes.UserRegistrationScreen.route) {
            UserRegistrationScreen(navController = navController)  // Fixed here
        }
        composable(Routes.HomeScreen.route) {
            val baseViewModel: BaseViewModel = hiltViewModel()
            HomeScreen(navHostController = navController, homeBaseViewModel = baseViewModel)
        }
        composable(Routes.UpdateScreen.route) {
            UpdateScreen(navController)
        }
        composable(Routes.CommunitiesScreen.route) {
            CommunityScreen(navController)
        }
        composable(Routes.CallScreen.route) {
            CallScreen(navHostController = navController)
        }
        composable(Routes.UserProfileSetScreen.route) {
            UserProfileSetScreen(navHostController = navController)
        }
    }
}