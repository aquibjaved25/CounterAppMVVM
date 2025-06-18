package com.assignment3.nav

import com.assignment3.model.User
import kotlinx.serialization.Serializable

sealed interface NavDestination {

    val route: String

    data object Home : NavDestination {
        override val route: String
            get() = "home"
    }

   /* @Serializable
    data class TypeSafeDestination(
        val user: User
    ) : NavDestination {
        override val route: String
            get() = "type_safe_destination"
    }*/

    @Serializable
    data class TypeSafeDestination(
        val userName: String,
        val userId: Int,
        val email: String,
        val about: String
    ) : NavDestination {
        override val route: String
            get() = "type_safe_destination"
    }
}