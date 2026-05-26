package br.com.fiap.aguiabranca

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import br.com.fiap.aguiabranca.ui.navigation.AppNavigation
import br.com.fiap.aguiabranca.ui.theme.AguiaBrancaTheme

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