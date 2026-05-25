package br.com.fiap.aguiabranca.ui.operador.home.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Lightbulb
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import br.com.fiap.aguiabranca.ui.theme.AzulPrincipal

@Composable
fun BottomBarOperador(
    navController: NavHostController
) {

    val backStackEntry =
        navController.currentBackStackEntryAsState()

    val currentRoute =
        backStackEntry.value?.destination?.route

    NavigationBar(

        containerColor = Color.White
    ) {

        // HOME

        NavigationBarItem(

            selected =
                currentRoute == "homeOperador",

            onClick = {

                navController.navigate("homeOperador")
            },

            icon = {

                Icon(
                    imageVector = Icons.Outlined.Home,
                    contentDescription = null
                )
            },

            label = {

                Text("Home")
            },

            colors = NavigationBarItemDefaults.colors(

                selectedIconColor = AzulPrincipal,
                selectedTextColor = AzulPrincipal,

                indicatorColor =
                    AzulPrincipal.copy(alpha = 0.12f)
            )
        )

        // IDEIAS

        NavigationBarItem(

            selected =
                currentRoute == "minhasIdeias",

            onClick = {

                navController.navigate("minhasIdeias")
            },

            icon = {

                Icon(
                    imageVector = Icons.Outlined.Lightbulb,
                    contentDescription = null
                )
            },

            label = {

                Text("Ideias")
            },

            colors = NavigationBarItemDefaults.colors(

                selectedIconColor = AzulPrincipal,
                selectedTextColor = AzulPrincipal,

                indicatorColor =
                    AzulPrincipal.copy(alpha = 0.12f)
            )
        )

        // PERFIL

        NavigationBarItem(

            selected =
                currentRoute == "perfilOperador",

            onClick = {

                navController.navigate("perfilOperador")
            },

            icon = {

                Icon(
                    imageVector = Icons.Outlined.Person,
                    contentDescription = null
                )
            },

            label = {

                Text("Perfil")
            },

            colors = NavigationBarItemDefaults.colors(

                selectedIconColor = AzulPrincipal,
                selectedTextColor = AzulPrincipal,

                indicatorColor =
                    AzulPrincipal.copy(alpha = 0.12f)
            )
        )
    }
}