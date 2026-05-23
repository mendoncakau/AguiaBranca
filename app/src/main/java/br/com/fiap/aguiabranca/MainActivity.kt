package br.com.fiap.aguiabranca

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import br.com.fiap.aguiabranca.ui.operador.home.HomeOperadorScreen
import br.com.fiap.aguiabranca.ui.lider.dashboard.DashboardLiderScreen
import br.com.fiap.aguiabranca.ui.lider.diretrizes.DiretrizesLiderScreen
import br.com.fiap.aguiabranca.ui.lider.relatorios.RelatoriosLiderScreen
import br.com.fiap.aguiabranca.ui.lider.projetos.ProjetosLiderScreen
import br.com.fiap.aguiabranca.ui.theme.AguiaBrancaTheme
import br.com.fiap.aguiabranca.ui.navigation.AppNavigation


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {

            AguiaBrancaTheme {

                AppNavigation()
            }
        }
    }
}