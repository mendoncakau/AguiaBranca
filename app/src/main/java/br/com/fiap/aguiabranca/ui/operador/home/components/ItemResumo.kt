package br.com.fiap.aguiabranca.ui.operador.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.aguiabranca.ui.theme.TextoEscuro
import br.com.fiap.aguiabranca.ui.theme.TextoCinza

@Composable
fun ItemResumo(
    titulo: String,
    valor: String,
    cor: Color
) {

    Column {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 14.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Box(
                    modifier = Modifier
                        .size(12.dp)
                        .background(
                            cor,
                            CircleShape
                        )
                )

                Spacer(modifier = Modifier.width(14.dp))

                Text(
                    text = titulo,
                    color = TextoEscuro,
                    fontSize = 17.sp
                )
            }

            Text(
                text = valor,
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
                color = cor
            )
        }

        HorizontalDivider(
            color = TextoCinza.copy(alpha = 0.15f)
        )
    }
}