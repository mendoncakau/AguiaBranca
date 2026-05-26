package br.com.fiap.aguiabranca.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import br.com.fiap.aguiabranca.ui.autenticacao.LoginScreen
import br.com.fiap.aguiabranca.ui.gestor.GestorScreen
import br.com.fiap.aguiabranca.ui.lider.LiderScreen
import br.com.fiap.aguiabranca.ui.operador.home.HomeOperadorScreen
import br.com.fiap.aguiabranca.ui.operador.ideias.DetalhesIdeiaScreen
import br.com.fiap.aguiabranca.ui.operador.ideias.MinhasIdeiasScreen
import br.com.fiap.aguiabranca.ui.operador.novaideia.NovaIdeiaScreen
import br.com.fiap.aguiabranca.ui.operador.perfil.PerfilOperadorScreen

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "login"
    ) {


        composable("login") {

            LoginScreen(
                navController = navController
            )
        }


        composable("homeOperador") {

            HomeOperadorScreen(

                navController = navController,

                onNovaIdeiaClick = {

                    navController.navigate("novaIdeia")
                }
            )
        }


        composable("homeGestor") {

            GestorScreen(
                navController = navController
            )
        }

        composable("homeLider") {

            LiderScreen(
                navController = navController
            )
        }

        composable("novaIdeia") {

            NovaIdeiaScreen(

                onVoltarClick = {

                    navController.popBackStack()
                }
            )
        }
        composable("minhasIdeias") {

            MinhasIdeiasScreen(
                navController = navController
            )
        }

        composable("perfilOperador") {

            PerfilOperadorScreen(
                navController = navController
            )
        }

        composable(
            route = "detalhesIdeia/{titulo}",
            arguments = listOf(
                navArgument("titulo") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->

            val titulo =
                backStackEntry.arguments?.getString("titulo") ?: ""

            DetalhesIdeiaScreen(
                navController = navController,
                tituloIdeia = titulo
            )
        }
    }
}