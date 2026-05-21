package br.com.fiap.aguiabranca.ui.lider.dashboard

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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import br.com.fiap.aguiabranca.ui.components.BottomBarLider
import br.com.fiap.aguiabranca.ui.components.CardMetricaLider
import br.com.fiap.aguiabranca.ui.components.LinhaGraficoMock
import br.com.fiap.aguiabranca.ui.components.TopoTelaLider
import br.com.fiap.aguiabranca.ui.theme.Branco
import br.com.fiap.aguiabranca.ui.theme.FundoTela
import br.com.fiap.aguiabranca.ui.theme.PretoTexto
import br.com.fiap.aguiabranca.ui.theme.VerdeAprovado
import br.com.fiap.aguiabranca.viewmodel.LiderViewModel

@Composable
fun DashboardLiderScreen(viewModel: LiderViewModel = LiderViewModel()) {

    val totalIdeias by viewModel.totalIdeias.collectAsState()
    val ideiasAprovadas by viewModel.ideiasAprovadas.collectAsState()
    val projetosConcluidos by viewModel.projetosConcluidos.collectAsState()

    Scaffold(
        bottomBar = { BottomBarLider() },
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

            TopoTelaLider("Dashboard estratégico")

            BoxWithConstraints {
                val alturaCard = if (maxWidth < 360.dp) 120.dp else 112.dp

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    CardMetricaLider(
                        titulo = "Ideias\nenviadas",
                        valor = totalIdeias.toString(),
                        altura = alturaCard,
                        modifier = Modifier.weight(1f)
                    )

                    CardMetricaLider(
                        titulo = "Aprovadas",
                        valor = ideiasAprovadas.toString(),
                        altura = alturaCard,
                        modifier = Modifier.weight(1f)
                    )

                    CardMetricaLider(
                        titulo = "Projetos\nconcluídos",
                        valor = projetosConcluidos.toString(),
                        altura = alturaCard,
                        modifier = Modifier.weight(1f)
                    )
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
                    CardMetricaLider(
                        titulo = "Investimento",
                        valor = "R$\n120.000",
                        altura = alturaCard,
                        modifier = Modifier.weight(1f)
                    )

                    CardMetricaLider(
                        titulo = "Retorno total",
                        valor = "R$\n165.000",
                        altura = alturaCard,
                        modifier = Modifier.weight(1f)
                    )

                    CardMetricaLider(
                        titulo = "ROI total",
                        valor = "R$\n45.000",
                        corValor = VerdeAprovado,
                        altura = alturaCard,
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Branco),
                elevation = CardDefaults.cardElevation(3.dp)
            ) {
                Column(
                    modifier = Modifier.padding(18.dp)
                ) {
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