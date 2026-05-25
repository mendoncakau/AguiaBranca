package br.com.fiap.aguiabranca.ui.gestor.ideias

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Lightbulb
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*

private val Azul = Color(0xFF0047C7)
private val AzulClaro = Color(0xFFEAF1FF)
private val AzulEscuro = Color(0xFF06143A)
private val Verde = Color(0xFF00995D)
private val VerdeClaro = Color(0xFFE8F8F1)
private val Vermelho = Color(0xFFE53935)
private val VermelhoClaro = Color(0xFFFFECEC)
private val Laranja = Color(0xFFFFA000)
private val LaranjaClaro = Color(0xFFFFF5DD)
private val CinzaTexto = Color(0xFF5C6680)
private val Fundo = Color(0xFFF7F9FC)

data class IdeiaGestor(
    val titulo: String,
    val autor: String,
    val data: String,
    val impacto: String,
    val status: String,
    val cor: Color,
    val fundoStatus: Color
)

@Composable
fun TodasIdeiasScreen(
    modifier: Modifier = Modifier,
    statusAtualizado: String = "Em análise",
    onAbrirAprovacao: () -> Unit = {}
) {
    var abaSelecionada by remember { mutableStateOf("Todos") }

    val abas = listOf("Todos", "Em análise", "Aprovadas", "Rejeitadas")

    val corAtualizada = when (statusAtualizado) {
        "Aprovadas" -> Verde
        "Rejeitadas" -> Vermelho
        else -> Azul
    }

    val fundoAtualizado = when (statusAtualizado) {
        "Aprovadas" -> VerdeClaro
        "Rejeitadas" -> VermelhoClaro
        else -> AzulClaro
    }

    val ideias = listOf(
        IdeiaGestor(
            titulo = "Redução de papel nas garagens",
            autor = "João Silva",
            data = "10/05/2024",
            impacto = "Baixo impacto",
            status = statusAtualizado,
            cor = corAtualizada,
            fundoStatus = fundoAtualizado
        ),
        IdeiaGestor(
            titulo = "Otimização no processo de manutenção",
            autor = "Ana Paula",
            data = "10/05/2024",
            impacto = "Alto impacto",
            status = "Aprovadas",
            cor = Verde,
            fundoStatus = VerdeClaro
        ),
        IdeiaGestor(
            titulo = "Mais sinalização nas áreas operacionais",
            autor = "Carlos Lima",
            data = "06/05/2024",
            impacto = "Médio impacto",
            status = "Em análise",
            cor = Laranja,
            fundoStatus = LaranjaClaro
        ),
        IdeiaGestor(
            titulo = "Uniformização de documentos",
            autor = "Marina Alves",
            data = "03/05/2024",
            impacto = "Baixo impacto",
            status = "Rejeitadas",
            cor = Vermelho,
            fundoStatus = VermelhoClaro
        )
    )

    val ideiasFiltradas = if (abaSelecionada == "Todos") {
        ideias
    } else {
        ideias.filter { it.status == abaSelecionada }
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(Fundo),
        contentPadding = PaddingValues(horizontal = 18.dp, vertical = 18.dp),
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        item {
            HeaderIdeias()
        }

        item {
            CardResumoIdeias()
        }

        item {
            TabsIdeias(
                abas = abas,
                selecionada = abaSelecionada,
                onSelecionar = { abaSelecionada = it }
            )
        }

        items(ideiasFiltradas) { ideia ->
            CardIdeiaLinha(
                ideia = ideia,
                onClick = onAbrirAprovacao
            )
        }

        item {
            Spacer(modifier = Modifier.height(80.dp))
        }
    }
}

@Composable
private fun HeaderIdeias() {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Todas as ideias",
            fontSize = 23.sp,
            fontWeight = FontWeight.Bold,
            color = AzulEscuro
        )

        Text(
            text = "Clique para aprovar a ideia",
            fontSize = 12.sp,
            color = CinzaTexto
        )
    }
}

@Composable
private fun CardResumoIdeias() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(containerColor = Azul),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.horizontalGradient(
                        listOf(Azul, Color(0xFF002E8A))
                    )
                )
                .padding(18.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Surface(
                    modifier = Modifier.size(46.dp),
                    shape = RoundedCornerShape(16.dp),
                    color = Color.White.copy(alpha = 0.18f)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(
                            imageVector = Icons.Outlined.Lightbulb,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                }

                Spacer(modifier = Modifier.width(14.dp))

                Column {
                    Text(
                        text = "23 ideias recebidas",
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = "10 aprovadas • 8 em análise • 3 rejeitadas",
                        color = Color.White.copy(alpha = 0.85f),
                        fontSize = 12.sp
                    )
                }
            }
        }
    }
}

@Composable
private fun TabsIdeias(
    abas: List<String>,
    selecionada: String,
    onSelecionar: (String) -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        color = Color.White,
        shadowElevation = 2.dp
    ) {
        Row(
            modifier = Modifier.padding(5.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            abas.forEach { aba ->
                val ativa = aba == selecionada

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(38.dp)
                        .background(
                            color = if (ativa) AzulClaro else Color.Transparent,
                            shape = RoundedCornerShape(13.dp)
                        )
                        .clickable {
                            onSelecionar(aba)
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = aba,
                        fontSize = 12.sp,
                        fontWeight = if (ativa) FontWeight.Bold else FontWeight.Medium,
                        color = if (ativa) Azul else CinzaTexto
                    )
                }
            }
        }
    }
}

@Composable
private fun CardIdeiaLinha(
    ideia: IdeiaGestor,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            },
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(3.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.Top
        ) {
            Box(
                modifier = Modifier
                    .width(4.dp)
                    .height(88.dp)
                    .background(
                        ideia.cor,
                        RoundedCornerShape(50.dp)
                    )
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = ideia.titulo,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = AzulEscuro,
                    lineHeight = 18.sp
                )

                Spacer(modifier = Modifier.height(5.dp))

                Text(
                    text = ideia.autor,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    color = CinzaTexto
                )

                Spacer(modifier = Modifier.height(11.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(9.dp)
                            .background(
                                ideia.cor,
                                CircleShape
                            )
                    )

                    Spacer(modifier = Modifier.width(7.dp))

                    Text(
                        text = ideia.impacto,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = ideia.cor
                    )
                }
            }

            Column(horizontalAlignment = Alignment.End) {
                Text(
                    text = ideia.data,
                    fontSize = 11.sp,
                    color = CinzaTexto
                )

                Spacer(modifier = Modifier.height(10.dp))

                Surface(
                    color = ideia.fundoStatus,
                    shape = RoundedCornerShape(50.dp)
                ) {
                    Text(
                        text = ideia.status,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = ideia.cor,
                        modifier = Modifier.padding(
                            horizontal = 10.dp,
                            vertical = 6.dp
                        )
                    )
                }
            }
        }
    }
}