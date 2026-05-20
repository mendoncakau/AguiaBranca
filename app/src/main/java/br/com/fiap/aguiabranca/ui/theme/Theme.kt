package br.com.fiap.aguiabranca.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val EsquemaClaro = lightColorScheme(

    primary = AzulPrincipal,

    secondary = AzulClaro,

    background = FundoTela,

    surface = Branco,

    onPrimary = Branco,

    onBackground = PretoTexto,

    onSurface = PretoTexto
)

private val EsquemaEscuro = darkColorScheme(

    primary = AzulPrincipal,

    secondary = VerdeAprovado
)

@Composable
fun AguiaBrancaTheme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {

    val esquemaCores = if (darkTheme) {
        EsquemaEscuro
    } else {
        EsquemaClaro
    }

    MaterialTheme(
        colorScheme = esquemaCores,
        content = content
    )
}
