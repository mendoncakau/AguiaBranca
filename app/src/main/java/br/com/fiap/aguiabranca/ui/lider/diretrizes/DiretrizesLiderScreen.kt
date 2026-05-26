package br.com.fiap.aguiabranca.ui.lider.diretrizes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.filled.PriorityHigh
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.aguiabranca.ui.components.CardDiretriz
import br.com.fiap.aguiabranca.ui.theme.*
import br.com.fiap.aguiabranca.viewmodel.LiderViewModel
import kotlinx.coroutines.launch

private val RoxoEscuro = Color(0xFF4A148C)

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

    var categoria by remember {
        mutableStateOf("Estratégica")
    }

    var prioridade by remember {
        mutableStateOf("Alta prioridade")
    }

    var diretrizEditandoId by remember {
        mutableStateOf<Int?>(null)
    }

    var diretrizParaExcluir by remember {
        mutableStateOf<Int?>(null)
    }

    Scaffold(

        snackbarHost = {
            SnackbarHost(snackbarHostState)
        },

        containerColor = FundoTela

    ) { paddingValues ->

        Column(
            modifier = modifier
                .fillMaxSize()
                .background(FundoTela)
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                RoxoLider,
                                RoxoEscuro
                            )
                        )
                    )
                    .padding(
                        horizontal = 22.dp,
                        vertical = 30.dp
                    )
            ) {

                Column {

                    Text(
                        text = "Diretrizes Estratégicas",
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        color = Branco
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Gerencie prioridades, metas e direcionamentos da empresa.",
                        fontSize = 16.sp,
                        color = Branco.copy(alpha = 0.9f)
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {

                        CardInfo(
                            titulo = "Diretrizes",
                            valor = diretrizes.size.toString()
                        )

                        CardInfo(
                            titulo = "Alta prioridade",
                            valor = diretrizes.count {
                                it.prioridade == "Alta prioridade"
                            }.toString()
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(22.dp))

            Column(
                modifier = Modifier.padding(horizontal = 18.dp)
            ) {

                Card(
                    modifier = Modifier.fillMaxWidth(),

                    shape = RoundedCornerShape(28.dp),

                    colors = CardDefaults.cardColors(
                        containerColor = Branco
                    )
                ) {

                    Column(
                        modifier = Modifier.padding(22.dp)
                    ) {

                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Box(
                                modifier = Modifier
                                    .size(54.dp)
                                    .background(
                                        RoxoLider.copy(alpha = 0.12f),
                                        CircleShape
                                    ),

                                contentAlignment = Alignment.Center
                            ) {

                                Icon(
                                    imageVector = Icons.Default.Add,
                                    contentDescription = null,
                                    tint = RoxoLider
                                )
                            }

                            Spacer(modifier = Modifier.width(14.dp))

                            Column {

                                Text(
                                    text = "Nova diretriz",
                                    fontSize = 22.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = PretoTexto
                                )

                                Spacer(modifier = Modifier.height(4.dp))

                                Text(
                                    text = "Crie novos direcionamentos estratégicos",
                                    color = CinzaTexto,
                                    fontSize = 14.sp
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(22.dp))

                        Button(

                            onClick = {

                                titulo = ""
                                descricao = ""

                                categoria = "Estratégica"
                                prioridade = "Alta prioridade"

                                mostrarDialogNova = true
                            },

                            modifier = Modifier
                                .fillMaxWidth()
                                .height(56.dp),

                            shape = RoundedCornerShape(18.dp),

                            colors = ButtonDefaults.buttonColors(
                                containerColor = RoxoLider
                            )
                        ) {

                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = null
                            )

                            Spacer(modifier = Modifier.width(8.dp))

                            Text(
                                text = "Criar diretriz",
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(28.dp))

                Text(
                    text = "Diretrizes cadastradas",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = PretoTexto
                )

                Spacer(modifier = Modifier.height(16.dp))

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

                    Spacer(modifier = Modifier.height(16.dp))
                }

                Spacer(modifier = Modifier.height(120.dp))
            }
        }
    }

    if (mostrarDialogNova) {

        DialogDiretriz(
            tituloDialog = "Nova diretriz",

            titulo = titulo,
            descricao = descricao,

            categoria = categoria,
            prioridade = prioridade,

            onTituloChange = {
                titulo = it
            },

            onDescricaoChange = {
                descricao = it
            },

            onCategoriaChange = {
                categoria = it
            },

            onPrioridadeChange = {
                prioridade = it
            },

            onDismiss = {
                mostrarDialogNova = false
            },

            onSalvar = {

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
        )
    }

    if (mostrarDialogEditar) {

        DialogDiretriz(
            tituloDialog = "Editar diretriz",

            titulo = titulo,
            descricao = descricao,

            categoria = categoria,
            prioridade = prioridade,

            onTituloChange = {
                titulo = it
            },

            onDescricaoChange = {
                descricao = it
            },

            onCategoriaChange = {
                categoria = it
            },

            onPrioridadeChange = {
                prioridade = it
            },

            onDismiss = {
                mostrarDialogEditar = false
            },

            onSalvar = {

                diretrizEditandoId?.let {

                    viewModel.editarDiretriz(
                        it,
                        titulo,
                        descricao,
                        categoria,
                        prioridade
                    )
                }

                mostrarDialogEditar = false

                scope.launch {

                    snackbarHostState.showSnackbar(
                        "✓ Diretriz atualizada"
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

            shape = RoundedCornerShape(28.dp),

            title = {

                Text(
                    text = "Excluir diretriz",
                    fontWeight = FontWeight.Bold
                )
            },

            text = {

                Text(
                    text = "Tem certeza que deseja remover esta diretriz?"
                )
            },

            confirmButton = {

                Button(

                    onClick = {

                        viewModel.excluirDiretriz(
                            diretrizParaExcluir!!
                        )

                        diretrizParaExcluir = null

                        scope.launch {

                            snackbarHostState.showSnackbar(
                                "✓ Diretriz removida"
                            )
                        }
                    },

                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Red
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

@Composable
fun DialogDiretriz(

    tituloDialog: String,

    titulo: String,
    descricao: String,

    categoria: String,
    prioridade: String,

    onTituloChange: (String) -> Unit,
    onDescricaoChange: (String) -> Unit,

    onCategoriaChange: (String) -> Unit,
    onPrioridadeChange: (String) -> Unit,

    onDismiss: () -> Unit,
    onSalvar: () -> Unit
) {

    AlertDialog(

        onDismissRequest = onDismiss,

        shape = RoundedCornerShape(30.dp),

        title = {

            Text(
                text = tituloDialog,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )
        },

        text = {

            Column {

                OutlinedTextField(
                    value = titulo,

                    onValueChange = onTituloChange,

                    label = {
                        Text("Título")
                    },

                    modifier = Modifier.fillMaxWidth(),

                    shape = RoundedCornerShape(18.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = descricao,

                    onValueChange = onDescricaoChange,

                    label = {
                        Text("Descrição")
                    },

                    modifier = Modifier.fillMaxWidth(),

                    minLines = 4,

                    shape = RoundedCornerShape(18.dp)
                )

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "Categoria",
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {

                    AssistChip(
                        onClick = {
                            onCategoriaChange("Estratégica")
                        },

                        label = {
                            Text("Estratégica")
                        },

                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Flag,
                                contentDescription = null
                            )
                        }
                    )

                    AssistChip(
                        onClick = {
                            onCategoriaChange("Operacional")
                        },

                        label = {
                            Text("Operacional")
                        }
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "Prioridade",
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {

                    AssistChip(
                        onClick = {
                            onPrioridadeChange("Alta prioridade")
                        },

                        label = {
                            Text("Alta")
                        },

                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.PriorityHigh,
                                contentDescription = null
                            )
                        }
                    )

                    AssistChip(
                        onClick = {
                            onPrioridadeChange("Média prioridade")
                        },

                        label = {
                            Text("Média")
                        }
                    )

                    AssistChip(
                        onClick = {
                            onPrioridadeChange("Baixa prioridade")
                        },

                        label = {
                            Text("Baixa")
                        }
                    )
                }
            }
        },

        confirmButton = {

            Button(
                onClick = onSalvar,

                colors = ButtonDefaults.buttonColors(
                    containerColor = RoxoLider
                ),

                shape = RoundedCornerShape(16.dp)
            ) {

                Text("Salvar")
            }
        },

        dismissButton = {

            TextButton(
                onClick = onDismiss
            ) {

                Text("Cancelar")
            }
        }
    )
}

@Composable
fun CardInfo(
    titulo: String,
    valor: String
) {

    Card(

        shape = RoundedCornerShape(22.dp),

        colors = CardDefaults.cardColors(
            containerColor = Branco.copy(alpha = 0.15f)
        )
    ) {

        Column(
            modifier = Modifier.padding(
                horizontal = 22.dp,
                vertical = 16.dp
            )
        ) {

            Text(
                text = valor,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Branco
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = titulo,
                color = Branco.copy(alpha = 0.9f)
            )
        }
    }
}