package br.com.fiap.aguiabranca.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.ui.unit.Dp
import br.com.fiap.aguiabranca.ui.theme.*
import br.com.fiap.aguiabranca.ui.lider.TelaLider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

val RoxoLider = AzulAguaBranca

@Composable
fun CardMetricaLider(
    titulo: String,
    valor: String,
    corValor: Color = RoxoLider,
    altura: Dp = 112.dp,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.height(altura),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = Branco),
        elevation = CardDefaults.cardElevation(3.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 12.dp, vertical = 14.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = titulo,
                color = CinzaTexto,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Text(
                text = valor,
                color = corValor,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                maxLines = 2,
                overflow = TextOverflow.Clip
            )
        }
    }
}

@Composable
fun TopoTelaLider(
    titulo: String,
    mostrarFiltro: Boolean = true,
    textoFiltro: String = "Este mês",
    onFiltroClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 22.dp, bottom = 22.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = titulo,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = PretoTexto
        )

        if (mostrarFiltro) {
            Surface(
                onClick = onFiltroClick,
                shape = RoundedCornerShape(24.dp),
                color = Color(0xFFEFF1F5)
            ) {
                Text(
                    text = textoFiltro,
                    modifier = Modifier.padding(
                        horizontal = 16.dp,
                        vertical = 10.dp
                    ),
                    style = MaterialTheme.typography.bodyMedium,
                    color = CinzaTexto
                )
            }
        }
    }
}

@Composable
fun BottomBarLider(
    telaAtual: TelaLider,
    onTelaSelecionada: (TelaLider) -> Unit
) {
    NavigationBar(
        containerColor = Branco,
        tonalElevation = 8.dp
    ) {
        NavigationBarItem(
            selected = telaAtual == TelaLider.DASHBOARD,
            onClick = { onTelaSelecionada(TelaLider.DASHBOARD) },
            icon = { Icon(Icons.Default.Home, contentDescription = null) },
            label = { Text("Dashboard", maxLines = 1) },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = RoxoLider,
                selectedTextColor = RoxoLider,
                indicatorColor = Color(0xFFD6E9FF)
            )
        )

        NavigationBarItem(
            selected = telaAtual == TelaLider.DIRETRIZES,
            onClick = { onTelaSelecionada(TelaLider.DIRETRIZES) },
            icon = { Icon(Icons.Default.Flag, contentDescription = null) },
            label = { Text("Estratégia", maxLines = 1) },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = RoxoLider,
                selectedTextColor = RoxoLider,
                indicatorColor = Color(0xFFD6E9FF)
            )
        )

        NavigationBarItem(
            selected = telaAtual == TelaLider.PROJETOS,
            onClick = { onTelaSelecionada(TelaLider.PROJETOS) },
            icon = { Icon(Icons.Default.Folder, contentDescription = null) },
            label = { Text("Projetos", maxLines = 1) },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = RoxoLider,
                selectedTextColor = RoxoLider,
                indicatorColor = Color(0xFFD6E9FF)
            )
        )

        NavigationBarItem(
            selected = telaAtual == TelaLider.RELATORIOS,
            onClick = { onTelaSelecionada(TelaLider.RELATORIOS) },
            icon = { Icon(Icons.Default.BarChart, contentDescription = null) },
            label = { Text("Relatórios", maxLines = 1) },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = RoxoLider,
                selectedTextColor = RoxoLider,
                indicatorColor = Color(0xFFD6E9FF)
            )
        )
    }
}

@Composable
fun LinhaGraficoMock(valor: Float, mes: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .width(26.dp)
                .height((valor * 130).dp)
                .clip(RoundedCornerShape(18.dp))
                .background(RoxoLider)
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = mes,
            style = MaterialTheme.typography.bodyMedium,
            color = CinzaTexto
        )
    }
}

