package com.geekaid.payload.components

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.geekaid.payload.MainActivity
import com.geekaid.payload.driver.navigation.DriverScreens
import com.google.firebase.auth.FirebaseAuth

@ExperimentalMaterialApi
@Composable
fun DriverBottomNav(navController: NavHostController) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    var openDialog by remember { mutableStateOf(false) }

    val auth = FirebaseAuth.getInstance()
    val activity = LocalContext.current as Activity
    val context = LocalContext.current



    BottomNavigation {

        BottomNavigationItem(
            label = {
                Text(text = DriverScreens.ReqDealsScreen.title)
            },

            icon = {
                Icon(
                    imageVector = DriverScreens.ReqDealsScreen.icon,
                    contentDescription = DriverScreens.ReqDealsScreen.title
                )
            },

            selected = currentDestination?.hierarchy?.any {
                it.route == DriverScreens.ReqDealsScreen.route
            } == true,

            unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),

            onClick = {
                navController.navigate(DriverScreens.ReqDealsScreen.route) {
                    popUpTo(DriverScreens.ReqDealsScreen.route)
                    launchSingleTop = true
                }
            }
        )

        BottomNavigationItem(
            label = {
                Text(text = DriverScreens.DashboardScreen.title)
            },

            icon = {
                Icon(
                    imageVector = DriverScreens.DashboardScreen.icon,
                    contentDescription = DriverScreens.DashboardScreen.title
                )
            },

            selected = currentDestination?.hierarchy?.any {
                it.route == DriverScreens.DashboardScreen.route
            } == true,

            unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),

            onClick = {
                navController.navigate(DriverScreens.DashboardScreen.route) {
                    popUpTo(DriverScreens.DashboardScreen.route)
                    launchSingleTop = true
                }
            }
        )

        BottomNavigationItem(
            label = {
                Text(text = DriverScreens.AssignDealsScreen.title)
            },

            icon = {
                Icon(
                    imageVector = DriverScreens.AssignDealsScreen.icon,
                    contentDescription = DriverScreens.AssignDealsScreen.title
                )
            },

            selected = currentDestination?.hierarchy?.any {
                it.route == DriverScreens.AssignDealsScreen.route
            } == true,

            unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),

            onClick = {
                navController.navigate(DriverScreens.AssignDealsScreen.route) {
                    popUpTo(DriverScreens.AssignDealsScreen.route)
                    launchSingleTop = true
                }
            }
        )

        BottomNavigationItem(
            label = {
                Text(text = DriverScreens.SignOutScreen.title)
            },

            icon = {
                Icon(
                    imageVector = DriverScreens.SignOutScreen.icon,
                    contentDescription = DriverScreens.SignOutScreen.title
                )
            },

            selected = currentDestination?.hierarchy?.any {
                it.route == DriverScreens.SignOutScreen.route
            } == true,

            unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),

            onClick = {
                openDialog = true
            }
        )
    }


    if (openDialog)
        AlertDialog(

            onDismissRequest = { openDialog = false },

            text = {
                Text(
                    text = "Sign out of Collage Notes",
                    modifier = Modifier.padding(4.dp),
                    textAlign = TextAlign.Center
                )
            },

            buttons = {
                Row(modifier = Modifier.padding(8.dp)) {
                    Button(
                        onClick = {
                            auth.signOut()
                            openDialog = false
                            activity.finish()
                            ContextCompat.startActivity(
                                context,
                                Intent(context, MainActivity::class.java),
                                null
                            )
                        },
                        modifier = Modifier.padding(6.dp)
                    ) {
                        Text(text = "Sign Out", modifier = Modifier.padding(4.dp))
                    }

                    Button(
                        onClick = { openDialog = false },
                        modifier = Modifier.padding(6.dp)
                    ) {
                        Text(text = "Cancel", modifier = Modifier.padding(4.dp))
                    }
                }
            }
        )
}
