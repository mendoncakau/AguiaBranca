package br.com.fiap.aguiabranca.ui.lider

import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.fiap.aguiabranca.ui.lider.dashboard.DashboardLiderScreen
import br.com.fiap.aguiabranca.ui.lider.diretrizes.DiretrizesLiderScreen
import br.com.fiap.aguiabranca.ui.lider.projetos.ProjetosLiderScreen
import br.com.fiap.aguiabranca.ui.lider.relatorios.RelatoriosLiderScreen
import br.com.fiap.aguiabranca.viewmodel.LiderViewModel

enum class TelaLider {
    DASHBOARD,
    DIRETRIZES,
    PROJETOS,
    RELATORIOS
}

@Composable
fun LiderScreen() {
    var telaAtual by remember { mutableStateOf(TelaLider.DASHBOARD) }

    val viewModel: LiderViewModel = viewModel()

    when (telaAtual) {
        TelaLider.DASHBOARD -> DashboardLiderScreen(
            viewModel = viewModel,
            telaAtual = telaAtual,
            onTelaSelecionada = { telaAtual = it }
        )

        TelaLider.DIRETRIZES -> DiretrizesLiderScreen(
            viewModel = viewModel,
            telaAtual = telaAtual,
            onTelaSelecionada = { telaAtual = it }
        )

        TelaLider.PROJETOS -> ProjetosLiderScreen(
            viewModel = viewModel,
            telaAtual = telaAtual,
            onTelaSelecionada = { telaAtual = it }
        )

        TelaLider.RELATORIOS -> RelatoriosLiderScreen(
            viewModel = viewModel,
            telaAtual = telaAtual,
            onTelaSelecionada = { telaAtual = it }
        )
    }
}