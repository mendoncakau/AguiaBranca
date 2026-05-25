package br.com.fiap.aguiabranca.ui.operador.ideias.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.aguiabranca.ui.theme.*

@Composable
fun CardIdeia(
    titulo: String,
    status: String,
    impacto: String,
    data: String,
    onClick: () -> Unit = {}
) {

    val corStatus = when (status) {

        "Em análise" -> LaranjaAnalise
        "Aprovada" -> VerdeAprovado
        "Implementada" -> AzulPrincipal
        else -> VermelhoStatus
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation = 4.dp,
                shape = RoundedCornerShape(20.dp)
            )
            .clickable {
                onClick()
            },
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = Branco
        )
    ) {

        Column(
            modifier = Modifier.padding(20.dp)
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Text(
                    text = titulo,
                    modifier = Modifier.weight(1f),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = PretoTexto
                )

                Spacer(modifier = Modifier.width(12.dp))

                Text(
                    text = data,
                    fontSize = 14.sp,
                    color = CinzaTexto
                )
            }

            Spacer(modifier = Modifier.height(18.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Box(
                    modifier = Modifier
                        .size(12.dp)
                        .background(
                            color = corStatus,
                            shape = CircleShape
                        )
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = status,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = corStatus
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            Box(
                modifier = Modifier
                    .background(
                        color = corStatus.copy(alpha = 0.12f),
                        shape = RoundedCornerShape(30.dp)
                    )
                    .padding(
                        horizontal = 14.dp,
                        vertical = 8.dp
                    )
            ) {

                Text(
                    text = impacto,
                    fontSize = 14.sp,
                    color = corStatus,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}
