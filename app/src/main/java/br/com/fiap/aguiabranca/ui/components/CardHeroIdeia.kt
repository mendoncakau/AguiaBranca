package br.com.fiap.aguiabranca.ui.componentes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import br.com.fiap.aguiabranca.ui.theme.AzulClaro
import br.com.fiap.aguiabranca.ui.theme.AzulPrincipal
import br.com.fiap.aguiabranca.ui.theme.Branco

@Composable
fun CardHeroIdeia() {

    Card(
        shape = RoundedCornerShape(30.dp),

        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        )
    ) {

        Column(
            modifier = Modifier
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            AzulPrincipal,
                            AzulClaro
                        )
                    )
                )
                .padding(24.dp),

            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {

            Row(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(Color.White.copy(alpha = 0.15f))
                    .padding(12.dp),

                verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(
                    imageVector = Icons.Default.Lightbulb,
                    contentDescription = null,
                    tint = Color.Yellow,
                    modifier = Modifier.size(28.dp)
                )
            }

            Text(
                text = "Tem uma ideia inovadora?",
                color = Branco,
                style = MaterialTheme.typography.headlineSmall
            )

            Text(
                text = "Compartilhe suas ideias e ajude a transformar a empresa.",
                color = Branco
            )
        }
    }
}