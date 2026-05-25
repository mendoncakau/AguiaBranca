package br.com.fiap.aguiabranca.ui.gestor.projetos

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*

private val Azul = Color(0xFF0047C7)
private val AzulEscuro = Color(0xFF06143A)
private val Verde = Color(0xFF00995D)
private val CinzaTexto = Color(0xFF5C6680)
private val CinzaClaro = Color(0xFFE8ECF4)
private val Fundo = Color(0xFFF7F9FC)

@Composable
fun AcompanharProgressoScreen(
    modifier: Modifier = Modifier,
    onFechar: () -> Unit = {}
) {
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
            IconButton(
                onClick = onFechar
            ) {
                Icon(
                    imageVector = Icons.Outlined.Close,
                    contentDescription = null,
                    tint = AzulEscuro
                )
            }

            Text(
                text = "Acompanhamento",
                fontSize = 23.sp,
                fontWeight = FontWeight.Bold,
                color = AzulEscuro
            )

            Spacer(modifier = Modifier.width(48.dp))
        }

        Spacer(modifier = Modifier.height(18.dp))

        Text(
            text = "Redução de papel nas garagens",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = AzulEscuro
        )

        Spacer(modifier = Modifier.height(8.dp))

        Surface(
            color = Color(0xFFEAF1FF),
            shape = RoundedCornerShape(50.dp)
        ) {
            Text(
                text = "Em andamento",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = Azul,
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
            )
        }

        Spacer(modifier = Modifier.height(22.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Progresso geral",
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = AzulEscuro
            )

            Text(
                text = "40%",
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = AzulEscuro
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        LinearProgressIndicator(
            progress = { 0.40f },
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp),
            color = Azul,
            trackColor = CinzaClaro
        )

        Spacer(modifier = Modifier.height(26.dp))

        Text(
            text = "Marcos",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = AzulEscuro
        )

        Spacer(modifier = Modifier.height(18.dp))

        MarcoItem("Planejamento", "Concluído", "10/05/2024", true)
        MarcoItem("Implementação", "Em andamento", "", false)
        MarcoItem("Testes", "Pendente", "", false)
        MarcoItem("Conclusão", "Pendente", "", false, true)

        Spacer(modifier = Modifier.height(90.dp))
    }
}

@Composable
private fun MarcoItem(
    titulo: String,
    status: String,
    data: String,
    concluido: Boolean,
    ultimo: Boolean = false
) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Surface(
                modifier = Modifier.size(22.dp),
                shape = CircleShape,
                color = if (concluido) Verde else Color.White,
                border = BorderStroke(
                    width = 2.dp,
                    color = if (concluido) Verde else CinzaClaro
                )
            ) {
                if (concluido) {
                    Icon(
                        imageVector = Icons.Outlined.Check,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.padding(4.dp)
                    )
                }
            }

            if (!ultimo) {
                Box(
                    modifier = Modifier
                        .width(2.dp)
                        .height(34.dp)
                        .background(CinzaClaro)
                )
            }
        }

        Spacer(modifier = Modifier.width(12.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 18.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            Text(
                text = titulo,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = AzulEscuro
            )

            Column(horizontalAlignment = Alignment.End) {
                Text(
                    text = status,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (concluido) CinzaTexto else AzulEscuro
                )

                if (data.isNotEmpty()) {
                    Text(
                        text = data,
                        fontSize = 11.sp,
                        color = CinzaTexto
                    )
                }
            }
        }
    }
}