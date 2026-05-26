package br.com.fiap.aguiabranca.ui.lider.relatorios

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import br.com.fiap.aguiabranca.ui.theme.*
import br.com.fiap.aguiabranca.viewmodel.LiderViewModel
import kotlinx.coroutines.launch
import androidx.compose.foundation.shape.RoundedCornerShape

data class IdeiaRanking(
    val posicao: Int,
    val titulo: String,
    val valor: String,
    val categoria: String,
    val impacto: String,
    val economiaMensal: String
)

@Composable
fun RelatoriosLiderScreen(
    viewModel: LiderViewModel
) {

    var tipoRelatorio by remember {
        mutableStateOf("Impacto")
    }

    var menuRelatorioAberto by remember {
        mutableStateOf(false)
    }

    var mesSelecionado by remember {
        mutableStateOf("Maio")
    }

    var menuMesAberto by remember {
        mutableStateOf(false)
    }

    val snackbarHostState = remember {
        SnackbarHostState()
    }

    val scope = rememberCoroutineScope()

    val meses = listOf(
        "Janeiro",
        "Fevereiro",
        "Março",
        "Abril",
        "Maio",
        "Junho",
        "Julho",
        "Agosto",
        "Setembro",
        "Outubro",
        "Novembro",
        "Dezembro"
    )

    Scaffold(

        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
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
                text = "Relatórios",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = PretoTexto,
                modifier = Modifier.padding(top = 22.dp)
            )

            Text(
                text = "Visualize métricas estratégicas",
                style = MaterialTheme.typography.bodyMedium,
                color = CinzaTexto
            )

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Box {

                    AssistChip(

                        onClick = {
                            menuRelatorioAberto = true
                        },

                        label = {
                            Text("Relatório de $tipoRelatorio")
                        },

                        trailingIcon = {

                            Icon(
                                imageVector = Icons.Default.KeyboardArrowDown,
                                contentDescription = null
                            )
                        }
                    )

                    DropdownMenu(
                        expanded = menuRelatorioAberto,

                        onDismissRequest = {
                            menuRelatorioAberto = false
                        }
                    ) {

                        DropdownMenuItem(

                            text = {
                                Text("Relatório de impacto")
                            },

                            onClick = {

                                tipoRelatorio = "Impacto"
                                menuRelatorioAberto = false
                            }
                        )

                        DropdownMenuItem(

                            text = {
                                Text("Relatório de ideias")
                            },

                            onClick = {

                                tipoRelatorio = "Ideias"
                                menuRelatorioAberto = false
                            }
                        )
                    }
                }

                Box {

                    AssistChip(

                        onClick = {
                            menuMesAberto = true
                        },

                        label = {
                            Text(mesSelecionado)
                        },

                        trailingIcon = {

                            Icon(
                                imageVector = Icons.Default.KeyboardArrowDown,
                                contentDescription = null
                            )
                        }
                    )

                    DropdownMenu(
                        expanded = menuMesAberto,

                        onDismissRequest = {
                            menuMesAberto = false
                        }
                    ) {

                        meses.forEach { mes ->

                            DropdownMenuItem(

                                text = {
                                    Text(mes)
                                },

                                onClick = {

                                    mesSelecionado = mes
                                    menuMesAberto = false
                                }
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            Button(

                onClick = {

                    scope.launch {

                        snackbarHostState.showSnackbar(
                            "✓ Relatório exportado com sucesso"
                        )
                    }
                },

                modifier = Modifier.fillMaxWidth(),

                colors = ButtonDefaults.buttonColors(
                    containerColor = RoxoLider
                )
            ) {

                Text("Exportar PDF")
            }

            Spacer(modifier = Modifier.height(20.dp))

            if (tipoRelatorio == "Impacto") {

                RelatorioImpactoContent(
                    viewModel = viewModel,
                    mesSelecionado = mesSelecionado
                )

            } else {

                RelatorioIdeiasContent(
                    mesSelecionado = mesSelecionado
                )
            }

            Spacer(modifier = Modifier.height(120.dp))
        }
    }
}

@Composable
fun RelatorioImpactoContent(
    viewModel: LiderViewModel,
    mesSelecionado: String
) {

    val projetos by viewModel.projetos.collectAsState()

    val dadosPorMes = mapOf(

        "Janeiro" to Triple(
            "R$ 40 mil",
            "R$ 58 mil",
            "R$ 18 mil"
        ),

        "Fevereiro" to Triple(
            "R$ 55 mil",
            "R$ 82 mil",
            "R$ 27 mil"
        ),

        "Março" to Triple(
            "R$ 75 mil",
            "R$ 110 mil",
            "R$ 35 mil"
        ),

        "Abril" to Triple(
            "R$ 98 mil",
            "R$ 138 mil",
            "R$ 40 mil"
        ),

        "Maio" to Triple(
            "R$ 120 mil",
            "R$ 165 mil",
            "R$ 45 mil"
        )
    )

    val dados =
        dadosPorMes[mesSelecionado]
            ?: dadosPorMes["Maio"]!!

    Column {

        Text(
            text = "Resumo financeiro",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            color = PretoTexto
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "Indicadores estratégicos de $mesSelecionado",
            style = MaterialTheme.typography.bodyMedium,
            color = CinzaTexto
        )

        Spacer(modifier = Modifier.height(20.dp))


        Column(
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {

            CardFinanceiroImpacto(
                titulo = "Investimento total",
                valor = dados.first,
                descricao = "Valor aplicado nos projetos",
                cor = AzulAguaBranca
            )

            CardFinanceiroImpacto(
                titulo = "Retorno total",
                valor = dados.second,
                descricao = "Resultado financeiro obtido",
                cor = Color(0xFF16A34A)
            )

            CardFinanceiroImpacto(
                titulo = "ROI total",
                valor = dados.third,
                descricao = "Lucro gerado pelas iniciativas",
                cor = Color(0xFF2563EB)
            )
        }

        Spacer(modifier = Modifier.height(28.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),

            shape = RoundedCornerShape(24.dp),

            colors = CardDefaults.cardColors(
                containerColor = Branco
            ),

            elevation = CardDefaults.cardElevation(
                defaultElevation = 4.dp
            )
        ) {

            Column(
                modifier = Modifier.padding(20.dp)
            ) {

                Text(
                    text = "Análise do período",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = PretoTexto
                )

                Spacer(modifier = Modifier.height(14.dp))

                IndicadorLinha(
                    titulo = "Projetos ativos",
                    valor = "${projetos.size}",
                    cor = AzulAguaBranca
                )

                Spacer(modifier = Modifier.height(12.dp))

                IndicadorLinha(
                    titulo = "Projetos concluídos",
                    valor = "12",
                    cor = VerdeAprovado
                )

                Spacer(modifier = Modifier.height(12.dp))

                IndicadorLinha(
                    titulo = "Eficiência operacional",
                    valor = "92%",
                    cor = Color(0xFF2563EB)
                )

                Spacer(modifier = Modifier.height(18.dp))

                LinearProgressIndicator(
                    progress = 0.92f,

                    modifier = Modifier
                        .fillMaxWidth()
                        .height(10.dp),

                    color = AzulAguaBranca,

                    trackColor = Color(0xFFE5E7EB)
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Desempenho acima da meta estratégica.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = CinzaTexto
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
fun CardFinanceiroImpacto(
    titulo: String,
    valor: String,
    descricao: String,
    cor: Color
) {

    Card(
        modifier = Modifier.fillMaxWidth(),

        shape = RoundedCornerShape(24.dp),

        colors = CardDefaults.cardColors(
            containerColor = Branco
        ),

        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),

            horizontalArrangement = Arrangement.SpaceBetween,

            verticalAlignment = Alignment.CenterVertically
        ) {

            Column(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = titulo,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = PretoTexto
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = descricao,
                    style = MaterialTheme.typography.bodyMedium,
                    color = CinzaTexto
                )
            }

            Surface(
                shape = RoundedCornerShape(18.dp),
                color = cor.copy(alpha = 0.12f)
            ) {

                Text(
                    text = valor,

                    modifier = Modifier.padding(
                        horizontal = 18.dp,
                        vertical = 14.dp
                    ),

                    color = cor,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}

@Composable
fun IndicadorLinha(
    titulo: String,
    valor: String,
    cor: Color
) {

    Row(
        modifier = Modifier.fillMaxWidth(),

        horizontalArrangement = Arrangement.SpaceBetween,

        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = titulo,
            style = MaterialTheme.typography.bodyLarge,
            color = PretoTexto
        )

        Text(
            text = valor,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = cor
        )
    }
}
@Composable
fun RelatorioIdeiasContent(
    mesSelecionado: String
) {

    var ideiaSelecionada by remember {
        mutableStateOf<IdeiaRanking?>(null)
    }

    val ideias = listOf(

        IdeiaRanking(
            1,
            "Redução de papel nas garagens",
            "R$ 45.000/mês",
            "Sustentabilidade",
            "Alto",
            "R$ 45.000"
        ),

        IdeiaRanking(
            2,
            "Otimização no processo de manutenção",
            "R$ 12.000/mês",
            "Processos",
            "Médio",
            "R$ 12.000"
        ),

        IdeiaRanking(
            3,
            "Mais sinalização nas áreas operacionais",
            "R$ 8.000/mês",
            "Segurança",
            "Médio",
            "R$ 8.000"
        )
    )

    Card(
        modifier = Modifier.fillMaxWidth(),

        colors = CardDefaults.cardColors(
            containerColor = Branco
        )
    ) {

        Column(
            modifier = Modifier.padding(18.dp)
        ) {

            Text(
                text = "Top ideias aprovadas",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = PretoTexto
            )

            Text(
                text = "Dados referentes a $mesSelecionado",
                style = MaterialTheme.typography.bodyMedium,
                color = CinzaTexto
            )

            Spacer(modifier = Modifier.height(18.dp))

            ideias.forEach { ideia ->

                ItemIdeiaAprovada(

                    posicao = ideia.posicao,

                    titulo = ideia.titulo,

                    valor = ideia.valor,

                    onClick = {

                        ideiaSelecionada = ideia
                    }
                )
            }
        }
    }

    if (ideiaSelecionada != null) {

        AlertDialog(

            onDismissRequest = {
                ideiaSelecionada = null
            },

            title = {
                Text(ideiaSelecionada!!.titulo)
            },

            text = {

                Column {

                    Text(
                        text = "Categoria",
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = ideiaSelecionada!!.categoria
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "Impacto",
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = ideiaSelecionada!!.impacto
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "Economia mensal",
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = ideiaSelecionada!!.economiaMensal,
                        color = VerdeAprovado,
                        fontWeight = FontWeight.Bold
                    )
                }
            },

            confirmButton = {

                TextButton(
                    onClick = {
                        ideiaSelecionada = null
                    }
                ) {

                    Text("Fechar")
                }
            }
        )
    }
}

@Composable
fun CardResumoFinanceiro(
    titulo: String,
    valor: String,
    modifier: Modifier = Modifier,
    corValor: Color
) {

    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = Branco
        )
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Text(
                text = titulo,
                color = CinzaTexto
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = valor,
                fontWeight = FontWeight.Bold,
                color = corValor
            )
        }
    }
}

@Composable
fun ItemIdeiaAprovada(
    posicao: Int,
    titulo: String,
    valor: String,
    onClick: () -> Unit = {}
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .clickable {
                onClick()
            },

        colors = CardDefaults.cardColors(
            containerColor = FundoTela
        )
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),

            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = "$posicao",
                fontWeight = FontWeight.Bold,
                color = RoxoLider,
                modifier = Modifier.width(26.dp)
            )

            Column(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = titulo,
                    fontWeight = FontWeight.Bold,
                    color = PretoTexto
                )

                Text(
                    text = "Toque para ver detalhes",
                    color = CinzaTexto
                )
            }

            Text(
                text = valor,
                fontWeight = FontWeight.Bold,
                color = VerdeAprovado
            )
        }
    }
}