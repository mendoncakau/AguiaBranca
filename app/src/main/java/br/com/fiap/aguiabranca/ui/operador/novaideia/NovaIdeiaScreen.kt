package br.com.fiap.aguiabranca.ui.operador.novaideia

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.aguiabranca.ui.operador.novaideia.components.CardImpacto
import br.com.fiap.aguiabranca.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NovaIdeiaScreen(
    onVoltarClick: () -> Unit = {}
) {

    var titulo by remember { mutableStateOf("") }
    var descricao by remember { mutableStateOf("") }
    var estimativa by remember { mutableStateOf("") }

    var impactoSelecionado by remember {
        mutableStateOf("Custo")
    }

    var impactoEsperado by remember {
        mutableStateOf("Médio")
    }


    val categorias = listOf(
        "Redução de custos",
        "Produtividade",
        "Atendimento",
        "Logística",
        "Sustentabilidade",
        "Segurança operacional"
    )

    var categoriaSelecionada by remember {
        mutableStateOf("")
    }

    var categoriaExpandida by remember {
        mutableStateOf(false)
    }


    val diretrizes = listOf(
        "Reduzir custos operacionais",
        "Melhorar experiência do cliente",
        "Aumentar eficiência logística"
    )

    var diretrizSelecionada by remember {
        mutableStateOf("")
    }

    var diretrizExpandida by remember {
        mutableStateOf(false)
    }

    Scaffold(
        containerColor = Fundo,

        topBar = {

            TopAppBar(

                title = {

                    Text(
                        text = "Nova ideia",
                        fontWeight = FontWeight.Bold
                    )
                },

                navigationIcon = {

                    IconButton(
                        onClick = onVoltarClick
                    ) {

                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = null
                        )
                    }
                }
            )
        }

    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 20.dp)
                .verticalScroll(rememberScrollState())
        ) {

            Spacer(modifier = Modifier.height(12.dp))


            Text(
                text = "Título da ideia",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = titulo,
                onValueChange = {
                    titulo = it
                },
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text("Ex.: Redução de consumo de papel")
                },
                shape = RoundedCornerShape(14.dp),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Descrição da ideia",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = descricao,
                onValueChange = {
                    if (it.length <= 500) {
                        descricao = it
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                placeholder = {
                    Text("Descreva sua ideia de forma clara...")
                },
                shape = RoundedCornerShape(14.dp)
            )

            Text(
                text = "${descricao.length}/500",
                modifier = Modifier.fillMaxWidth(),
                fontSize = 12.sp,
                color = CinzaTexto
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Categoria",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            ExposedDropdownMenuBox(
                expanded = categoriaExpandida,
                onExpandedChange = {
                    categoriaExpandida = !categoriaExpandida
                }
            ) {

                OutlinedTextField(
                    value = categoriaSelecionada,
                    onValueChange = {},
                    readOnly = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor(),
                    placeholder = {
                        Text("Selecione uma categoria")
                    },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(
                            expanded = categoriaExpandida
                        )
                    },
                    shape = RoundedCornerShape(14.dp)
                )

                ExposedDropdownMenu(
                    expanded = categoriaExpandida,
                    onDismissRequest = {
                        categoriaExpandida = false
                    }
                ) {

                    categorias.forEach { categoria ->

                        DropdownMenuItem(
                            text = {
                                Text(categoria)
                            },
                            onClick = {

                                categoriaSelecionada = categoria
                                categoriaExpandida = false
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))


            Text(
                text = "Tipo de impacto",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )

            Spacer(modifier = Modifier.height(14.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                CardImpacto(
                    emoji = "💰",
                    titulo = "Impacto de custo",
                    selecionado = impactoSelecionado == "Custo",
                    onClick = {
                        impactoSelecionado = "Custo"
                    }
                )

                CardImpacto(
                    emoji = "⚡",
                    titulo = "Impacto de tempo",
                    selecionado = impactoSelecionado == "Tempo",
                    onClick = {
                        impactoSelecionado = "Tempo"
                    }
                )

                CardImpacto(
                    emoji = "😊",
                    titulo = "Impacto de cliente",
                    selecionado = impactoSelecionado == "Cliente",
                    onClick = {
                        impactoSelecionado = "Cliente"
                    }
                )
            }

            Spacer(modifier = Modifier.height(24.dp))


            Text(
                text = "Impacto esperado",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                FilterChip(
                    selected = impactoEsperado == "Baixo",
                    onClick = {
                        impactoEsperado = "Baixo"
                    },
                    label = {
                        Text("Baixo")
                    }
                )

                FilterChip(
                    selected = impactoEsperado == "Médio",
                    onClick = {
                        impactoEsperado = "Médio"
                    },
                    label = {
                        Text("Médio")
                    }
                )

                FilterChip(
                    selected = impactoEsperado == "Alto",
                    onClick = {
                        impactoEsperado = "Alto"
                    },
                    label = {
                        Text("Alto")
                    }
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Estimativa de ganho (R$ ou %)",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = estimativa,
                onValueChange = {
                    estimativa = it
                },
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text("Ex.: R$ 5.000/mês ou 10% de redução")
                },
                shape = RoundedCornerShape(14.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Diretriz estratégica relacionada",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            ExposedDropdownMenuBox(
                expanded = diretrizExpandida,
                onExpandedChange = {
                    diretrizExpandida = !diretrizExpandida
                }
            ) {

                OutlinedTextField(
                    value = diretrizSelecionada,
                    onValueChange = {},
                    readOnly = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor(),
                    placeholder = {
                        Text("Selecione uma diretriz")
                    },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(
                            expanded = diretrizExpandida
                        )
                    },
                    shape = RoundedCornerShape(14.dp)
                )

                ExposedDropdownMenu(
                    expanded = diretrizExpandida,
                    onDismissRequest = {
                        diretrizExpandida = false
                    }
                ) {

                    diretrizes.forEach { diretriz ->

                        DropdownMenuItem(
                            text = {
                                Text(diretriz)
                            },
                            onClick = {

                                diretrizSelecionada = diretriz
                                diretrizExpandida = false
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(36.dp))

            Button(
                onClick = {

                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(58.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = AzulPrincipal
                ),
                shape = RoundedCornerShape(16.dp)
            ) {

                Text(
                    text = "Enviar ideia",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}
