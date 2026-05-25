package br.com.fiap.aguiabranca.ui.operador.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.aguiabranca.ui.theme.*

@Composable
fun CardNovaIdeia(
    onNovaIdeiaClick: () -> Unit
) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {

        Box(
            modifier = Modifier
                .background(
                    brush = Brush.horizontalGradient(
                        listOf(
                            AzulAguaBranca,
                            AzulAguaBranca.copy(alpha = 0.85f)
                        )
                    )
                )
                .padding(22.dp)
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Column(
                    modifier = Modifier.weight(1f)
                ) {

                    Text(
                        text = "Tem uma ideia?",
                        color = Branco,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Compartilhe e ajude a transformar nossa empresa.",
                        color = Branco.copy(alpha = 0.9f),
                        fontSize = 15.sp
                    )

                    Spacer(modifier = Modifier.height(18.dp))

                    Button(
                        onClick = onNovaIdeiaClick,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Branco
                        ),
                        shape = RoundedCornerShape(14.dp)
                    ) {

                        Text(
                            text = "Nova Ideia",
                            color = AzulAguaBranca,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Text(
                    text = "💡",
                    fontSize = 72.sp
                )
            }
        }
    }
}