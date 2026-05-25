package br.com.fiap.aguiabranca.ui.lider.relatorios

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import br.com.fiap.aguiabranca.ui.components.*
import br.com.fiap.aguiabranca.ui.lider.TelaLider
import br.com.fiap.aguiabranca.ui.theme.*
import br.com.fiap.aguiabranca.viewmodel.LiderViewModel
import kotlinx.coroutines.launch
import androidx.compose.foundation.clickable

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
    viewModel: LiderViewModel,
    telaAtual: TelaLider,
    onTelaSelecionada: (TelaLider) -> Unit
) {
    var tipoRelatorio by remember { mutableStateOf("Impacto") }
    var menuRelatorioAberto by remember { mutableStateOf(false) }

    var mesSelecionado by remember { mutableStateOf("Maio") }
    var menuMesAberto by remember { mutableStateOf(false) }

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    val meses = listOf(
        "Janeiro", "Fevereiro", "Março", "Abril",
        "Maio", "Junho", "Julho", "Agosto",
        "Setembro", "Outubro", "Novembro", "Dezembro"
    )

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
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
            TopoTelaLider(
                titulo = "Relatórios",
                mostrarFiltro = false
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Box {
                    AssistChip(
                        onClick = { menuRelatorioAberto = true },
                        label = { Text("Relatório de $tipoRelatorio") },
                        trailingIcon = {
                            Icon(
                                imageVector = Icons.Default.KeyboardArrowDown,
                                contentDescription = null
                            )
                        }
                    )

                    DropdownMenu(
                        expanded = menuRelatorioAberto,
                        onDismissRequest = { menuRelatorioAberto = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text("Relatório de impacto") },
                            onClick = {
                                tipoRelatorio = "Impacto"
                                menuRelatorioAberto = false
                            }
                        )

                        DropdownMenuItem(
                            text = { Text("Relatório de ideias") },
                            onClick = {
                                tipoRelatorio = "Ideias"
                                menuRelatorioAberto = false
                            }
                        )
                    }
                }

                Box {
                    AssistChip(
                        onClick = { menuMesAberto = true },
                        label = { Text(mesSelecionado) },
                        trailingIcon = {
                            Icon(
                                imageVector = Icons.Default.KeyboardArrowDown,
                                contentDescription = null
                            )
                        }
                    )

                    DropdownMenu(
                        expanded = menuMesAberto,
                        onDismissRequest = { menuMesAberto = false }
                    ) {
                        meses.forEach { mes ->
                            DropdownMenuItem(
                                text = { Text(mes) },
                                onClick = {
                                    mesSelecionado = mes
                                    menuMesAberto = false
                                }
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

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

            Spacer(modifier = Modifier.height(18.dp))

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

            Spacer(modifier = Modifier.height(20.dp))
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
        "Janeiro" to Triple("R$ 40 mil", "R$ 58 mil", "R$ 18 mil"),
        "Fevereiro" to Triple("R$ 55 mil", "R$ 82 mil", "R$ 27 mil"),
        "Março" to Triple("R$ 75 mil", "R$ 110 mil", "R$ 35 mil"),
        "Abril" to Triple("R$ 98 mil", "R$ 138 mil", "R$ 40 mil"),
        "Maio" to Triple("R$ 120 mil", "R$ 165 mil", "R$ 45 mil"),
        "Junho" to Triple("R$ 130 mil", "R$ 180 mil", "R$ 50 mil"),
        "Julho" to Triple("R$ 125 mil", "R$ 172 mil", "R$ 47 mil"),
        "Agosto" to Triple("R$ 140 mil", "R$ 196 mil", "R$ 56 mil"),
        "Setembro" to Triple("R$ 150 mil", "R$ 215 mil", "R$ 65 mil"),
        "Outubro" to Triple("R$ 165 mil", "R$ 238 mil", "R$ 73 mil"),
        "Novembro" to Triple("R$ 178 mil", "R$ 260 mil", "R$ 82 mil"),
        "Dezembro" to Triple("R$ 190 mil", "R$ 285 mil", "R$ 95 mil")
    )

    val dados = dadosPorMes[mesSelecionado] ?: dadosPorMes["Maio"]!!

    Text(
        text = "Resumo financeiro",
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.Bold,
        color = PretoTexto
    )

    Text(
        text = "Dados referentes a $mesSelecionado",
        style = MaterialTheme.typography.bodyMedium,
        color = CinzaTexto
    )

    Spacer(modifier = Modifier.height(14.dp))

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        CardResumoFinanceiro(
            titulo = "Investimento\ntotal",
            valor = dados.first,
            modifier = Modifier.weight(1f),
            corValor = RoxoLider
        )

        CardResumoFinanceiro(
            titulo = "Retorno total",
            valor = dados.second,
            modifier = Modifier.weight(1f),
            corValor = RoxoLider
        )

        CardResumoFinanceiro(
            titulo = "ROI total",
            valor = dados.third,
            modifier = Modifier.weight(1f),
            corValor = VerdeAprovado
        )
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
                cor = AzulAguaBranca
            )

            BarraImpacto(
                titulo = "Ganho de tempo",
                valor = "R$ 45.000",
                progresso = 0.65f,
                cor = AmareloDestaque
            )

            BarraImpacto(
                titulo = "Melhoria do cliente",
                valor = "R$ 30.000",
                progresso = 0.45f,
                cor = VermelhoStatus
            )
        }
    }
}

