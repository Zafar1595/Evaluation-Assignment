package uz.domain.evaluationassignment.ui

import uz.domain.evaluationassignment.R

sealed class BottomNavItem(
    val route: String,
    val icon: Int,
    val label: String
) {
    data object HomeScreen :
        BottomNavItem(
            Screen.HomeScreen.route,
            R.drawable.home,
            "Home"
        )

    data object CallsScreen :
        BottomNavItem(
            Screen.CallsScreen.route,
            R.drawable.phone,
            "Calls"
        )

    data object ChatScreen :
        BottomNavItem(
            Screen.ChatScreen.route,
            R.drawable.messages_square,
            "Chat"
        )

    data object LeadsScreen :
        BottomNavItem(
            Screen.LeadsScreen.route,
            R.drawable.users,
            "Leads"
        )

    data object MoreScreen :
        BottomNavItem(
            Screen.MoreScreen.route,
            R.drawable.more_horizontal,
            "More"
        )
}