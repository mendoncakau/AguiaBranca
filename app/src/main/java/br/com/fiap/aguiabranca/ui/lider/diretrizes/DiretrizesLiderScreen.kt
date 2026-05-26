package br.com.fiap.aguiabranca.ui.lider.diretrizes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import br.com.fiap.aguiabranca.ui.components.CardDiretriz
import br.com.fiap.aguiabranca.ui.components.TopoTelaLider
import br.com.fiap.aguiabranca.ui.theme.*
import br.com.fiap.aguiabranca.viewmodel.LiderViewModel
import kotlinx.coroutines.launch

@Composable
fun DiretrizesLiderScreen(
    modifier: Modifier = Modifier,
    viewModel: LiderViewModel
) {

    val diretrizes by viewModel.diretrizes.collectAsState()

    val snackbarHostState = remember {
        SnackbarHostState()
    }

    val scope = rememberCoroutineScope()

    var mostrarDialogNova by remember {
        mutableStateOf(false)
    }

    var mostrarDialogEditar by remember {
        mutableStateOf(false)
    }

    var titulo by remember {
        mutableStateOf("")
    }

    var descricao by remember {
        mutableStateOf("")
    }

    var diretrizEditandoId by remember {
        mutableStateOf<Int?>(null)
    }

    var diretrizParaExcluir by remember {
        mutableStateOf<Int?>(null)
    }

    var categoria by remember {
        mutableStateOf("Estratégica")
    }

    var prioridade by remember {
        mutableStateOf("Alta prioridade")
    }

    Scaffold(

        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },

        containerColor = FundoTela

    ) { paddingValues ->

        Column(
            modifier = modifier
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

                    titulo = ""
                    descricao = ""

                    mostrarDialogNova = true
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

                    categoria = diretriz.categoria,

                    prioridade = diretriz.prioridade,

                    onEditar = {

                        diretrizEditandoId = diretriz.id

                        titulo = diretriz.titulo
                        descricao = diretriz.descricao

                        categoria = diretriz.categoria

                        prioridade = diretriz.prioridade

                        mostrarDialogEditar = true
                    },

                    onExcluir = {

                        diretrizParaExcluir = diretriz.id
                    }
                )
            }

            Spacer(modifier = Modifier.height(120.dp))
        }
    }

    // =========================
    // NOVA DIRETRIZ
    // =========================

    if (mostrarDialogNova) {

        AlertDialog(

            onDismissRequest = {

                mostrarDialogNova = false
            },

            confirmButton = {

                TextButton(

                    onClick = {

                        viewModel.adicionarDiretriz(
                            titulo,
                            descricao,
                            categoria,
                            prioridade
                        )

                        mostrarDialogNova = false

                        scope.launch {

                            snackbarHostState.showSnackbar(
                                "✓ Diretriz criada com sucesso"
                            )
                        }
                    }
                ) {

                    Text("Salvar")
                }
            },

            dismissButton = {

                TextButton(

                    onClick = {

                        mostrarDialogNova = false
                    }
                ) {

                    Text("Cancelar")
                }
            },

            title = {

                Text("Nova diretriz")
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

                    Spacer(
                        modifier = Modifier.height(12.dp)
                    )

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

    // =========================
    // EDITAR DIRETRIZ
    // =========================

    if (mostrarDialogEditar) {

        AlertDialog(

            onDismissRequest = {

                mostrarDialogEditar = false
            },

            confirmButton = {

                TextButton(

                    onClick = {

                        diretrizEditandoId?.let {

                            viewModel.editarDiretriz(
                                diretrizEditandoId!!,
                                titulo,
                                descricao,
                                categoria,
                                prioridade
                            )
                        }

                        mostrarDialogEditar = false

                        scope.launch {

                            snackbarHostState.showSnackbar(
                                "✓ Diretriz atualizada com sucesso"
                            )
                        }
                    }
                ) {

                    Text("Salvar")
                }
            },

            dismissButton = {

                TextButton(

                    onClick = {

                        mostrarDialogEditar = false
                    }
                ) {

                    Text("Cancelar")
                }
            },

            title = {

                Text("Editar diretriz")
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

                    Spacer(
                        modifier = Modifier.height(12.dp)
                    )

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

    // =========================
    // EXCLUIR DIRETRIZ
    // =========================

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

                TextButton(

                    onClick = {

                        viewModel.excluirDiretriz(
                            diretrizParaExcluir!!
                        )

                        diretrizParaExcluir = null

                        scope.launch {

                            snackbarHostState.showSnackbar(
                                "✓ Diretriz removida com sucesso"
                            )
                        }
                    }
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