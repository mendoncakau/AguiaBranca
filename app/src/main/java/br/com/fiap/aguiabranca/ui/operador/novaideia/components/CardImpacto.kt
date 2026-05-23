package br.com.fiap.aguiabranca.ui.operador.novaideia.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.aguiabranca.ui.theme.*

@Composable
fun CardImpacto(
    emoji: String,
    titulo: String,
    selecionado: Boolean,
    onClick: () -> Unit
) {

    Card(
        modifier = Modifier
            .width(110.dp)
            .height(110.dp)
            .clickable {
                onClick()
            }
            .border(
                width = if (selecionado) 2.dp else 1.dp,
                color = if (selecionado) AzulPrincipal else CinzaTexto.copy(alpha = 0.3f),
                shape = RoundedCornerShape(18.dp)
            ),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (selecionado)
                AzulClaro.copy(alpha = 0.08f)
            else
                Branco
        )
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = emoji,
                fontSize = 28.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = titulo,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}