@Composable
fun CardDiretriz(
    titulo: String,
    descricao: String,
    categoria: String,
    prioridade: String,
    onEditar: () -> Unit = {},
    onExcluir: () -> Unit = {}
) {
    val corCategoria = when (categoria) {
        "Sustentabilidade" -> VerdeStatus
        "Clientes" -> LaranjaStatus
        else -> AzulAguaBranca
    }

    val corPrioridade = when (prioridade) {
        "Alta prioridade" -> VermelhoStatus
        "Média prioridade" -> LaranjaStatus
        else -> VerdeStatus
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = Branco),
        elevation = CardDefaults.cardElevation(3.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = titulo,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = PretoTexto
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = descricao,
                        style = MaterialTheme.typography.bodyMedium,
                        color = CinzaTexto
                    )
                }

                Row {
                    IconButton(onClick = onEditar) {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = "Editar",
                            tint = AzulAguaBranca
                        )
                    }

                    IconButton(onClick = onExcluir) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Excluir",
                            tint = VermelhoStatus
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                AssistChip(
                    onClick = {},
                    label = { Text(categoria) },
                    colors = AssistChipDefaults.assistChipColors(
                        labelColor = corCategoria
                    )
                )

                AssistChip(
                    onClick = {},
                    label = { Text(prioridade) },
                    colors = AssistChipDefaults.assistChipColors(
                        labelColor = corPrioridade
                    )
                )
            }
        }
    }
}

@Composable
fun CardProjetoLider(
    nome: String,
    responsavel: String,
    prioridade: String,
    status: String,
    investimento: Double,
    retorno: Double,
    descricao: String = "Projeto criado a partir de uma ideia aprovada, com foco em gerar impacto operacional e financeiro.",
    statusDetalhado: String = "Projeto em acompanhamento pelo gestor responsável."
) {
    var expandido by remember {
        mutableStateOf(false)
    }

    val corPrioridade = when (prioridade) {
        "Alta" -> VermelhoStatus
        "Média" -> LaranjaStatus
        else -> VerdeStatus
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = Branco),
        elevation = CardDefaults.cardElevation(3.dp),
        onClick = {
            expandido = !expandido
        }
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = nome,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = PretoTexto
                    )

                    Text(
                        text = responsavel,
                        style = MaterialTheme.typography.bodyMedium,
                        color = CinzaTexto
                    )
                }

                Text(
                    text = prioridade,
                    color = corPrioridade,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Status: $status",
                    color = CinzaTexto,
                    style = MaterialTheme.typography.bodyMedium
                )

                Text(
                    text = if (expandido) "Ocultar" else "Ver detalhes",
                    color = RoxoLider,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            if (expandido) {
                Spacer(modifier = Modifier.height(14.dp))

                Divider()

                Spacer(modifier = Modifier.height(14.dp))

                Text(
                    text = "Descrição",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = PretoTexto
                )

                Text(
                    text = descricao,
                    style = MaterialTheme.typography.bodyMedium,
                    color = CinzaTexto
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Investimento",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = PretoTexto
                )

                Text(
                    text = "R$ %.0f".format(investimento),
                    style = MaterialTheme.typography.bodyMedium,
                    color = CinzaTexto
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Retorno esperado",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = PretoTexto
                )

                Text(
                    text = "R$ %.0f".format(retorno),
                    style = MaterialTheme.typography.bodyMedium,
                    color = VerdeStatus,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Status detalhado",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = PretoTexto
                )

                Text(
                    text = statusDetalhado,
                    style = MaterialTheme.typography.bodyMedium,
                    color = CinzaTexto
                )
            }
        }
    }
}

@Composable
fun BarraImpacto(
    titulo: String,
    valor: String,
    progresso: Float,
    cor: Color
) {
    Column(
        modifier = Modifier.padding(vertical = 8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = titulo,
                style = MaterialTheme.typography.bodyMedium,
                color = PretoTexto
            )

            Text(
                text = valor,
                style = MaterialTheme.typography.bodyMedium,
                color = CinzaTexto
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        LinearProgressIndicator(
            progress = progresso,
            modifier = Modifier
                .fillMaxWidth()
                .height(10.dp)
                .clip(RoundedCornerShape(20.dp)),
            color = cor,
            trackColor = Color(0xFFE6EAF0)
        )
    }
}

@Composable
fun CardResumoFinanceiro(
    titulo: String,
    valor: String,
    corValor: Color = RoxoLider,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.height(92.dp),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = Branco),
        elevation = CardDefaults.cardElevation(3.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = titulo,
                style = MaterialTheme.typography.bodySmall,
                color = CinzaTexto,
                maxLines = 2
            )

            Text(
                text = valor,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = corValor,
                maxLines = 2
            )
        }
    }
}

