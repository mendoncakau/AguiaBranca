package br.com.fiap.aguiabranca.ui.operador.perfil

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.outlined.Badge
import androidx.compose.material.icons.outlined.Business
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.com.fiap.aguiabranca.ui.operador.home.components.BottomBarOperador
import br.com.fiap.aguiabranca.ui.theme.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

data class SeloModel(
    val emoji: String,
    val titulo: String,
    val descricao: String
)

private val AzulEscuro = Color(0xFF0D47A1)

@Composable
fun PerfilOperadorScreen(
    navController: NavHostController
) {

    val auth = FirebaseAuth.getInstance()
    val firestore = FirebaseFirestore.getInstance()

    val currentUser = auth.currentUser

    var nomeUsuario by remember {
        mutableStateOf("Carregando...")
    }

    // BUSCAR NOME NO FIRESTORE

    LaunchedEffect(Unit) {

        currentUser?.uid?.let { uid ->

            firestore
                .collection("users")
                .document(uid)
                .get()
                .addOnSuccessListener { document ->

                    nomeUsuario =
                        document.getString("nome")
                            ?: "Operador"
                }
                .addOnFailureListener {

                    nomeUsuario = "Operador"
                }
        }
    }

    // INICIAIS AUTOMÁTICAS

    val iniciais = remember(nomeUsuario) {

        nomeUsuario
            .split(" ")
            .take(2)
            .mapNotNull {
                it.firstOrNull()?.toString()
            }
            .joinToString("")
            .uppercase()
    }

    val selos = listOf(

        SeloModel(
            emoji = "🚀",
            titulo = "Iniciante",
            descricao = "Primeira ideia enviada"
        ),

        SeloModel(
            emoji = "🏆",
            titulo = "Colaborador",
            descricao = "5 ideias cadastradas"
        ),

        SeloModel(
            emoji = "💡",
            titulo = "Criativo",
            descricao = "Ideia aprovada"
        ),

        SeloModel(
            emoji = "⭐",
            titulo = "Impacto",
            descricao = "Projeto implementado"
        )
    )

    Scaffold(

        containerColor = Fundo,

        bottomBar = {

            BottomBarOperador(
                navController = navController
            )
        }

    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
        ) {

            // HEADER

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(280.dp)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                AzulPrincipal,
                                AzulEscuro
                            )
                        )
                    )
            ) {

                // BOTÃO SAIR

                IconButton(

                    onClick = {

                        auth.signOut()

                        navController.navigate("login") {

                            popUpTo(0)
                        }
                    },

                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(16.dp)
                ) {

                    Icon(
                        imageVector = Icons.Default.Logout,
                        contentDescription = null,
                        tint = Branco
                    )
                }

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp),

                    verticalArrangement = Arrangement.Center
                ) {

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        // FOTO / INICIAIS

                        Box(
                            modifier = Modifier
                                .size(100.dp)
                                .clip(CircleShape)
                                .background(Branco),

                            contentAlignment = Alignment.Center
                        ) {

                            Text(
                                text = iniciais,
                                fontSize = 34.sp,
                                fontWeight = FontWeight.Bold,
                                color = AzulPrincipal
                            )
                        }

                        Spacer(modifier = Modifier.width(18.dp))

                        Column {

                            // NOME DINÂMICO

                            Text(
                                text = nomeUsuario,
                                fontSize = 30.sp,
                                fontWeight = FontWeight.Bold,
                                color = Branco
                            )

                            Spacer(modifier = Modifier.height(6.dp))

                            Text(
                                text = "Operador Logístico",
                                fontSize = 18.sp,
                                color = Branco.copy(alpha = 0.9f)
                            )

                            Spacer(modifier = Modifier.height(14.dp))

                            Surface(
                                shape = RoundedCornerShape(50.dp),
                                color = Branco.copy(alpha = 0.18f)
                            ) {

                                Text(
                                    text = "Colaborador Destaque",

                                    modifier = Modifier.padding(
                                        horizontal = 16.dp,
                                        vertical = 8.dp
                                    ),

                                    color = Branco,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // CARD FUNCIONÁRIO

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),

                shape = RoundedCornerShape(26.dp),

                colors = CardDefaults.cardColors(
                    containerColor = Branco
                )
            ) {

                Column(
                    modifier = Modifier.padding(22.dp)
                ) {

                    Text(
                        text = "Informações do funcionário",
                        fontSize = 23.sp,
                        fontWeight = FontWeight.Bold,
                        color = PretoTexto
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    ItemFuncionario(
                        icon = {

                            Icon(
                                imageVector = Icons.Outlined.Badge,
                                contentDescription = null,
                                tint = AzulPrincipal
                            )
                        },

                        titulo = "Matrícula",
                        valor = "AB-2026-0192"
                    )

                    Spacer(modifier = Modifier.height(18.dp))

                    ItemFuncionario(
                        icon = {

                            Icon(
                                imageVector = Icons.Outlined.Business,
                                contentDescription = null,
                                tint = AzulPrincipal
                            )
                        },

                        titulo = "Unidade",
                        valor = "Garagem Barra Funda"
                    )

                    Spacer(modifier = Modifier.height(18.dp))

                    ItemFuncionario(
                        icon = {

                            Icon(
                                imageVector = Icons.Outlined.Person,
                                contentDescription = null,
                                tint = AzulPrincipal
                            )
                        },

                        titulo = "Setor",
                        valor = "Operações"
                    )
                }
            }

            Spacer(modifier = Modifier.height(34.dp))

            // TÍTULO SELOS

            Text(
                text = "Meus selos",

                modifier = Modifier.padding(horizontal = 20.dp),

                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = PretoTexto
            )

            Spacer(modifier = Modifier.height(18.dp))

            // GRID SELOS

            LazyVerticalGrid(

                columns = GridCells.Fixed(2),

                modifier = Modifier
                    .fillMaxWidth()
                    .height(430.dp)
                    .padding(horizontal = 20.dp),

                horizontalArrangement = Arrangement.spacedBy(16.dp),

                verticalArrangement = Arrangement.spacedBy(16.dp),

                userScrollEnabled = false
            ) {

                items(selos) { selo ->

                    CardSelo(
                        emoji = selo.emoji,
                        titulo = selo.titulo,
                        descricao = selo.descricao
                    )
                }
            }

            Spacer(modifier = Modifier.height(120.dp))
        }
    }
}

