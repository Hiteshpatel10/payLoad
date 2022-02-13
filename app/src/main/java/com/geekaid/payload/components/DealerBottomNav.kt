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
import com.geekaid.payload.dealer.navigation.DealerScreens
import com.google.firebase.auth.FirebaseAuth

@ExperimentalMaterialApi
@Composable
fun DealerBottomNav(navController: NavHostController) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    var openDialog by remember { mutableStateOf(false) }

    val auth = FirebaseAuth.getInstance()
    val activity = LocalContext.current as Activity
    val context = LocalContext.current



    BottomNavigation {

        BottomNavigationItem(
            label = {
                Text(text = DealerScreens.DashboardScreen.title)
            },

            icon = {
                Icon(
                    imageVector = DealerScreens.DashboardScreen.icon,
                    contentDescription = DealerScreens.DashboardScreen.title
                )
            },

            selected = currentDestination?.hierarchy?.any {
                it.route == DealerScreens.DashboardScreen.route
            } == true,

            unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),

            onClick = {
                navController.navigate(DealerScreens.DashboardScreen.route) {
                    popUpTo(DealerScreens.DashboardScreen.route)
                    launchSingleTop = true
                }
            }
        )



        BottomNavigationItem(
            label = {
                Text(text = DealerScreens.SignOut.title)
            },

            icon = {
                Icon(
                    imageVector = DealerScreens.SignOut.icon,
                    contentDescription = DealerScreens.SignOut.title
                )
            },

            selected = currentDestination?.hierarchy?.any {
                it.route == DealerScreens.SignOut.route
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
