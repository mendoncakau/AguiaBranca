package br.com.fiap.aguiabranca.ui.gestor.ideias

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBackIosNew
import androidx.compose.material.icons.outlined.Description
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*

private val Azul = Color(0xFF0047C7)
private val AzulEscuro = Color(0xFF06143A)
private val AzulClaro = Color(0xFFEAF1FF)
private val Verde = Color(0xFF00995D)
private val Vermelho = Color(0xFFE53935)
private val Laranja = Color(0xFFFF9800)
private val CinzaTexto = Color(0xFF5C6680)
private val Fundo = Color(0xFFF7F9FC)
private val Borda = Color(0xFFE8ECF4)

@Composable
fun AprovacaoIdeiaScreen(
    modifier: Modifier = Modifier,
    statusAtual: String = "Em análise",
    onVoltar: () -> Unit = {},
    onAprovar: () -> Unit = {},
    onRejeitar: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Fundo)
    ) {
        HeaderAprovacao(onVoltar)

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 18.dp, vertical = 18.dp)
        ) {
            CardPrincipalIdeia(statusAtual)

            Spacer(modifier = Modifier.height(18.dp))

            CardDetalhesIdeia()

            Spacer(modifier = Modifier.height(22.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Button(
                    onClick = onRejeitar,
                    modifier = Modifier
                        .weight(1f)
                        .height(64.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Vermelho
                    )
                ) {
                    Text(
                        text = "Rejeitar",
                        fontWeight = FontWeight.Bold
                    )
                }

                Button(
                    onClick = onAprovar,
                    modifier = Modifier
                        .weight(1f)
                        .height(64.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Verde
                    )
                ) {
                    Text(
                        text = "Aprovar",
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(160.dp))
        }
    }
}

@Composable
private fun HeaderAprovacao(
    onVoltar: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                Brush.horizontalGradient(
                    listOf(Azul, Color(0xFF002E8A))
                )
            )
            .statusBarsPadding()
            .padding(horizontal = 16.dp, vertical = 14.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                modifier = Modifier.size(42.dp),
                shape = RoundedCornerShape(14.dp),
                color = Color.White.copy(alpha = 0.18f)
            ) {
                IconButton(
                    onClick = onVoltar
                ) {
                    Icon(
                        imageVector = Icons.Outlined.ArrowBackIosNew,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(19.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.width(14.dp))

            Column {
                Text(
                    text = "Aprovação de Ideia",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                Text(
                    text = "Analise os detalhes antes de aprovar",
                    fontSize = 12.sp,
                    color = Color.White.copy(alpha = 0.82f)
                )
            }
        }
    }
}

@Composable
private fun CardPrincipalIdeia(
    statusAtual: String
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(3.dp)
    ) {
        Column(
            modifier = Modifier.padding(18.dp)
        ) {
            Surface(
                color = AzulClaro,
                shape = RoundedCornerShape(50.dp)
            ) {
                Text(
                    text = statusAtual,
                    color = Azul,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(
                        horizontal = 12.dp,
                        vertical = 6.dp
                    )
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            Text(
                text = "Redução de papel nas garagens",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = AzulEscuro,
                lineHeight = 26.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Enviada por João Silva • 12/05/2024",
                fontSize = 13.sp,
                fontWeight = FontWeight.Medium,
                color = CinzaTexto
            )

            Spacer(modifier = Modifier.height(18.dp))

            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = Color(0xFFF4F7FE),
                shape = RoundedCornerShape(18.dp)
            ) {
                Row(
                    modifier = Modifier.padding(14.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Surface(
                        modifier = Modifier.size(38.dp),
                        shape = RoundedCornerShape(13.dp),
                        color = AzulClaro
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Icon(
                                imageVector = Icons.Outlined.Description,
                                contentDescription = null,
                                tint = Azul,
                                modifier = Modifier.size(21.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    Text(
                        text = "Implementar documentos digitais para reduzir o uso de papel nos processos diários das garagens.",
                        fontSize = 14.sp,
                        color = AzulEscuro,
                        lineHeight = 20.sp
                    )
                }
            }
        }
    }
}

@Composable
private fun CardDetalhesIdeia() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(3.dp)
    ) {
        Column(
            modifier = Modifier.padding(18.dp)
        ) {
            Text(
                text = "Detalhes da ideia",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = AzulEscuro
            )

            Spacer(modifier = Modifier.height(14.dp))

            LinhaDetalhe("Categoria", "Meio ambiente")
            LinhaDetalhe("Tipo de impacto", "Redução de custo")
            LinhaDetalhe("Impacto esperado", "Médio", Laranja)
            LinhaDetalhe("Estimativa de ganho", "R$ 5.000 / mês", Verde)
            LinhaDetalhe("Diretriz estratégica", "Reduzir custos operacionais")
        }
    }
}

@Composable
private fun LinhaDetalhe(
    titulo: String,
    valor: String,
    corValor: Color = AzulEscuro
) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 11.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = titulo,
                fontSize = 13.sp,
                fontWeight = FontWeight.Medium,
                color = CinzaTexto
            )

            Text(
                text = valor,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = corValor
            )
        }

        Divider(color = Borda)
    }
}