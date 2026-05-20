package br.com.fiap.aguiabranca.ui.operador.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.fiap.aguiabranca.ui.componentes.BarraNavegacaoOperador
import br.com.fiap.aguiabranca.ui.componentes.CardEstatistica
import br.com.fiap.aguiabranca.ui.componentes.CardHeroIdeia
import br.com.fiap.aguiabranca.ui.theme.AzulPrincipal
import br.com.fiap.aguiabranca.ui.theme.FundoTela
import br.com.fiap.aguiabranca.viewmodel.HomeOperadorViewModel


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

            BarraNavegacaoOperador()
        },

        floatingActionButton = {

            FloatingActionButton(
                onClick = { },
                containerColor = AzulPrincipal
            ) {

                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null,
                    tint = Color.White
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

            verticalArrangement = Arrangement.spacedBy(24.dp),

            contentPadding = PaddingValues(bottom = 120.dp)
        ) {

            item {

                Spacer(modifier = Modifier.height(10.dp))
            }

            item {

                Text(
                    text = "Olá, $nomeUsuario 👋",
                    style = MaterialTheme.typography.headlineMedium
                )
            }

            item {

                Text(
                    text = "Vamos transformar ideias em resultados.",
                    color = Color.Gray
                )
            }

            item {

                CardHeroIdeia()
            }

            item {

                Text(
                    text = "Resumo",
                    style = MaterialTheme.typography.titleLarge
                )
            }

            item {

                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {

                    item {

                        CardEstatistica(
                            titulo = "Minhas ideias",
                            valor = quantidadeIdeias,
                            cor = AzulPrincipal
                        )
                    }

                    item {

                        CardEstatistica(
                            titulo = "Em análise",
                            valor = quantidadeEmAnalise,
                            cor = Color(0xFFF59E0B)
                        )
                    }

                    item {

                        CardEstatistica(
                            titulo = "Aprovadas",
                            valor = quantidadeAprovadas,
                            cor = Color(0xFF16A34A)
                        )
                    }

                    item {

                        CardEstatistica(
                            titulo = "Projetos",
                            valor = quantidadeViraramProjeto,
                            cor = Color(0xFF7C3AED)
                        )
                    }
                }
            }
        }
    }
}