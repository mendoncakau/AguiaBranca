package br.com.fiap.aguiabranca.ui.operador.ideias

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.com.fiap.aguiabranca.ui.operador.home.components.BottomBarOperador
import br.com.fiap.aguiabranca.ui.operador.ideias.components.CardIdeia
import br.com.fiap.aguiabranca.ui.theme.*

data class IdeiaModel(
    val titulo: String,
    val status: String,
    val impacto: String,
    val data: String
)

@Composable
fun MinhasIdeiasScreen(
    navController: NavHostController
) {

    var filtroSelecionado by remember {
        mutableStateOf("Todas")
    }

    val ideias = listOf(

        IdeiaModel(
            titulo = "Redução de papel nas garagens",
            status = "Em análise",
            impacto = "Médio impacto",
            data = "12/05/2026"
        ),

        IdeiaModel(
            titulo = "Otimização no processo de manutenção",
            status = "Aprovada",
            impacto = "Alto impacto",
            data = "10/05/2026"
        ),

        IdeiaModel(
            titulo = "Mais sinalização nas áreas operacionais",
            status = "Implementada",
            impacto = "Médio impacto",
            data = "05/05/2026"
        ),

        IdeiaModel(
            titulo = "Uniformização de documentos",
            status = "Rejeitada",
            impacto = "Baixo impacto",
            data = "02/05/2026"
        )
    )

    val ideiasFiltradas = when (filtroSelecionado) {

        "Em análise" -> ideias.filter {
            it.status == "Em análise"
        }

        "Aprovadas" -> ideias.filter {
            it.status == "Aprovada"
        }

        "Implementadas" -> ideias.filter {
            it.status == "Implementada"
        }

        "Rejeitadas" -> ideias.filter {
            it.status == "Rejeitada"
        }

        else -> ideias
    }

    Scaffold(

        containerColor = Fundo,

        floatingActionButton = {

            FloatingActionButton(
                onClick = {
                    navController.navigate("novaIdeia")
                },
                containerColor = AzulPrincipal
            ) {

                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null,
                    tint = Branco
                )
            }
        },

        bottomBar = {

            BottomBarOperador(
                navController = navController
            )
        }

    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Minhas ideias",
                modifier = Modifier.padding(horizontal = 20.dp),
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = PretoTexto
            )

            Spacer(modifier = Modifier.height(20.dp))

            ScrollableTabRow(
                selectedTabIndex = when (filtroSelecionado) {
                    "Todas" -> 0
                    "Em análise" -> 1
                    "Aprovadas" -> 2
                    "Implementadas" -> 3
                    else -> 4
                },
                containerColor = Fundo,
                contentColor = AzulPrincipal,
                edgePadding = 12.dp
            ) {

                val filtros = listOf(
                    "Todas",
                    "Em análise",
                    "Aprovadas",
                    "Implementadas",
                    "Rejeitadas"
                )

                filtros.forEach { filtro ->

                    Tab(
                        selected = filtroSelecionado == filtro,
                        onClick = {
                            filtroSelecionado = filtro
                        },
                        text = {
                            Text(text = filtro)
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(
                    horizontal = 20.dp,
                    vertical = 12.dp
                ),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                items(ideiasFiltradas) { ideia ->

                    CardIdeia(
                        titulo = ideia.titulo,
                        status = ideia.status,
                        impacto = ideia.impacto,
                        data = ideia.data,

                        onClick = {

                            navController.navigate(
                                "detalhesIdeia/${ideia.titulo}"
                            )
                        }
                    )
                }

                item {
                    Spacer(modifier = Modifier.height(100.dp))
                }
            }
        }
    }
}