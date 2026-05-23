package br.com.fiap.aguiabranca.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
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
        startDestination = "homeOperador"
    ) {

        // HOME

        composable("homeOperador") {

            HomeOperadorScreen(

                navController = navController,

                onNovaIdeiaClick = {

                    navController.navigate("novaIdeia")
                }
            )
        }

        // NOVA IDEIA

        composable("novaIdeia") {

            NovaIdeiaScreen(

                onVoltarClick = {

                    navController.popBackStack()
                }
            )
        }

        // MINHAS IDEIAS

        composable("minhasIdeias") {

            MinhasIdeiasScreen(
                navController = navController
            )
        }

        // PERFIL

        composable("perfilOperador") {

            PerfilOperadorScreen(
                navController = navController
            )
        }

        // DETALHES DA IDEIA

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
