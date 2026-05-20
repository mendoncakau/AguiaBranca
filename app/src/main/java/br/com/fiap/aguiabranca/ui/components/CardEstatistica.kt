package br.com.fiap.aguiabranca.ui.componentes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CardEstatistica(
    titulo: String,
    valor: Int,
    cor: Color
) {

    Card(
        shape = RoundedCornerShape(24.dp),

        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),

        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        )
    ) {

        Column(
            modifier = Modifier
                .width(140.dp)
                .background(Color.White)
                .padding(20.dp),

            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {

            Text(
                text = valor.toString(),
                style = MaterialTheme.typography.headlineMedium,
                color = cor
            )

            Text(
                text = titulo,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
        }
    }
}