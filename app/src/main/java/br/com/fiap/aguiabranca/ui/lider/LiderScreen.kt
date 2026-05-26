package br.com.fiap.aguiabranca.ui.lider

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Assessment
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.filled.Work
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import br.com.fiap.aguiabranca.ui.lider.dashboard.DashboardLiderScreen
import br.com.fiap.aguiabranca.ui.lider.diretrizes.DiretrizesLiderScreen
import br.com.fiap.aguiabranca.ui.lider.perfil.PerfilLiderScreen
import br.com.fiap.aguiabranca.ui.lider.projetos.ProjetosLiderScreen
import br.com.fiap.aguiabranca.ui.lider.relatorios.RelatoriosLiderScreen
import br.com.fiap.aguiabranca.ui.theme.*
import br.com.fiap.aguiabranca.viewmodel.LiderViewModel

enum class TelaLider {
    DASHBOARD,
    DIRETRIZES,
    PROJETOS,
    RELATORIOS,
    PERFIL
}

@Composable
fun LiderScreen(
    navController: NavHostController
) {

    var telaAtual by remember {
        mutableStateOf(TelaLider.DASHBOARD)
    }

    val viewModel: LiderViewModel = viewModel()

    Scaffold(

        bottomBar = {

            NavigationBar(

                containerColor = Branco

            ) {

                NavigationBarItem(

                    selected =
                        telaAtual == TelaLider.DASHBOARD,

                    onClick = {

                        telaAtual =
                            TelaLider.DASHBOARD
                    },

                    colors =
                        NavigationBarItemDefaults.colors(

                            selectedIconColor =
                                AzulAguaBranca,

                            selectedTextColor =
                                AzulAguaBranca,

                            indicatorColor =
                                AzulAguaBranca.copy(alpha = 0.12f),

                            unselectedIconColor =
                                CinzaTexto,

                            unselectedTextColor =
                                CinzaTexto
                        ),

                    icon = {

                        Icon(
                            Icons.Default.Dashboard,
                            contentDescription = null
                        )
                    },

                    label = {

                        Text("Dashboard")
                    }
                )

                NavigationBarItem(

                    selected =
                        telaAtual == TelaLider.DIRETRIZES,

                    onClick = {

                        telaAtual =
                            TelaLider.DIRETRIZES
                    },

                    colors =
                        NavigationBarItemDefaults.colors(

                            selectedIconColor =
                                AzulAguaBranca,

                            selectedTextColor =
                                AzulAguaBranca,

                            indicatorColor =
                                AzulAguaBranca.copy(alpha = 0.12f),

                            unselectedIconColor =
                                CinzaTexto,

                            unselectedTextColor =
                                CinzaTexto
                        ),

                    icon = {

                        Icon(
                            Icons.Default.Flag,
                            contentDescription = null
                        )
                    },

                    label = {

                        Text("Diretrizes")
                    }
                )

                NavigationBarItem(

                    selected =
                        telaAtual == TelaLider.PROJETOS,

                    onClick = {

                        telaAtual =
                            TelaLider.PROJETOS
                    },

                    colors =
                        NavigationBarItemDefaults.colors(

                            selectedIconColor =
                                AzulAguaBranca,

                            selectedTextColor =
                                AzulAguaBranca,

                            indicatorColor =
                                AzulAguaBranca.copy(alpha = 0.12f),

                            unselectedIconColor =
                                CinzaTexto,

                            unselectedTextColor =
                                CinzaTexto
                        ),

                    icon = {

                        Icon(
                            Icons.Default.Work,
                            contentDescription = null
                        )
                    },

                    label = {

                        Text("Projetos")
                    }
                )

                NavigationBarItem(

                    selected =
                        telaAtual == TelaLider.RELATORIOS,

                    onClick = {

                        telaAtual =
                            TelaLider.RELATORIOS
                    },

                    colors =
                        NavigationBarItemDefaults.colors(

                            selectedIconColor =
                                AzulAguaBranca,

                            selectedTextColor =
                                AzulAguaBranca,

                            indicatorColor =
                                AzulAguaBranca.copy(alpha = 0.12f),

                            unselectedIconColor =
                                CinzaTexto,

                            unselectedTextColor =
                                CinzaTexto
                        ),

                    icon = {

                        Icon(
                            Icons.Default.Assessment,
                            contentDescription = null
                        )
                    },

                    label = {

                        Text("Relatórios")
                    }
                )

                NavigationBarItem(

                    selected =
                        telaAtual == TelaLider.PERFIL,

                    onClick = {

                        telaAtual =
                            TelaLider.PERFIL
                    },

                    colors =
                        NavigationBarItemDefaults.colors(

                            selectedIconColor =
                                AzulAguaBranca,

                            selectedTextColor =
                                AzulAguaBranca,

                            indicatorColor =
                                AzulAguaBranca.copy(alpha = 0.12f),

                            unselectedIconColor =
                                CinzaTexto,

                            unselectedTextColor =
                                CinzaTexto
                        ),

                    icon = {

                        Icon(
                            Icons.Default.AccountCircle,
                            contentDescription = null
                        )
                    },

                    label = {

                        Text("Perfil")
                    }
                )
            }
        }

    ) { paddingValues ->

        when (telaAtual) {

            TelaLider.DASHBOARD -> {

                DashboardLiderScreen(

                    modifier = Modifier.padding(
                        paddingValues
                    ),

                    viewModel = viewModel
                )
            }

            TelaLider.DIRETRIZES -> {

                DiretrizesLiderScreen(

                    modifier = Modifier.padding(
                        paddingValues
                    ),

                    viewModel = viewModel
                )
            }

            TelaLider.PROJETOS -> {

                ProjetosLiderScreen(

                    modifier = Modifier.padding(
                        paddingValues
                    ),

                    viewModel = viewModel
                )
            }

            TelaLider.RELATORIOS -> {

                RelatoriosLiderScreen(

                    viewModel = viewModel
                )
            }

            TelaLider.PERFIL -> {

                PerfilLiderScreen(

                    modifier = Modifier.padding(
                        paddingValues
                    ),

                    onLogout = {

                        navController.navigate("login") {

                            popUpTo(0)
                        }
                    }
                )
            }
        }
    }
}
