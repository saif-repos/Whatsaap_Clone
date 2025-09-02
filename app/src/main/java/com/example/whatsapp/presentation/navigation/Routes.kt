package com.example.whatsapp.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Routes(val route: String) {

    @Serializable
    data object SplashScreen : Routes("splash_screen")

    @Serializable
    data object WellcomeScreen : Routes("registration")

    @Serializable
    data object UserRegistrationScreen : Routes("user_registration")

    @Serializable
    data object HomeScreen : Routes("home_screen")

    @Serializable
    data object UpdateScreen : Routes("update_screen")

    @Serializable
    data object CommunitiesScreen : Routes("communities_screen")

    @Serializable
    data object CallScreen : Routes("call_screen")

    @Serializable
    data object UserProfileSetScreen : Routes("profile_screen")

    @Serializable
    data object SettingsScreen : Routes("settings_screen")

    @Serializable
    data object ChatScreen : Routes("chat_screen/{phoneNumber}") {
        fun createRoute(phoneNumber: String) = "chat_screen/$phoneNumber"
    }
}
