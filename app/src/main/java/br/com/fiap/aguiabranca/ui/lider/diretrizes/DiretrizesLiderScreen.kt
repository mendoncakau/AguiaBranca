package br.com.fiap.aguiabranca.ui.lider.diretrizes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import br.com.fiap.aguiabranca.ui.components.BottomBarLider
import br.com.fiap.aguiabranca.ui.components.CardDiretriz
import br.com.fiap.aguiabranca.ui.components.RoxoLider
import br.com.fiap.aguiabranca.ui.components.TopoTelaLider
import br.com.fiap.aguiabranca.ui.theme.Branco
import br.com.fiap.aguiabranca.ui.theme.FundoTela
import br.com.fiap.aguiabranca.ui.theme.PretoTexto
import br.com.fiap.aguiabranca.viewmodel.LiderViewModel

@Composable
fun DiretrizesLiderScreen(
    viewModel: LiderViewModel = LiderViewModel()
) {

    val diretrizes by viewModel.diretrizes.collectAsState()

    Scaffold(
        bottomBar = { BottomBarLider() },
        containerColor = FundoTela
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(FundoTela)
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState())
        ) {

            TopoTelaLider(
                titulo = "Diretrizes estratégicas",
                mostrarFiltro = false
            )

            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(
                    containerColor = RoxoLider
                ),
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.fillMaxWidth()
            ) {

                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = "Nova diretriz",
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(18.dp))

            Text(
                text = "Diretrizes cadastradas",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = PretoTexto
            )

            Spacer(modifier = Modifier.height(10.dp))

            diretrizes.forEach { diretriz ->

                CardDiretriz(
                    titulo = diretriz.titulo,
                    descricao = diretriz.descricao,
                    onEditar = {},
                    onExcluir = {
                        viewModel.excluirDiretriz(diretriz.id)
                    }
                )
            }

            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}