@Composable
fun ItemFuncionario(
    icon: @Composable () -> Unit,
    titulo: String,
    valor: String
) {

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier
                .size(48.dp)
                .background(
                    color = AzulPrincipal.copy(alpha = 0.12f),
                    shape = CircleShape
                ),

            contentAlignment = Alignment.Center
        ) {

            icon()
        }

        Spacer(modifier = Modifier.width(14.dp))

        Column {

            Text(
                text = titulo,
                fontSize = 14.sp,
                color = CinzaTexto
            )

            Spacer(modifier = Modifier.height(2.dp))

            Text(
                text = valor,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = PretoTexto
            )
        }
    }
}

@Composable
fun CardSelo(
    emoji: String,
    titulo: String,
    descricao: String
) {

    Card(

        shape = RoundedCornerShape(24.dp),

        colors = CardDefaults.cardColors(
            containerColor = Branco
        )
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),

            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Box(
                modifier = Modifier
                    .size(76.dp)
                    .background(
                        color = AzulPrincipal.copy(alpha = 0.12f),
                        shape = CircleShape
                    ),

                contentAlignment = Alignment.Center
            ) {

                Text(
                    text = emoji,
                    fontSize = 34.sp
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = titulo,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = PretoTexto
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = descricao,
                fontSize = 14.sp,
                color = CinzaTexto,
                lineHeight = 20.sp
            )
        }
    }
}
