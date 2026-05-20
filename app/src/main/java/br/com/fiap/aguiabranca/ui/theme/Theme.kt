package br.com.fiap.aguiabranca.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val EsquemaCoresClaro = lightColorScheme(

    primary = AzulPrincipal,

    secondary = VerdeAprovado,

    background = FundoTela,

    surface = Branco
)

private val EsquemaCoresEscuro = darkColorScheme(

    primary = AzulPrincipal,

    secondary = VerdeAprovado
)

@Composable
fun AguiaBrancaTheme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {

    val esquemaCores = if (darkTheme) {

        EsquemaCoresEscuro

    } else {

        EsquemaCoresClaro
    }

    MaterialTheme(

        colorScheme = esquemaCores,

        typography = Typography,

        content = content
    )
}