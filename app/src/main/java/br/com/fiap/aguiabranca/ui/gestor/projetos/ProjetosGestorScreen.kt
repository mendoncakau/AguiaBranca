package br.com.fiap.aguiabranca.ui.gestor.projetos

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import br.com.fiap.aguiabranca.ui.gestor.ProjetoGestorItem

private val Azul = Color(0xFF0047C7)
private val AzulEscuro = Color(0xFF06143A)
private val Verde = Color(0xFF00995D)
private val Vermelho = Color(0xFFE53935)
private val Amarelo = Color(0xFFFFA000)
private val CinzaTexto = Color(0xFF5C6680)
private val Fundo = Color(0xFFF7F9FC)
private val Borda = Color(0xFFE8ECF4)

@Composable
fun ProjetosGestorScreen(
    modifier: Modifier = Modifier,
    projetos: List<ProjetoGestorItem>,
    onEditarProjeto: (Int) -> Unit = {},
    onAbrirProgresso: () -> Unit = {},
    onCriarProjeto: () -> Unit = {}
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(Fundo),
        contentPadding = PaddingValues(horizontal = 18.dp, vertical = 18.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        item {
            HeaderProjetos()
        }

        itemsIndexed(projetos) { index, projeto ->
            CardProjetoGestor(
                ordem = index + 1,
                titulo = projeto.nome,
                responsavel = projeto.responsavel,
                prioridade = projeto.prioridade,
                status = projeto.status,
                progresso = projeto.progresso,
                onEditarProjeto = {
                    onEditarProjeto(index)
                },
                onAbrirProgresso = onAbrirProgresso
            )
        }

        item {
            Button(
                onClick = onCriarProjeto,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Azul)
            ) {
                Icon(
                    imageVector = Icons.Outlined.Add,
                    contentDescription = null
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = "Criar projeto",
                    fontWeight = FontWeight.Bold
                )
            }
        }

        item {
            Spacer(modifier = Modifier.height(90.dp))
        }
    }
}

@Composable
private fun HeaderProjetos() {
    Text(
        text = "Projetos",
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        color = AzulEscuro
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun CardProjetoGestor(
    ordem: Int,
    titulo: String,
    responsavel: String,
    prioridade: String,
    status: String,
    progresso: Float,
    onEditarProjeto: () -> Unit,
    onAbrirProgresso: () -> Unit
) {
    val corPrioridade = when (prioridade) {
        "Alta" -> Vermelho
        "Média" -> Amarelo
        else -> Verde
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .combinedClickable(
                onClick = {},
                onDoubleClick = { onAbrirProgresso() }
            ),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top
            ) {
                Text(
                    text = ordem.toString(),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = AzulEscuro,
                    modifier = Modifier.width(30.dp)
                )

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = titulo,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = AzulEscuro
                    )

                    Text(
                        text = responsavel,
                        fontSize = 12.sp,
                        color = CinzaTexto
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = status,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = Azul
                    )
                }

                IconButton(onClick = onEditarProjeto) {
                    Icon(
                        imageVector = Icons.Outlined.Edit,
                        contentDescription = null,
                        tint = Azul
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                LinearProgressIndicator(
                    progress = { progresso },
                    modifier = Modifier
                        .weight(1f)
                        .height(7.dp),
                    color = Azul,
                    trackColor = Borda
                )

                Spacer(modifier = Modifier.width(10.dp))

                Text(
                    text = "${(progresso * 100).toInt()}%",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = CinzaTexto
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = prioridade,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = corPrioridade
            )
        }
    }
}