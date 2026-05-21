package br.com.fiap.aguiabranca.ui.lider.projetos

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import br.com.fiap.aguiabranca.ui.components.BottomBarLider
import br.com.fiap.aguiabranca.ui.components.CardProjetoLider
import br.com.fiap.aguiabranca.ui.components.TopoTelaLider
import br.com.fiap.aguiabranca.ui.theme.CinzaTexto
import br.com.fiap.aguiabranca.ui.theme.FundoTela
import br.com.fiap.aguiabranca.ui.theme.PretoTexto
import br.com.fiap.aguiabranca.viewmodel.LiderViewModel

@Composable
fun ProjetosLiderScreen(
    viewModel: LiderViewModel = LiderViewModel()
) {
    val projetos by viewModel.projetos.collectAsState()

    Scaffold(
        bottomBar = { BottomBarLider() },
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


            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = "Projetos (visão geral)",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = PretoTexto
                    )

                    Text(
                        text = "Acompanhe os projetos aprovados",
                        style = MaterialTheme.typography.bodyMedium,
                        color = CinzaTexto
                    )
                }

                AssistChip(
                    onClick = {},
                    label = { Text("Todos") },
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowDown,
                            contentDescription = null
                        )
                    }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            projetos.forEach { projeto ->
                CardProjetoLider(
                    nome = projeto.nome,
                    responsavel = projeto.responsavel,
                    prioridade = projeto.prioridade,
                    status = projeto.status,
                    investimento = projeto.investimento,
                    retorno = projeto.retorno
                )
            }

            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}