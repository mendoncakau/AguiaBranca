package br.com.fiap.aguiabranca.ui.gestor.projetos

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.FolderOpen
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.PersonOutline
import androidx.compose.material.icons.outlined.QueryStats
import androidx.compose.material.icons.outlined.TaskAlt
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import br.com.fiap.aguiabranca.ui.gestor.ProjetoGestorItem

private val Azul = Color(0xFF0047C7)
private val AzulEscuro = Color(0xFF06143A)
private val AzulClaro = Color(0xFFEFF4FF)
private val Verde = Color(0xFF00995D)
private val Vermelho = Color(0xFFE53935)
private val Amarelo = Color(0xFFFFA000)
private val Roxo = Color(0xFF6C4DFF)
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

        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        item {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Azul,
                                AzulEscuro
                            )
                        )
                    )
                    .padding(
                        horizontal = 20.dp,
                        vertical = 28.dp
                    )
            ) {

                Column {

                    Text(
                        text = "Projetos Estratégicos",
                        fontSize = 31.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Gerencie iniciativas, acompanhe progresso e monitore resultados.",
                        fontSize = 16.sp,
                        color = Color.White.copy(alpha = 0.88f),
                        lineHeight = 24.sp
                    )

                    Spacer(modifier = Modifier.height(26.dp))

                    Button(
                        onClick = onCriarProjeto,

                        modifier = Modifier
                            .fillMaxWidth()
                            .height(58.dp),

                        shape = RoundedCornerShape(18.dp),

                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White
                        )
                    ) {

                        Icon(
                            imageVector = Icons.Outlined.Add,
                            contentDescription = null,
                            tint = Azul
                        )

                        Spacer(modifier = Modifier.width(10.dp))

                        Text(
                            text = "Criar novo projeto",
                            color = Azul,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {

                        CardResumoProjeto(
                            titulo = "Projetos",
                            valor = projetos.size.toString(),
                            icon = {
                                Icon(
                                    imageVector = Icons.Default.FolderOpen,
                                    contentDescription = null,
                                    tint = Azul
                                )
                            },
                            modifier = Modifier.weight(1f)
                        )

                        CardResumoProjeto(
                            titulo = "Concluídos",
                            valor = projetos.count {
                                it.progresso >= 1f
                            }.toString(),
                            icon = {
                                Icon(
                                    imageVector = Icons.Outlined.TaskAlt,
                                    contentDescription = null,
                                    tint = Verde
                                )
                            },
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }
        }

        item {

            Column(
                modifier = Modifier.padding(horizontal = 18.dp)
            ) {

                Text(
                    text = "Projetos em andamento",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = AzulEscuro
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = "Toque duas vezes para abrir progresso",
                    color = CinzaTexto,
                    fontSize = 14.sp
                )
            }
        }

        itemsIndexed(projetos) { index, projeto ->

            Box(
                modifier = Modifier.padding(horizontal = 18.dp)
            ) {

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
        }

        item {
            Spacer(modifier = Modifier.height(110.dp))
        }
    }
}

@Composable
fun CardResumoProjeto(
    titulo: String,
    valor: String,
    icon: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {

    Card(
        modifier = modifier,

        shape = RoundedCornerShape(22.dp),

        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {

        Column(
            modifier = Modifier.padding(18.dp)
        ) {

            Box(
                modifier = Modifier
                    .size(44.dp)
                    .clip(CircleShape)
                    .background(AzulClaro),

                contentAlignment = Alignment.Center
            ) {

                icon()
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = valor,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = AzulEscuro
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = titulo,
                color = CinzaTexto,
                fontSize = 14.sp
            )
        }
    }
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

    val corStatus = when (status) {
        "Concluído" -> Verde
        "Em andamento" -> Azul
        else -> Roxo
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .combinedClickable(
                onClick = {},
                onDoubleClick = {
                    onAbrirProgresso()
                }
            ),

        shape = RoundedCornerShape(28.dp),

        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),

        elevation = CardDefaults.cardElevation(
            defaultElevation = 3.dp
        )
    ) {

        Column(
            modifier = Modifier.padding(20.dp)
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top
            ) {

                Box(
                    modifier = Modifier
                        .size(52.dp)
                        .clip(CircleShape)
                        .background(AzulClaro),

                    contentAlignment = Alignment.Center
                ) {

                    Text(
                        text = ordem.toString(),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Azul
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                Column(
                    modifier = Modifier.weight(1f)
                ) {

                    Text(
                        text = titulo,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = AzulEscuro
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Icon(
                            imageVector = Icons.Outlined.PersonOutline,
                            contentDescription = null,
                            tint = CinzaTexto,
                            modifier = Modifier.size(18.dp)
                        )

                        Spacer(modifier = Modifier.width(6.dp))

                        Text(
                            text = responsavel,
                            color = CinzaTexto,
                            fontSize = 14.sp
                        )
                    }
                }

                IconButton(
                    onClick = onEditarProjeto
                ) {

                    Icon(
                        imageVector = Icons.Outlined.Edit,
                        contentDescription = null,
                        tint = Azul
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {

                Surface(
                    shape = RoundedCornerShape(50.dp),
                    color = corStatus.copy(alpha = 0.12f)
                ) {

                    Text(
                        text = status,

                        modifier = Modifier.padding(
                            horizontal = 14.dp,
                            vertical = 8.dp
                        ),

                        color = corStatus,
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp
                    )
                }

                Surface(
                    shape = RoundedCornerShape(50.dp),
                    color = corPrioridade.copy(alpha = 0.12f)
                ) {

                    Text(
                        text = "$prioridade prioridade",

                        modifier = Modifier.padding(
                            horizontal = 14.dp,
                            vertical = 8.dp
                        ),

                        color = corPrioridade,
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(22.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Icon(
                        imageVector = Icons.Outlined.QueryStats,
                        contentDescription = null,
                        tint = Azul,
                        modifier = Modifier.size(18.dp)
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = "Progresso",
                        fontWeight = FontWeight.SemiBold,
                        color = AzulEscuro
                    )
                }

                Text(
                    text = "${(progresso * 100).toInt()}%",
                    fontWeight = FontWeight.Bold,
                    color = AzulEscuro
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            LinearProgressIndicator(
                progress = { progresso },

                modifier = Modifier
                    .fillMaxWidth()
                    .height(10.dp)
                    .clip(RoundedCornerShape(50.dp)),

                color = Azul,

                trackColor = Borda
            )

            Spacer(modifier = Modifier.height(18.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(
                    imageVector = Icons.Default.AutoAwesome,
                    contentDescription = null,
                    tint = Roxo,
                    modifier = Modifier.size(18.dp)
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = "Toque duas vezes para abrir detalhes",
                    color = CinzaTexto,
                    fontSize = 13.sp
                )
            }
        }
    }
}