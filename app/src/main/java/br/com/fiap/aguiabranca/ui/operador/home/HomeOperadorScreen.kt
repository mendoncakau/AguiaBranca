package br.com.fiap.aguiabranca.ui.operador.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.fiap.aguiabranca.ui.componentes.CardResumo
import br.com.fiap.aguiabranca.ui.theme.AzulPrincipal
import br.com.fiap.aguiabranca.ui.theme.Branco
import br.com.fiap.aguiabranca.ui.theme.FundoTela
import br.com.fiap.aguiabranca.ui.theme.LaranjaAnalise
import br.com.fiap.aguiabranca.ui.theme.VerdeAprovado
import br.com.fiap.aguiabranca.viewmodel.HomeOperadorViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun HomeOperadorScreen(
    homeOperadorViewModel: HomeOperadorViewModel = viewModel()
) {

    val nomeUsuario by homeOperadorViewModel.nomeUsuario.collectAsState()

    val quantidadeIdeias by homeOperadorViewModel.quantidadeIdeias.collectAsState()

    val quantidadeEmAnalise by homeOperadorViewModel.quantidadeEmAnalise.collectAsState()

    val quantidadeAprovadas by homeOperadorViewModel.quantidadeAprovadas.collectAsState()

    val quantidadeViraramProjeto by homeOperadorViewModel.quantidadeViraramProjeto.collectAsState()

    Scaffold(

        containerColor = FundoTela,

        bottomBar = {

            BottomAppBar(
                containerColor = Branco
            ) {

                Row(
                    modifier = Modifier.fillMaxWidth(),

                    horizontalArrangement = Arrangement.SpaceAround
                ) {

                    Icon(
                        imageVector = Icons.Default.Home,
                        contentDescription = null,
                        tint = AzulPrincipal
                    )

                    Icon(
                        imageVector = Icons.Default.Lightbulb,
                        contentDescription = null,
                        tint = Color.Gray
                    )

                    Icon(
                        imageVector = Icons.Default.Settings,
                        contentDescription = null,
                        tint = Color.Gray
                    )

                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = null,
                        tint = Color.Gray
                    )
                }
            }
        },

        floatingActionButton = {

            FloatingActionButton(
                onClick = { },
                containerColor = AzulPrincipal
            ) {

                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null,
                    tint = Branco
                )
            }
        }

    ) { paddingValues ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(FundoTela)
                .padding(paddingValues)
                .padding(horizontal = 20.dp),

            verticalArrangement = Arrangement.spacedBy(18.dp),

            contentPadding = PaddingValues(bottom = 100.dp)
        ) {

            item {

                Spacer(modifier = Modifier.height(10.dp))
            }

            item {

                Column {

                    Text(
                        text = "Olá, $nomeUsuario 👋",
                        style = MaterialTheme.typography.headlineSmall
                    )

                    Text(
                        text = "Que bom ver você aqui.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Gray
                    )
                }
            }

            item {

                Card(
                    modifier = Modifier.fillMaxWidth(),

                    shape = RoundedCornerShape(28.dp),

                    colors = CardDefaults.cardColors(
                        containerColor = AzulPrincipal
                    ),

                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 8.dp
                    )
                ) {

                    Column(
                        modifier = Modifier.padding(24.dp),

                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {

                        BoxIconeIdeia()

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

            item {

                Text(
                    text = "Resumo",
                    style = MaterialTheme.typography.titleLarge
                )
            }

            item {

                CardResumo(
                    titulo = "Minhas ideias",
                    valor = quantidadeIdeias,
                    corCard = AzulPrincipal
                )
            }

            item {

                CardResumo(
                    titulo = "Em análise",
                    valor = quantidadeEmAnalise,
                    corCard = LaranjaAnalise
                )
            }

            item {

                CardResumo(
                    titulo = "Aprovadas",
                    valor = quantidadeAprovadas,
                    corCard = VerdeAprovado
                )
            }

            item {

                CardResumo(
                    titulo = "Viraram projeto",
                    valor = quantidadeViraramProjeto,
                    corCard = Color(0xFF7B1FA2)
                )
            }
        }
    }
}

@Composable
fun BoxIconeIdeia() {

    Box(
        modifier = Modifier
            .clip(CircleShape)
            .background(Color.White.copy(alpha = 0.2f))
            .padding(12.dp)
    ) {

        Icon(
            imageVector = Icons.Default.Lightbulb,
            contentDescription = null,
            tint = Color.Yellow,
            modifier = Modifier.size(28.dp)
        )
    }
}
