package br.com.fiap.aguiabranca.ui.gestor

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Work
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import br.com.fiap.aguiabranca.ui.gestor.dashboard.DashboardGestorScreen
import br.com.fiap.aguiabranca.ui.gestor.ideias.AprovacaoIdeiaScreen
import br.com.fiap.aguiabranca.ui.gestor.ideias.TodasIdeiasScreen
import br.com.fiap.aguiabranca.ui.gestor.projetos.*

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
fun GestorScreen() {

    var selected by remember { mutableStateOf(0) }

    var telaIdeias by remember { mutableStateOf("lista") }
    var telaProgressoIdeia by remember { mutableStateOf(false) }
    var statusIdeiaSelecionada by remember { mutableStateOf("Em análise") }

    var telaProjeto by remember { mutableStateOf("lista") }
    var telaAndamentoProjeto by remember { mutableStateOf(false) }
    var telaCriarProjeto by remember { mutableStateOf(false) }

    var projetoSelecionadoIndex by remember { mutableStateOf(0) }

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
                    selected = selected == 0,
                    onClick = {
                        selected = 0
                        telaIdeias = "lista"
                        telaProgressoIdeia = false
                        telaProjeto = "lista"
                        telaAndamentoProjeto = false
                        telaCriarProjeto = false
                    },
                    icon = { Icon(Icons.Default.Dashboard, null) },
                    label = { Text("Dashboard") }
                )

                NavigationBarItem(
                    selected = selected == 1,
                    onClick = {
                        selected = 1
                        telaIdeias = "lista"
                        telaProgressoIdeia = false
                        telaProjeto = "lista"
                        telaAndamentoProjeto = false
                        telaCriarProjeto = false
                    },
                    icon = { Icon(Icons.Default.Lightbulb, null) },
                    label = { Text("Ideias") }
                )

                NavigationBarItem(
                    selected = selected == 2,
                    onClick = {
                        selected = 2
                        telaProjeto = "lista"
                        telaAndamentoProjeto = false
                        telaCriarProjeto = false
                    },
                    icon = { Icon(Icons.Default.Work, null) },
                    label = { Text("Projetos") }
                )
            }
        }
    ) { paddingValues ->

        when (selected) {
            0 -> DashboardGestorScreen(
                modifier = Modifier.padding(paddingValues)
            )

            1 -> {
                when (telaIdeias) {
                    "lista" -> {
                        if (telaProgressoIdeia) {
                            AcompanharProgressoScreen(
                                modifier = Modifier.padding(paddingValues),
                                onFechar = { telaProgressoIdeia = false }
                            )
                        } else {
                            TodasIdeiasScreen(
                                modifier = Modifier.padding(paddingValues),
                                statusAtualizado = statusIdeiaSelecionada,
                                onAbrirAprovacao = { telaIdeias = "aprovacao" },)
                        }
                    }

                    "aprovacao" -> AprovacaoIdeiaScreen(
                        modifier = Modifier.padding(paddingValues),
                        statusAtual = statusIdeiaSelecionada,
                        onVoltar = { telaIdeias = "lista" },
                        onAprovar = {
                            statusIdeiaSelecionada = "Aprovadas"
                            telaIdeias = "lista"
                        },
                        onRejeitar = {
                            statusIdeiaSelecionada = "Rejeitadas"
                            telaIdeias = "lista"
                        }
                    )
                }
            }

            2 -> {
                if (telaCriarProjeto) {
                    CriarProjetoScreen(
                        modifier = Modifier.padding(paddingValues),
                        onFechar = {
                            telaCriarProjeto = false
                        },
                        onCriar = { nome, responsavel, prioridade, status, progresso, investimento, retorno ->
                            projetos = projetos + ProjetoGestorItem(
                                nome = nome,
                                responsavel = responsavel,
                                prioridade = prioridade,
                                status = status,
                                progresso = progresso,
                                investimento = investimento,
                                retorno = retorno
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
                                    modifier = Modifier.padding(paddingValues),
                                    onFechar = { telaAndamentoProjeto = false }
                                )
                            } else {
                                ProjetosGestorScreen(
                                    modifier = Modifier.padding(paddingValues),
                                    projetos = projetos,
                                    onEditarProjeto = { index ->
                                        projetoSelecionadoIndex = index
                                        telaProjeto = "editar"
                                    },
                                    onAbrirProgresso = {
                                        telaAndamentoProjeto = true
                                    },
                                    onCriarProjeto = {
                                        telaCriarProjeto = true
                                    }
                                )
                            }
                        }

                        "editar" -> {
                            val projeto = projetos[projetoSelecionadoIndex]

                            EditarProjetoScreen(
                                modifier = Modifier.padding(paddingValues),
                                nomeInicial = projeto.nome,
                                responsavelInicial = projeto.responsavel,
                                prioridadeInicial = projeto.prioridade,
                                statusInicial = projeto.status,
                                progressoInicial = projeto.progresso,
                                investimentoInicial = projeto.investimento,
                                retornoInicial = projeto.retorno,
                                onSalvar = { nome, prioridade, status, progresso, investimento, retorno ->
                                    projetos = projetos.toMutableList().also {
                                        it[projetoSelecionadoIndex] = projeto.copy(
                                            nome = nome,
                                            prioridade = prioridade,
                                            status = status,
                                            progresso = progresso,
                                            investimento = investimento,
                                            retorno = retorno
                                        )
                                    }

                                    telaProjeto = "lista"
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}