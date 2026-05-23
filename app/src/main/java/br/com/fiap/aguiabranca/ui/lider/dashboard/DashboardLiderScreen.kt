package br.com.fiap.aguiabranca.ui.lider.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import br.com.fiap.aguiabranca.ui.components.BottomBarLider
import br.com.fiap.aguiabranca.ui.components.CardMetricaLider
import br.com.fiap.aguiabranca.ui.components.LinhaGraficoMock
import br.com.fiap.aguiabranca.ui.lider.TelaLider
import br.com.fiap.aguiabranca.ui.theme.*
import br.com.fiap.aguiabranca.viewmodel.LiderViewModel

data class DadosDashboardMes(
    val ideiasEnviadas: Int,
    val ideiasAprovadas: Int,
    val projetosConcluidos: Int,
    val investimento: String,
    val retorno: String,
    val roi: String
)

@Composable
fun DashboardLiderScreen(
    viewModel: LiderViewModel,
    telaAtual: TelaLider,
    onTelaSelecionada: (TelaLider) -> Unit
) {
    var mesSelecionado by remember { mutableStateOf("Maio") }
    var menuAberto by remember { mutableStateOf(false) }

    val dadosPorMes = mapOf(
        "Janeiro" to DadosDashboardMes(22, 8, 3, "R$\n40.000", "R$\n58.000", "R$\n18.000"),
        "Fevereiro" to DadosDashboardMes(31, 12, 5, "R$\n55.000", "R$\n82.000", "R$\n27.000"),
        "Março" to DadosDashboardMes(44, 19, 8, "R$\n75.000", "R$\n110.000", "R$\n35.000"),
        "Abril" to DadosDashboardMes(49, 23, 10, "R$\n98.000", "R$\n138.000", "R$\n40.000"),
        "Maio" to DadosDashboardMes(56, 28, 12, "R$\n120.000", "R$\n165.000", "R$\n45.000"),
        "Junho" to DadosDashboardMes(61, 31, 14, "R$\n130.000", "R$\n180.000", "R$\n50.000"),
        "Julho" to DadosDashboardMes(58, 29, 13, "R$\n125.000", "R$\n172.000", "R$\n47.000"),
        "Agosto" to DadosDashboardMes(64, 33, 15, "R$\n140.000", "R$\n196.000", "R$\n56.000"),
        "Setembro" to DadosDashboardMes(69, 36, 16, "R$\n150.000", "R$\n215.000", "R$\n65.000"),
        "Outubro" to DadosDashboardMes(72, 39, 18, "R$\n165.000", "R$\n238.000", "R$\n73.000"),
        "Novembro" to DadosDashboardMes(76, 42, 20, "R$\n178.000", "R$\n260.000", "R$\n82.000"),
        "Dezembro" to DadosDashboardMes(80, 45, 22, "R$\n190.000", "R$\n285.000", "R$\n95.000")
    )

    val dados = dadosPorMes[mesSelecionado] ?: dadosPorMes["Maio"]!!

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
                .padding(horizontal = 14.dp)
                .verticalScroll(rememberScrollState())
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 22.dp, bottom = 22.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Dashboard estratégico",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = PretoTexto
                )

                Box {
                    AssistChip(
                        onClick = { menuAberto = true },
                        label = { Text(mesSelecionado) },
                        trailingIcon = {
                            Icon(
                                imageVector = Icons.Default.KeyboardArrowDown,
                                contentDescription = null
                            )
                        }
                    )

                    DropdownMenu(
                        expanded = menuAberto,
                        onDismissRequest = { menuAberto = false }
                    ) {
                        dadosPorMes.keys.forEach { mes ->
                            DropdownMenuItem(
                                text = { Text(mes) },
                                onClick = {
                                    mesSelecionado = mes
                                    menuAberto = false
                                }
                            )
                        }
                    }
                }
            }

            BoxWithConstraints {
                val alturaCard = if (maxWidth < 360.dp) 120.dp else 112.dp

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    CardMetricaLider("Ideias\nenviadas", dados.ideiasEnviadas.toString(), altura = alturaCard, modifier = Modifier.weight(1f))
                    CardMetricaLider("Aprovadas", dados.ideiasAprovadas.toString(), altura = alturaCard, modifier = Modifier.weight(1f))
                    CardMetricaLider("Projetos\nconcluídos", dados.projetosConcluidos.toString(), altura = alturaCard, modifier = Modifier.weight(1f))
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Impacto financeiro",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = PretoTexto
            )

            Spacer(modifier = Modifier.height(14.dp))

            BoxWithConstraints {
                val alturaCard = if (maxWidth < 360.dp) 120.dp else 112.dp

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    CardMetricaLider("Investimento", dados.investimento, altura = alturaCard, modifier = Modifier.weight(1f))
                    CardMetricaLider("Retorno total", dados.retorno, altura = alturaCard, modifier = Modifier.weight(1f))
                    CardMetricaLider("ROI total", dados.roi, corValor = VerdeAprovado, altura = alturaCard, modifier = Modifier.weight(1f))
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Branco),
                elevation = CardDefaults.cardElevation(3.dp)
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Text(
                        text = "Evolução do ROI",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = PretoTexto
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp),
                        horizontalArrangement = Arrangement.SpaceAround,
                        verticalAlignment = Alignment.Bottom
                    ) {
                        LinhaGraficoMock(0.40f, "Jan")
                        LinhaGraficoMock(0.55f, "Fev")
                        LinhaGraficoMock(0.70f, "Mar")
                        LinhaGraficoMock(0.82f, "Abr")
                        LinhaGraficoMock(1.00f, "Mai")
                    }
                }
            }

            Spacer(modifier = Modifier.height(18.dp))
        }
    }
}