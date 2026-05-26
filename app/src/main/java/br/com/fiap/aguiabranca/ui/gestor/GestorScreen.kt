package br.com.fiap.aguiabranca.ui.gestor

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Work
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import br.com.fiap.aguiabranca.ui.gestor.dashboard.DashboardGestorScreen
import br.com.fiap.aguiabranca.ui.gestor.ideias.AprovacaoIdeiaScreen
import br.com.fiap.aguiabranca.ui.gestor.ideias.TodasIdeiasScreen
import br.com.fiap.aguiabranca.ui.gestor.perfil.PerfilGestorScreen
import br.com.fiap.aguiabranca.ui.gestor.projetos.*

enum class TelaGestor {
    DASHBOARD,
    IDEIAS,
    PROJETOS,
    PERFIL
}

data class ProjetoGestorItem(
    val nome: String,
    val responsavel: String,
    val prioridade: String,
    val status: String,
    val progresso: Float,
    val investimento: String = "1000",
    val retorno: String = "80.000,00"
)

@Composable
fun GestorScreen(
    navController: NavHostController
) {

    var telaAtual by remember {
        mutableStateOf(TelaGestor.DASHBOARD)
    }

    var telaIdeias by remember {
        mutableStateOf("lista")
    }

    var telaProgressoIdeia by remember {
        mutableStateOf(false)
    }

    var statusIdeiaSelecionada by remember {
        mutableStateOf("Em análise")
    }

    var telaProjeto by remember {
        mutableStateOf("lista")
    }

    var telaAndamentoProjeto by remember {
        mutableStateOf(false)
    }

    var telaCriarProjeto by remember {
        mutableStateOf(false)
    }

    var projetoSelecionadoIndex by remember {
        mutableStateOf(0)
    }

    var projetos by remember {

        mutableStateOf(

            listOf(

                ProjetoGestorItem(
                    nome = "Redução de papel nas garagens",
                    responsavel = "Maria Ferreira",
                    prioridade = "Média",
                    status = "Andamento",
                    progresso = 0.40f
                ),

                ProjetoGestorItem(
                    nome = "Otimização no processo de manutenção",
                    responsavel = "Ana Paula",
                    prioridade = "Alta",
                    status = "Andamento",
                    progresso = 0.60f
                ),

                ProjetoGestorItem(
                    nome = "Controle de atendimento digital",
                    responsavel = "Fernanda Lima",
                    prioridade = "Baixa",
                    status = "Aprovado",
                    progresso = 0.20f
                )
            )
        )
    }

    Scaffold(

        bottomBar = {

            NavigationBar {

                NavigationBarItem(

                    selected =
                        telaAtual == TelaGestor.DASHBOARD,

                    onClick = {

                        telaAtual =
                            TelaGestor.DASHBOARD
                    },

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
                        telaAtual == TelaGestor.IDEIAS,

                    onClick = {

                        telaAtual =
                            TelaGestor.IDEIAS

                        telaIdeias = "lista"
                        telaProgressoIdeia = false
                    },

                    icon = {
                        Icon(
                            Icons.Default.Lightbulb,
                            contentDescription = null
                        )
                    },

                    label = {
                        Text("Ideias")
                    }
                )

                NavigationBarItem(

                    selected =
                        telaAtual == TelaGestor.PROJETOS,

                    onClick = {

                        telaAtual =
                            TelaGestor.PROJETOS

                        telaProjeto = "lista"
                        telaAndamentoProjeto = false
                        telaCriarProjeto = false
                    },

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
                        telaAtual == TelaGestor.PERFIL,

                    onClick = {

                        telaAtual =
                            TelaGestor.PERFIL
                    },

                    icon = {
                        Icon(
                            Icons.Default.Person,
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

            TelaGestor.DASHBOARD -> {

                DashboardGestorScreen(
                     modifier = Modifier.padding(
                        paddingValues
                    )
                )
            }

            TelaGestor.IDEIAS -> {

                when (telaIdeias) {

                    "lista" -> {

                        if (telaProgressoIdeia) {

                            AcompanharProgressoScreen(

                                modifier = Modifier.padding(
                                    paddingValues
                                ),

                                onFechar = {

                                    telaProgressoIdeia =
                                        false
                                }
                            )

                        } else {

                            TodasIdeiasScreen(

                                modifier = Modifier.padding(
                                    paddingValues
                                ),

                                statusAtualizado =
                                    statusIdeiaSelecionada,

                                onAbrirAprovacao = {

                                    telaIdeias =
                                        "aprovacao"
                                }
                            )
                        }
                    }

                    "aprovacao" -> {

                        AprovacaoIdeiaScreen(

                            modifier = Modifier.padding(
                                paddingValues
                            ),

                            statusAtual =
                                statusIdeiaSelecionada,

                            onVoltar = {

                                telaIdeias = "lista"
                            },

                            onAprovar = {

                                statusIdeiaSelecionada =
                                    "Aprovadas"

                                telaIdeias = "lista"
                            },

                            onRejeitar = {

                                statusIdeiaSelecionada =
                                    "Rejeitadas"

                                telaIdeias = "lista"
                            }
                        )
                    }
                }
            }

            TelaGestor.PROJETOS -> {

                if (telaCriarProjeto) {

                    CriarProjetoScreen(

                        modifier = Modifier.padding(
                            paddingValues
                        ),

                        onFechar = {

                            telaCriarProjeto = false
                        },

                        onCriar = {
                                nome,
                                responsavel,
                                prioridade,
                                status,
                                progresso,
                                investimento,
                                retorno ->

                            projetos = projetos +
                                    ProjetoGestorItem(

                                        nome = nome,
                                        responsavel =
                                            responsavel,

                                        prioridade =
                                            prioridade,

                                        status = status,

                                        progresso =
                                            progresso,

                                        investimento =
                                            investimento,

                                        retorno =
                                            retorno
                                    )

                            telaCriarProjeto = false
                            telaProjeto = "lista"
                        }
                    )

                } else {

                    when (telaProjeto) {

                        "lista" -> {

                            if (telaAndamentoProjeto) {

                                AcompanharProgressoScreen(

                                    modifier = Modifier.padding(
                                        paddingValues
                                    ),

                                    onFechar = {

                                        telaAndamentoProjeto =
                                            false
                                    }
                                )

                            } else {

                                ProjetosGestorScreen(

                                    modifier = Modifier.padding(
                                        paddingValues
                                    ),

                                    projetos = projetos,

                                    onEditarProjeto = {
                                            index ->

                                        projetoSelecionadoIndex =
                                            index

                                        telaProjeto =
                                            "editar"
                                    },

                                    onAbrirProgresso = {

                                        telaAndamentoProjeto =
                                            true
                                    },

                                    onCriarProjeto = {

                                        telaCriarProjeto =
                                            true
                                    }
                                )
                            }
                        }

                        "editar" -> {

                            val projeto =
                                projetos[
                                    projetoSelecionadoIndex
                                ]

                            EditarProjetoScreen(

                                modifier = Modifier.padding(
                                    paddingValues
                                ),

                                nomeInicial =
                                    projeto.nome,

                                responsavelInicial =
                                    projeto.responsavel,

                                prioridadeInicial =
                                    projeto.prioridade,

                                statusInicial =
                                    projeto.status,

                                progressoInicial =
                                    projeto.progresso,

                                investimentoInicial =
                                    projeto.investimento,

                                retornoInicial =
                                    projeto.retorno,

                                onFechar = {

                                    telaProjeto = "lista"
                                },

                                onSalvar = {
                                        nome,
                                        prioridade,
                                        status,
                                        progresso,
                                        investimento,
                                        retorno ->

                                    projetos =
                                        projetos.toMutableList()
                                            .also {

                                                it[
                                                    projetoSelecionadoIndex
                                                ] = projeto.copy(

                                                    nome = nome,

                                                    prioridade =
                                                        prioridade,

                                                    status =
                                                        status,

                                                    progresso =
                                                        progresso,

                                                    investimento =
                                                        investimento,

                                                    retorno =
                                                        retorno
                                                )
                                            }

                                    telaProjeto = "lista"
                                }
                            )
                        }
                    }
                }
            }

            TelaGestor.PERFIL -> {

                PerfilGestorScreen(

                    modifier = Modifier.padding(
                        paddingValues
                    ),

                    onEditarPerfil = {

                    },

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