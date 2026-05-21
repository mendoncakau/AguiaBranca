package br.com.fiap.aguiabranca.ui.lider.relatorios

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import br.com.fiap.aguiabranca.ui.components.BarraImpacto
import br.com.fiap.aguiabranca.ui.components.BottomBarLider
import br.com.fiap.aguiabranca.ui.components.CardResumoFinanceiro
import br.com.fiap.aguiabranca.ui.components.TopoTelaLider
import br.com.fiap.aguiabranca.ui.theme.Branco
import br.com.fiap.aguiabranca.ui.theme.FundoTela
import br.com.fiap.aguiabranca.ui.theme.PretoTexto
import br.com.fiap.aguiabranca.ui.theme.VerdeAprovado
import br.com.fiap.aguiabranca.viewmodel.LiderViewModel

@Composable
fun RelatoriosLiderScreen(
    viewModel: LiderViewModel = LiderViewModel()
) {
    val projetos by viewModel.projetos.collectAsState()

    val investimentoTotal = projetos.sumOf { it.investimento }
    val retornoTotal = projetos.sumOf { it.retorno }
    val roiTotal = retornoTotal - investimentoTotal

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

            TopoTelaLider(
                titulo = "Relatório de impacto",
                mostrarFiltro = false
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Resumo financeiro",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = PretoTexto
                )

                AssistChip(
                    onClick = {},
                    label = { Text("Este mês") },
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowDown,
                            contentDescription = null
                        )
                    }
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            BoxWithConstraints {
                val alturaCard = if (maxWidth < 360.dp) 102.dp else 92.dp

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    CardResumoFinanceiro(
                        titulo = "Investimento\ntotal",
                        valor = "R$ 120 mil",
                        modifier = Modifier.weight(1f),
                        corValor = Color(0xFF4A148C)
                    )

                    CardResumoFinanceiro(
                        titulo = "Retorno total",
                        valor = "R$ 165 mil",
                        modifier = Modifier.weight(1f),
                        corValor = Color(0xFF4A148C)
                    )

                    CardResumoFinanceiro(
                        titulo = "ROI total",
                        valor = "R$ 45 mil",
                        modifier = Modifier.weight(1f),
                        corValor = VerdeAprovado
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.large,
                colors = CardDefaults.cardColors(containerColor = Branco),
                elevation = CardDefaults.cardElevation(3.dp)
            ) {
                Column(
                    modifier = Modifier.padding(18.dp)
                ) {
                    Text(
                        text = "Impacto por tipo",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = PretoTexto
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    BarraImpacto(
                        titulo = "Redução de custos",
                        valor = "R$ 60.000",
                        progresso = 0.85f,
                        cor = Color(0xFF0D47A1)
                    )

                    BarraImpacto(
                        titulo = "Ganho de tempo",
                        valor = "R$ 45.000",
                        progresso = 0.65f,
                        cor = Color(0xFFFFB300)
                    )

                    BarraImpacto(
                        titulo = "Melhoria do cliente",
                        valor = "R$ 30.000",
                        progresso = 0.45f,
                        cor = Color(0xFFD32F2F)
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}