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
import br.com.fiap.aguiabranca.ui.lider.TelaLider
import br.com.fiap.aguiabranca.ui.theme.FundoTela
import br.com.fiap.aguiabranca.ui.theme.PretoTexto
import br.com.fiap.aguiabranca.viewmodel.LiderViewModel

@Composable
fun DiretrizesLiderScreen(
    viewModel: LiderViewModel,
    telaAtual: TelaLider,
    onTelaSelecionada: (TelaLider) -> Unit
) {
    val diretrizes by viewModel.diretrizes.collectAsState()

    var abrirDialog by remember { mutableStateOf(false) }

    var editandoId by remember { mutableStateOf<Int?>(null) }

    var titulo by remember { mutableStateOf("") }

    var descricao by remember { mutableStateOf("") }

    var diretrizParaExcluir by remember { mutableStateOf<Int?>(null) }

    Scaffold(
        bottomBar = {
            BottomBarLider(
                telaAtual = telaAtual,
                onTelaSelecionada = onTelaSelecionada
            )
        },
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
                onClick = {
                    editandoId = null
                    titulo = ""
                    descricao = ""
                    abrirDialog = true
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = RoxoLider
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    "Nova diretriz",
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

                    onEditar = {
                        editandoId = diretriz.id
                        titulo = diretriz.titulo
                        descricao = diretriz.descricao
                        abrirDialog = true
                    },

                    onExcluir = {
                        diretrizParaExcluir = diretriz.id
                    }
                )
            }

            Spacer(modifier = Modifier.height(20.dp))
        }

        if (abrirDialog) {

            AlertDialog(

                onDismissRequest = {
                    abrirDialog = false
                },

                confirmButton = {

                    Button(
                        onClick = {

                            if (editandoId == null) {

                                viewModel.adicionarDiretriz(
                                    titulo,
                                    descricao
                                )

                            } else {

                                viewModel.editarDiretriz(
                                    editandoId!!,
                                    titulo,
                                    descricao
                                )
                            }

                            abrirDialog = false
                        }
                    ) {
                        Text("Salvar")
                    }
                },

                dismissButton = {

                    TextButton(
                        onClick = {
                            abrirDialog = false
                        }
                    ) {
                        Text("Cancelar")
                    }
                },

                title = {
                    Text(
                        if (editandoId == null)
                            "Nova diretriz"
                        else
                            "Editar diretriz"
                    )
                },

                text = {

                    Column {

                        OutlinedTextField(
                            value = titulo,
                            onValueChange = {
                                titulo = it
                            },
                            label = {
                                Text("Título")
                            },
                            modifier = Modifier.fillMaxWidth()
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        OutlinedTextField(
                            value = descricao,
                            onValueChange = {
                                descricao = it
                            },
                            label = {
                                Text("Descrição")
                            },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            )
        }
        if (diretrizParaExcluir != null) {

            AlertDialog(
                onDismissRequest = {
                    diretrizParaExcluir = null
                },

                title = {
                    Text("Excluir diretriz")
                },

                text = {
                    Text(
                        "Tem certeza que deseja remover esta diretriz?"
                    )
                },

                confirmButton = {
                    Button(
                        onClick = {
                            viewModel.excluirDiretriz(
                                diretrizParaExcluir!!
                            )

                            diretrizParaExcluir = null
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.error
                        )
                    ) {
                        Text("Excluir")
                    }
                },

                dismissButton = {
                    TextButton(
                        onClick = {
                            diretrizParaExcluir = null
                        }
                    ) {
                        Text("Cancelar")
                    }
                }
            )
        }
    }
}