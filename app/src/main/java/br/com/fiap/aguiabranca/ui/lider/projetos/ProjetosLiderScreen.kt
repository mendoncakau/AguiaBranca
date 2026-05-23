package br.com.fiap.aguiabranca.ui.lider.projetos

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import br.com.fiap.aguiabranca.ui.components.BottomBarLider
import br.com.fiap.aguiabranca.ui.components.CardProjetoLider
import br.com.fiap.aguiabranca.ui.lider.TelaLider
import br.com.fiap.aguiabranca.ui.theme.CinzaTexto
import br.com.fiap.aguiabranca.ui.theme.FundoTela
import br.com.fiap.aguiabranca.ui.theme.PretoTexto
import br.com.fiap.aguiabranca.viewmodel.LiderViewModel

@Composable
fun ProjetosLiderScreen(
    viewModel: LiderViewModel,
    telaAtual: TelaLider,
    onTelaSelecionada: (TelaLider) -> Unit
) {
    val projetos by viewModel.projetos.collectAsState()
    var pesquisa by remember { mutableStateOf("") }

    val projetosFiltrados = if (pesquisa.isBlank()) {
        projetos
    } else {
        projetos.filter {
            it.nome.contains(pesquisa, ignoreCase = true)
        }
    }

    Scaffold(
        bottomBar = {
            BottomBarLider(
                telaAtual = telaAtual,
                onTelaSelecionada = onTelaSelecionada
            )
        },
        containerColor = FundoTela
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(FundoTela)
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState())
        ) {

            Text(
                text = "Projetos (visão geral)",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = PretoTexto,
                modifier = Modifier.padding(top = 22.dp)
            )

            Text(
                text = "Acompanhe os projetos aprovados",
                style = MaterialTheme.typography.bodyMedium,
                color = CinzaTexto
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = pesquisa,
                onValueChange = { pesquisa = it },
                label = { Text("Pesquisar projeto") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null
                    )
                },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            if (projetosFiltrados.isEmpty()) {
                Text(
                    text = "Nenhum projeto encontrado.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = CinzaTexto
                )
            } else {
                projetosFiltrados.forEach { projeto ->
                    CardProjetoLider(
                        nome = projeto.nome,
                        responsavel = projeto.responsavel,
                        prioridade = projeto.prioridade,
                        status = projeto.status,
                        investimento = projeto.investimento,
                        retorno = projeto.retorno
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}