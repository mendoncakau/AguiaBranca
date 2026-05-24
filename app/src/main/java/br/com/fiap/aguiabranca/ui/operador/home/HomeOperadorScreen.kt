package br.com.fiap.aguiabranca.ui.operador.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.NotificationsNone
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.com.fiap.aguiabranca.ui.operador.home.components.BottomBarOperador
import br.com.fiap.aguiabranca.ui.operador.home.components.CardNovaIdeia
import br.com.fiap.aguiabranca.ui.operador.home.components.DiretrizCard
import br.com.fiap.aguiabranca.ui.operador.home.components.ItemResumo
import br.com.fiap.aguiabranca.ui.theme.*

@Composable
fun HomeOperadorScreen(
    navController: NavHostController,
    onNovaIdeiaClick: () -> Unit = {}
) {

    Scaffold(

        containerColor = Fundo,

        floatingActionButton = {

            FloatingActionButton(
                onClick = onNovaIdeiaClick,
                shape = CircleShape,
                containerColor = AzulPrincipal
            ) {

                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null,
                    tint = Branco
                )
            }
        },

        bottomBar = {

            BottomBarOperador(
                navController = navController
            )
        }

    ) { padding ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 20.dp)
        ) {

            item {

                Spacer(modifier = Modifier.height(20.dp))

                // HEADER

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Column {

                        Text(
                            text = "Olá, João! 👋",
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold,
                            color = PretoTexto
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            text = "Que bom te ver por aqui.",
                            fontSize = 16.sp,
                            color = CinzaTexto
                        )
                    }

                    IconButton(
                        onClick = {}
                    ) {

                        Icon(
                            imageVector = Icons.Outlined.NotificationsNone,
                            contentDescription = null,
                            tint = PretoTexto
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // CARD PRINCIPAL

                CardNovaIdeia(
                    onNovaIdeiaClick = onNovaIdeiaClick
                )

                Spacer(modifier = Modifier.height(32.dp))

                // DIRETRIZES

                Text(
                    text = "Diretrizes estratégicas",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = PretoTexto
                )

                Spacer(modifier = Modifier.height(16.dp))

                DiretrizCard(
                    titulo = "Reduzir custos operacionais",
                    descricao = "Ideias para otimizar recursos e reduzir desperdícios."
                )

                Spacer(modifier = Modifier.height(12.dp))

                DiretrizCard(
                    titulo = "Melhorar experiência do cliente",
                    descricao = "Sugestões para aumentar satisfação e qualidade."
                )

                Spacer(modifier = Modifier.height(12.dp))

                DiretrizCard(
                    titulo = "Aumentar eficiência logística",
                    descricao = "Melhorias em processos, rotas e produtividade."
                )

                Spacer(modifier = Modifier.height(32.dp))

                // RESUMO

                Text(
                    text = "Resumo",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = PretoTexto
                )

                Spacer(modifier = Modifier.height(18.dp))

                ItemResumo(
                    titulo = "Minhas ideias",
                    valor = "7",
                    cor = AzulPrincipal
                )

                ItemResumo(
                    titulo = "Em análise",
                    valor = "2",
                    cor = LaranjaAnalise
                )

                ItemResumo(
                    titulo = "Aprovadas",
                    valor = "3",
                    cor = VerdeAprovado
                )

                ItemResumo(
                    titulo = "Implementadas",
                    valor = "2",
                    cor = RoxoProjeto
                )

                Spacer(modifier = Modifier.height(120.dp))
            }
        }
    }

}
