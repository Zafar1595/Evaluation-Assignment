package uz.domain.evaluationassignment.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Scaffold
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import org.koin.androidx.compose.koinViewModel
import uz.domain.evaluationassignment.R
import uz.domain.evaluationassignment.ui.screens.CallsScreen
import uz.domain.evaluationassignment.ui.screens.ChatScreen
import uz.domain.evaluationassignment.ui.screens.HomeScreen
import uz.domain.evaluationassignment.ui.screens.MoreScreen
import uz.domain.evaluationassignment.ui.screens.leads.detail.LeadsDetailScreen
import uz.domain.evaluationassignment.ui.screens.leads.LeadsScreen
import uz.domain.evaluationassignment.ui.theme.EvaluationAssignmentTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App()
        }
    }
}

@Composable
fun InsertStartData() {
    val context = androidx.compose.ui.platform.LocalContext.current
    val preferences: SharedPreferences =
        context.getSharedPreferences("EvaluationAssignmentPreferences", Context.MODE_PRIVATE)

    if (!preferences.getBoolean("firstStart", true)) { //TODO убрать ! после тестов
        val sdViewModel: StartingDataViewModel = koinViewModel<StartingDataViewModel>()
        sdViewModel.insertStartingData()
        preferences.edit().putBoolean("firstStart", false).apply()
    }
}

@Preview(showBackground = true)
@Composable
fun AppPreview() {
    App()
}

@Composable
fun App() {
    InsertStartData()

    EvaluationAssignmentTheme(darkTheme = false) {

        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            val systemUiController = rememberSystemUiController()
            systemUiController.setSystemBarsColor(color = Color.White)

            val navController = rememberNavController()

            val screens = listOf(
                Screen.HomeScreen,
                Screen.CallsScreen,
                Screen.ChatScreen,
                Screen.LeadsScreen,
                Screen.MoreScreen
            )

            // Показывать и скрывать BottomBar на разных экранах
            val showBottomBar = navController
                .currentBackStackEntryAsState().value?.destination?.route in screens.map { it.route }

            Scaffold(
                topBar = {
                    if (showBottomBar)
                        TopBar(
                            addUserOnClick = {
                                Toast.makeText(
                                    navController.context,
                                    "Add User",
                                    Toast.LENGTH_SHORT
                                ).show()
                            },
                            searchOnClick = {
                                Toast.makeText(navController.context, "Search", Toast.LENGTH_SHORT)
                                    .show()
                            }
                        )
                },

                bottomBar = {
                    if (showBottomBar) {
                        val navBackStackEntry by navController.currentBackStackEntryAsState()
                        BottomNavigationBar(
                            navBackStackEntry = navBackStackEntry
                        ) { route ->
                            navController.navigate(route) {
                                popUpTo(navController.graph.startDestinationId)
                                launchSingleTop = true
                            }
                        }
                    }
                },
                content = { padding ->
                    NavigationHost(
                        navController = navController,
                        padding = padding
                    )
                }
            )
        }
    }
}

@Composable
fun TopBar(addUserOnClick: () -> Unit, searchOnClick: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = stringResource(id = R.string.leads),
            modifier = Modifier
                .padding(16.dp, 16.dp, 16.dp, 8.dp),
            fontSize = 18.sp,
            color = Color.Black
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.padding(top = 16.dp, end = 16.dp)
        ) {
            Box(
                contentAlignment = androidx.compose.ui.Alignment.Center,
                modifier = Modifier
                    .background(
                        color = colorResource(id = R.color.grey),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .size(40.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.user_plus),
                    contentDescription = "Settings",
                    alignment = androidx.compose.ui.Alignment.Center,
                    modifier = Modifier
                        .padding(10.dp, 10.dp, 10.dp, 10.dp)
                        .clickable {
                            addUserOnClick.invoke()
                        })
            }
            Spacer(modifier = Modifier.size(12.dp))
            Box(
                contentAlignment = androidx.compose.ui.Alignment.Center,
                modifier = Modifier
                    .background(
                        color = colorResource(id = R.color.grey),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .size(40.dp)

            ) {
                Image(
                    painter = painterResource(id = R.drawable.search),
                    contentDescription = "Settings",
                    modifier = Modifier
                        .size(60.dp)
                        .padding(10.dp, 10.dp, 10.dp, 10.dp)
                        .clickable {
                            searchOnClick.invoke()
                        })
            }
        }
    }
}

@Composable
fun BottomNavigationBar(
    navBackStackEntry: NavBackStackEntry?,
    itemOnClick: (route: String) -> Unit
) {

    BottomNavigation(
        modifier = Modifier
            .fillMaxWidth()
            .height(92.dp)
            .padding(16.dp, 0.dp, 16.dp, 16.dp)
            .clip(RoundedCornerShape(20.dp)),
        backgroundColor = colorResource(id = R.color.black85)
    ) {

        val currentRoute = navBackStackEntry?.destination?.route

        val items = listOf(
            BottomNavItem.HomeScreen,
            BottomNavItem.CallsScreen,
            BottomNavItem.ChatScreen,
            BottomNavItem.LeadsScreen,
            BottomNavItem.MoreScreen
        )

        items.forEach { item ->
            val selected = currentRoute == item.route
            BottomNavigationItem(
                selected = selected,
                alwaysShowLabel = true,
                modifier = Modifier
                    .padding(0.dp, 16.dp),
                onClick = {
                    itemOnClick(item.route)
                },
                icon = {
                    Box(
                        modifier = Modifier
                            .padding(top = 0.dp, bottom = 4.dp)
                            .background(
                                color = if (selected) colorResource(id = R.color.selected_color) else Color.Transparent,
                                shape = RoundedCornerShape(100.dp)
                            )
                    ) {
                        Icon(
                            painterResource(id = item.icon),
                            contentDescription = item.label,
                            modifier = Modifier
                                .padding(horizontal = 16.dp, vertical = 4.dp),
                            tint = if (selected) Color.Black else colorResource(id = R.color.white60),
                        )
                    }
                },
                label = {
                    Text(
                        item.label,
                        color = if (selected) Color.White else colorResource(id = R.color.white60),
                        fontSize = 12.sp,
                        maxLines = 1,
                        modifier = Modifier
                            .padding(0.dp, 0.dp, 0.dp, 8.dp)
                    )
                }
            )
        }
    }
}

@Composable
fun NavigationHost(
    navController: NavHostController, //TODO как тут избавиться от этого navController?
    padding: PaddingValues,
) {

    NavHost(
        navController,
        startDestination = BottomNavItem.HomeScreen.route,
        modifier = Modifier.padding(padding)
    ) {
        composable(BottomNavItem.HomeScreen.route) {
            HomeScreen {
            }
        }
        composable(BottomNavItem.CallsScreen.route) {
            CallsScreen {
            }
        }
        composable(BottomNavItem.ChatScreen.route) {
            ChatScreen {
            }
        }
        composable(BottomNavItem.LeadsScreen.route) {
            LeadsScreen(leadOnClick = { leadId -> // TODO правильно ли настроена навигация?
                navController.navigate("${Screen.LeadsDetailScreen.route}/${leadId}")
            })
        }
        composable(BottomNavItem.MoreScreen.route) {
            MoreScreen {
            }
        }
        composable(
            Screen.LeadsDetailScreen.route + "/{data}",
            arguments = listOf(navArgument("data") { type = NavType.IntType })
        ) { backStackEntry ->
            LeadsDetailScreen(
                backStackEntry.arguments?.getInt("data"),
                navController = navController   //TODO как тут избавиться от navController?
            )
        }
    }
}

