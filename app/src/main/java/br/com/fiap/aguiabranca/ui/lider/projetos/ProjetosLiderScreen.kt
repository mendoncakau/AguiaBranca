package br.com.fiap.aguiabranca.ui.lider.projetos

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import br.com.fiap.aguiabranca.ui.components.CardProjetoLider
import br.com.fiap.aguiabranca.ui.theme.*
import br.com.fiap.aguiabranca.viewmodel.LiderViewModel

@Composable
fun ProjetosLiderScreen(
    modifier: Modifier = Modifier,
    viewModel: LiderViewModel
) {

    val projetos by viewModel.projetos.collectAsState()

    var pesquisa by remember {
        mutableStateOf("")
    }

    val projetosFiltrados =
        if (pesquisa.isBlank()) {

            projetos

        } else {

            projetos.filter {

                it.nome.contains(
                    pesquisa,
                    ignoreCase = true
                )
            }
        }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(FundoTela)
    ) {

        // HEADER AZUL

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            AzulAguaBranca,
                            AzulAguaBranca.copy(alpha = 0.85f)
                        )
                    )
                )
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .padding(top = 28.dp, bottom = 26.dp)
            ) {

                Text(
                    text = "Projetos estratégicos",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = Branco
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = "Acompanhe os projetos aprovados da empresa",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Branco.copy(alpha = 0.9f)
                )

                Spacer(modifier = Modifier.height(22.dp))

                // CAMPO PESQUISA

                OutlinedTextField(
                    value = pesquisa,

                    onValueChange = {
                        pesquisa = it
                    },

                    placeholder = {
                        Text("Pesquisar projeto")
                    },

                    leadingIcon = {

                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = null
                        )
                    },

                    trailingIcon = {

                        if (pesquisa.isNotBlank()) {

                            IconButton(
                                onClick = {
                                    pesquisa = ""
                                }
                            ) {

                                Icon(
                                    imageVector = Icons.Default.Clear,
                                    contentDescription = null
                                )
                            }
                        }
                    },

                    singleLine = true,

                    shape = RoundedCornerShape(20.dp),

                    modifier = Modifier.fillMaxWidth(),

                    colors = OutlinedTextFieldDefaults.colors(

                        focusedContainerColor = Branco,
                        unfocusedContainerColor = Branco,

                        focusedBorderColor = Branco,
                        unfocusedBorderColor = Branco.copy(alpha = 0.3f),

                        focusedTextColor = PretoTexto,
                        unfocusedTextColor = PretoTexto,

                        cursorColor = AzulAguaBranca
                    )
                )

                Spacer(modifier = Modifier.height(22.dp))

                // RESUMO

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {

                    CardResumoProjeto(
                        titulo = "Projetos",
                        valor = projetos.size.toString(),
                        modifier = Modifier.weight(1f)
                    )

                    CardResumoProjeto(
                        titulo = "Concluídos",
                        valor = projetos.count {
                            it.status == "Concluído"
                        }.toString(),
                        modifier = Modifier.weight(1f)
                    )

                    CardResumoProjeto(
                        titulo = "Andamento",
                        valor = projetos.count {
                            it.status == "Em andamento"
                        }.toString(),
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // LISTAGEM

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(
                horizontal = 16.dp,
                vertical = 10.dp
            ),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            if (projetosFiltrados.isEmpty()) {

                item {

                    Card(
                        modifier = Modifier.fillMaxWidth(),

                        shape = RoundedCornerShape(26.dp),

                        colors = CardDefaults.cardColors(
                            containerColor = Branco
                        )
                    ) {

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(30.dp),

                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {

                            Text(
                                text = "Nenhum projeto encontrado",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = PretoTexto
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            Text(
                                text = "Tente pesquisar utilizando outro termo.",
                                style = MaterialTheme.typography.bodyMedium,
                                color = CinzaTexto
                            )
                        }
                    }
                }

            } else {

                items(projetosFiltrados) { projeto ->

                    CardProjetoLider(

                        nome = projeto.nome,

                        responsavel = projeto.responsavel,

                        prioridade = projeto.prioridade,

                        status = projeto.status,

                        investimento = projeto.investimento,

                        retorno = projeto.retorno,

                        descricao =
                            "Este projeto transforma ideias aprovadas em melhorias práticas para a operação da empresa.",

                        statusDetalhado = when (projeto.status) {

                            "Concluído" -> {

                                "Projeto finalizado com resultados positivos registrados nos relatórios estratégicos."
                            }

                            "Em andamento" -> {

                                "Projeto em execução com acompanhamento contínuo da liderança."
                            }

                            else -> {

                                "Projeto aguardando atualização da equipe responsável."
                            }
                        }
                    )
                }
            }

            item {

                Spacer(modifier = Modifier.height(120.dp))
            }
        }
    }
}

@Composable
fun CardResumoProjeto(
    titulo: String,
    valor: String,
    modifier: Modifier = Modifier
) {

    Card(
        modifier = modifier,

        shape = RoundedCornerShape(22.dp),

        colors = CardDefaults.cardColors(
            containerColor = Branco.copy(alpha = 0.16f)
        )
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 18.dp),

            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = valor,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = Branco
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = titulo,
                style = MaterialTheme.typography.bodySmall,
                color = Branco.copy(alpha = 0.85f)
            )
        }
    }
}
