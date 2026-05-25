package br.com.fiap.aguiabranca.ui.gestor.dashboard

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*

private val Azul = Color(0xFF0047C7)
private val AzulEscuro = Color(0xFF06143A)
private val Verde = Color(0xFF00995D)
private val Vermelho = Color(0xFFE53935)
private val Roxo = Color(0xFF6A35C2)
private val CinzaTexto = Color(0xFF5C6680)
private val CinzaBorda = Color(0xFFE8EBF2)
private val Fundo = Color(0xFFFAFBFD)

@Composable
fun DashboardGestorScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Fundo)
            .padding(horizontal = 20.dp, vertical = 18.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Dashboard",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = AzulEscuro,
                fontFamily = FontFamily.SansSerif
            )

            Icon(
                imageVector = Icons.Outlined.Notifications,
                contentDescription = null,
                tint = AzulEscuro,
                modifier = Modifier.size(24.dp)
            )
        }

        Spacer(modifier = Modifier.height(22.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            InfoCard(
                titulo = "Ideias rec.",
                valor = "23",
                corValor = Azul,
                modifier = Modifier.weight(1f)
            )

            InfoCard(
                titulo = "Aprovadas",
                valor = "10",
                corValor = Verde,
                modifier = Modifier.weight(1f)
            )

            InfoCard(
                titulo = "Em Andamento",
                valor = "8",
                corValor = Verde,
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = "Impacto estimado (ROI)",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = AzulEscuro
        )

        Spacer(modifier = Modifier.height(20.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(2.dp)
        ) {
            Column(
                modifier = Modifier.padding(18.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "Investimento total",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = CinzaTexto
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        Text(
                            text = "R$ 120.000",
                            fontSize = 23.sp,
                            fontWeight = FontWeight.Bold,
                            color = Azul
                        )
                    }

                    DividerVertical()

                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "Retorno estimado",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = CinzaTexto
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        Text(
                            text = "R$ 165.000",
                            fontSize = 23.sp,
                            fontWeight = FontWeight.Bold,
                            color = Azul
                        )
                    }
                }

                Spacer(modifier = Modifier.height(18.dp))

                Divider(color = CinzaBorda)

                Spacer(modifier = Modifier.height(18.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "ROI total",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = CinzaTexto
                    )

                    Text(
                        text = "R$ 45.000",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Verde
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = "Ideias por status",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = AzulEscuro
        )

        Spacer(modifier = Modifier.height(22.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(2.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(18.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                DonutChart()

                Spacer(modifier = Modifier.width(22.dp))

                Column(
                    verticalArrangement = Arrangement.spacedBy(14.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    LegendItem(Azul, "Em análise", "8 (34%)")
                    LegendItem(Verde, "Aprovadas", "10 (43%)")
                    LegendItem(Vermelho, "Rejeitadas", "3 (13%)")
                    LegendItem(Roxo, "Viraram projeto", "2 (10%)")
                }
            }
        }
    }
}

@Composable
private fun InfoCard(
    titulo: String,
    valor: String,
    corValor: Color,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.height(112.dp),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(14.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = titulo,
                fontSize = 13.sp,
                lineHeight = 15.sp,
                fontWeight = FontWeight.SemiBold,
                color = CinzaTexto,
                fontFamily = FontFamily.SansSerif
            )

            Text(
                text = valor,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = corValor,
                fontFamily = FontFamily.SansSerif
            )
        }
    }
}

@Composable
private fun DividerVertical() {
    Box(
        modifier = Modifier
            .height(70.dp)
            .width(1.dp)
            .background(CinzaBorda)
    )
}

@Composable
private fun DonutChart() {
    Canvas(
        modifier = Modifier.size(120.dp)
    ) {
        val stroke = Stroke(
            width = 22.dp.toPx(),
            cap = StrokeCap.Butt
        )

        val chartSize = Size(size.width, size.height)

        drawArc(
            color = Azul,
            startAngle = -90f,
            sweepAngle = 122f,
            useCenter = false,
            size = chartSize,
            style = stroke
        )

        drawArc(
            color = Verde,
            startAngle = 36f,
            sweepAngle = 155f,
            useCenter = false,
            size = chartSize,
            style = stroke
        )

        drawArc(
            color = Vermelho,
            startAngle = 195f,
            sweepAngle = 47f,
            useCenter = false,
            size = chartSize,
            style = stroke
        )

        drawArc(
            color = Roxo,
            startAngle = 246f,
            sweepAngle = 38f,
            useCenter = false,
            size = chartSize,
            style = stroke
        )
    }
}

@Composable
private fun LegendItem(
    cor: Color,
    texto: String,
    valor: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(13.dp)
                .background(cor, CircleShape)
        )

        Spacer(modifier = Modifier.width(10.dp))

        Text(
            text = texto,
            fontSize = 13.sp,
            fontWeight = FontWeight.Medium,
            color = CinzaTexto,
            modifier = Modifier.weight(1f)
        )

        Text(
            text = valor,
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold,
            color = AzulEscuro
        )
    }
}