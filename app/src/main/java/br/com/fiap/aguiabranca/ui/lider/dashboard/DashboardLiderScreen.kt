package br.com.fiap.aguiabranca.ui.lider.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import br.com.fiap.aguiabranca.ui.components.CardResumo
import br.com.fiap.aguiabranca.ui.theme.*
import br.com.fiap.aguiabranca.viewmodel.LiderViewModel

data class DadosDashboardMes(
    val ideiasEnviadas: Int,
    val ideiasAprovadas: Int,
    val projetosConcluidos: Int,
    val investimento: Int,
    val retorno: Int,
    val roi: Int
)

@Composable
fun DashboardLiderScreen(
    modifier: Modifier = Modifier,
    viewModel: LiderViewModel
) {

    var mesSelecionado by remember {
        mutableStateOf("Maio")
    }

    var menuAberto by remember {
        mutableStateOf(false)
    }

    val dadosPorMes = mapOf(

        "Janeiro" to DadosDashboardMes(
            22,
            8,
            3,
            40,
            58,
            18
        ),

        "Fevereiro" to DadosDashboardMes(
            31,
            12,
            5,
            55,
            82,
            27
        ),

        "Março" to DadosDashboardMes(
            44,
            19,
            8,
            75,
            110,
            35
        ),

        "Abril" to DadosDashboardMes(
            49,
            23,
            10,
            98,
            138,
            40
        ),

        "Maio" to DadosDashboardMes(
            56,
            28,
            12,
            120,
            165,
            45
        )
    )

    val dados =
        dadosPorMes[mesSelecionado]
            ?: dadosPorMes["Maio"]!!

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(FundoTela)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 18.dp)
    ) {

        Spacer(modifier = Modifier.height(20.dp))

        // HEADER

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column {

                Text(
                    text = "Dashboard",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    color = PretoTexto
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Visão estratégica da inovação",
                    style = MaterialTheme.typography.bodyMedium,
                    color = CinzaTexto
                )
            }

            Box {

                AssistChip(
                    onClick = {
                        menuAberto = true
                    },

                    label = {
                        Text(mesSelecionado)
                    },

                    trailingIcon = {

                        Icon(
                            imageVector =
                                Icons.Default.KeyboardArrowDown,

                            contentDescription = null
                        )
                    }
                )

                DropdownMenu(
                    expanded = menuAberto,

                    onDismissRequest = {
                        menuAberto = false
                    }
                ) {

                    dadosPorMes.keys.forEach { mes ->

                        DropdownMenuItem(

                            text = {
                                Text(mes)
                            },

                            onClick = {

                                mesSelecionado = mes
                                menuAberto = false
                            }
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // CARD PRINCIPAL

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(28.dp),
            colors = CardDefaults.cardColors(
                containerColor = AzulAguaBranca
            )
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
            ) {

                Text(
                    text = "ROI Total",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Branco.copy(alpha = 0.8f)
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "R$ ${dados.roi}.000",
                    style = MaterialTheme.typography.displaySmall,
                    fontWeight = FontWeight.Bold,
                    color = Branco
                )

                Spacer(modifier = Modifier.height(14.dp))

                LinearProgressIndicator(
                    progress = {
                        0.78f
                    },

                    modifier = Modifier
                        .fillMaxWidth()
                        .height(10.dp)
                        .clip(RoundedCornerShape(50.dp)),

                    color = Branco,

                    trackColor = Branco.copy(alpha = 0.25f)
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Crescimento de 18% em relação ao último mês",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Branco.copy(alpha = 0.9f)
                )
            }
        }

        Spacer(modifier = Modifier.height(28.dp))

        // MÉTRICAS

        Text(
            text = "Indicadores",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = PretoTexto
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            Box(modifier = Modifier.weight(1f)) {

                CardResumo(
                    titulo = "Ideias enviadas",
                    valor = dados.ideiasEnviadas,
                    corCard = AzulAguaBranca
                )
            }

            Box(modifier = Modifier.weight(1f)) {

                CardResumo(
                    titulo = "Aprovadas",
                    valor = dados.ideiasAprovadas,
                    corCard = VerdeStatus
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            Box(modifier = Modifier.weight(1f)) {

                CardResumo(
                    titulo = "Projetos",
                    valor = dados.projetosConcluidos,
                    corCard = RoxoLider
                )
            }

            Box(modifier = Modifier.weight(1f)) {

                CardResumo(
                    titulo = "Investimento",
                    valor = dados.investimento,
                    corCard = LaranjaStatus
                )
            }
        }

        Spacer(modifier = Modifier.height(28.dp))

        // IMPACTO FINANCEIRO

        Text(
            text = "Impacto financeiro",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = PretoTexto
        )

        Spacer(modifier = Modifier.height(16.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(
                containerColor = Branco
            )
        ) {

            Column(
                modifier = Modifier.padding(22.dp)
            ) {

                LinhaFinanceira(
                    titulo = "Investimento",
                    valor = "R$ ${dados.investimento}.000",
                    progresso = 0.55f,
                    cor = LaranjaStatus
                )

                Spacer(modifier = Modifier.height(20.dp))

                LinhaFinanceira(
                    titulo = "Retorno",
                    valor = "R$ ${dados.retorno}.000",
                    progresso = 0.82f,
                    cor = VerdeStatus
                )

                Spacer(modifier = Modifier.height(20.dp))

                LinhaFinanceira(
                    titulo = "ROI",
                    valor = "R$ ${dados.roi}.000",
                    progresso = 0.72f,
                    cor = RoxoLider
                )
            }
        }

        Spacer(modifier = Modifier.height(28.dp))

        // STATUS

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(
                containerColor = Branco
            )
        ) {

            Column(
                modifier = Modifier.padding(22.dp)
            ) {

                Text(
                    text = "Resumo estratégico",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = PretoTexto
                )

                Spacer(modifier = Modifier.height(14.dp))

                Text(
                    text =
                        "As iniciativas de inovação seguem em crescimento contínuo, com aumento na aprovação de ideias e maior retorno financeiro nos últimos meses.",

                    style = MaterialTheme.typography.bodyMedium,
                    color = CinzaTexto
                )
            }
        }

        Spacer(modifier = Modifier.height(120.dp))
    }
}

@Composable
fun LinhaFinanceira(
    titulo: String,
    valor: String,
    progresso: Float,
    cor: androidx.compose.ui.graphics.Color
) {

    Column {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement =
                Arrangement.SpaceBetween
        ) {

            Text(
                text = titulo,
                style = MaterialTheme.typography.bodyLarge,
                color = PretoTexto
            )

            Text(
                text = valor,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
                color = cor
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        LinearProgressIndicator(
            progress = {
                progresso
            },

            modifier = Modifier
                .fillMaxWidth()
                .height(10.dp)
                .clip(RoundedCornerShape(50.dp)),

            color = cor,

            trackColor = FundoTela
        )
    }
}