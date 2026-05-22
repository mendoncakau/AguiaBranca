package br.com.fiap.aguiabranca.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MoreHoriz
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

val RoxoLider = Color(0xFF4A148C)

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
    mostrarFiltro: Boolean = true
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
                shape = RoundedCornerShape(24.dp),
                color = Color(0xFFEFF1F5)
            ) {

                Text(
                    text = "Este mês",
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
fun BottomBarLider() {
    NavigationBar(
        containerColor = Branco,
        tonalElevation = 8.dp
    ) {
        NavigationBarItem(
            selected = true,
            onClick = {},
            icon = { Icon(Icons.Default.Home, contentDescription = null) },
            label = { Text("Dashboard", maxLines = 1) },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = RoxoLider,
                selectedTextColor = RoxoLider,
                indicatorColor = Color(0xFFE7D7FF)
            )
        )

        NavigationBarItem(
            selected = false,
            onClick = {},
            icon = { Icon(Icons.Default.Flag, contentDescription = null) },
            label = { Text("Estratégia", maxLines = 1) }
        )

        NavigationBarItem(
            selected = false,
            onClick = {},
            icon = { Icon(Icons.Default.Folder, contentDescription = null) },
            label = { Text("Projetos", maxLines = 1) }
        )

        NavigationBarItem(
            selected = false,
            onClick = {},
            icon = { Icon(Icons.Default.BarChart, contentDescription = null) },
            label = { Text("Relatórios", maxLines = 1) }
        )

        NavigationBarItem(
            selected = false,
            onClick = {},
            icon = { Icon(Icons.Default.MoreHoriz, contentDescription = null) },
            label = { Text("Mais", maxLines = 1) }
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
    onEditar: () -> Unit = {},
    onExcluir: () -> Unit = {}
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = Branco),
        elevation = CardDefaults.cardElevation(3.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
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
                        tint = RoxoLider
                    )
                }

                IconButton(onClick = onExcluir) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Excluir",
                        tint = Color.Red
                    )
                }
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
    retorno: Double
) {
    val corPrioridade = when (prioridade) {
        "Alta" -> Color(0xFFD32F2F)
        "Média" -> Color(0xFFFF9800)
        else -> VerdeAprovado
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
                Text("Status: $status", color = CinzaTexto)
                Text("ROI: R$ %.0f".format(retorno - investimento), color = VerdeAprovado)
            }

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Investimento: R$ %.0f  •  Retorno: R$ %.0f".format(investimento, retorno),
                style = MaterialTheme.typography.bodySmall,
                color = CinzaTexto
            )
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

