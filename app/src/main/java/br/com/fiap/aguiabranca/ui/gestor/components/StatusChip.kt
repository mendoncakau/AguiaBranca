package br.com.fiap.aguiabranca.ui.gestor.components

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun StatusChip(
    texto: String,
    cor: Color
) {

    AssistChip(
        onClick = {},
        label = {
            Text(texto)
        },
        colors = AssistChipDefaults.assistChipColors(
            containerColor = cor
        )
    )
}