@Composable
fun RelatorioIdeiasContent(
    mesSelecionado: String
) {
    var ideiaSelecionada by remember { mutableStateOf<IdeiaRanking?>(null) }

    val ideias = listOf(
        IdeiaRanking(
            posicao = 1,
            titulo = "Redução de papel nas garagens",
            valor = "R$ 45.000/mês",
            categoria = "Sustentabilidade",
            impacto = "Alto",
            economiaMensal = "R$ 45.000"
        ),
        IdeiaRanking(
            posicao = 2,
            titulo = "Otimização no processo de manutenção",
            valor = "R$ 12.000/mês",
            categoria = "Processos",
            impacto = "Médio",
            economiaMensal = "R$ 12.000"
        ),
        IdeiaRanking(
            posicao = 3,
            titulo = "Mais sinalização nas áreas operacionais",
            valor = "R$ 8.000/mês",
            categoria = "Segurança",
            impacto = "Médio",
            economiaMensal = "R$ 8.000"
        )
    )

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
                text = "Ideias por categoria",
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

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                GraficoRoscaIdeias(
                    modifier = Modifier.size(120.dp)
                )

                Spacer(modifier = Modifier.width(20.dp))

                Column {
                    LegendaIdeia("Meio ambiente", "12 (30%)", AzulAguaBranca)
                    LegendaIdeia("Processos", "10 (25%)", VerdeStatus)
                    LegendaIdeia("Tecnologia", "8 (20%)", AmareloDestaque)
                    LegendaIdeia("Clientes", "6 (15%)", VermelhoStatus)
                    LegendaIdeia("Outros", "4 (10%)", AzulEscuro)
                }
            }
        }
    }

    Spacer(modifier = Modifier.height(22.dp))

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
                text = "Top ideias aprovadas",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = PretoTexto
            )

            Spacer(modifier = Modifier.height(14.dp))

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
                        fontWeight = FontWeight.Bold,
                        color = PretoTexto
                    )
                    Text(
                        text = ideiaSelecionada!!.categoria,
                        color = CinzaTexto
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "Impacto",
                        fontWeight = FontWeight.Bold,
                        color = PretoTexto
                    )
                    Text(
                        text = ideiaSelecionada!!.impacto,
                        color = CinzaTexto
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "Economia mensal",
                        fontWeight = FontWeight.Bold,
                        color = PretoTexto
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
fun GraficoRoscaIdeias(modifier: Modifier = Modifier) {
    val cores = listOf(
        AzulAguaBranca,
        VerdeStatus,
        AmareloDestaque,
        VermelhoStatus,
        AzulEscuro
    )

    Canvas(modifier = modifier) {
        var anguloInicial = -90f
        val valores = listOf(30f, 25f, 20f, 15f, 10f)

        valores.forEachIndexed { index, valor ->
            drawArc(
                color = cores[index],
                startAngle = anguloInicial,
                sweepAngle = valor * 3.6f,
                useCenter = false,
                style = Stroke(width = 22.dp.toPx())
            )

            anguloInicial += valor * 3.6f
        }
    }
}

@Composable
fun LegendaIdeia(
    titulo: String,
    valor: String,
    cor: Color
) {
    Row(
        modifier = Modifier.padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Surface(
            modifier = Modifier.size(10.dp),
            shape = CircleShape,
            color = cor
        ) {}

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = titulo,
            style = MaterialTheme.typography.bodySmall,
            color = PretoTexto,
            modifier = Modifier.weight(1f)
        )

        Text(
            text = valor,
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.Bold,
            color = CinzaTexto
        )
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
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = FundoTela),
        elevation = CardDefaults.cardElevation(0.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "$posicao",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = RoxoLider,
                modifier = Modifier.width(26.dp)
            )

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = titulo,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold,
                    color = PretoTexto
                )

                Text(
                    text = "Toque para ver detalhes",
                    style = MaterialTheme.typography.bodySmall,
                    color = CinzaTexto
                )
            }

            Text(
                text = valor,
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.Bold,
                color = VerdeAprovado
            )
        }
    }
}