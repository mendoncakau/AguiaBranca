package br.com.fiap.aguiabranca.ui.gestor.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun CardIdeiaGestor(
    titulo: String
) {

    Card(
        modifier = Modifier.fillMaxWidth()
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Text(
                text = titulo,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            AssistChip(
                onClick = {},
                label = {
                    Text("Em análise")
                },
                colors = AssistChipDefaults.assistChipColors(
                    containerColor = Color(0xFFE3F2FD)
                )
            )
        }
    }
}