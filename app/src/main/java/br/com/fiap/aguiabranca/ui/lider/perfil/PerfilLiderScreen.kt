package br.com.fiap.aguiabranca.ui.lider.perfil

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.outlined.Badge
import androidx.compose.material.icons.outlined.Business
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.WorkOutline
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
import br.com.fiap.aguiabranca.ui.theme.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

private val AzulEscuro = Color(0xFF0D47A1)

@Composable
fun PerfilLiderScreen(
    modifier: Modifier = Modifier,
    onLogout: () -> Unit = {}
) {

    val auth = FirebaseAuth.getInstance()

    val firestore = FirebaseFirestore.getInstance()

    var nomeUsuario by remember {
        mutableStateOf("Carregando...")
    }

    LaunchedEffect(Unit) {

        val uid = auth.currentUser?.uid

        if (uid != null) {

            firestore.collection("users")
                .document(uid)
                .get()

                .addOnSuccessListener { document ->

                    nomeUsuario =
                        document.getString("nome")
                            ?: "Usuário"
                }
        }
    }

    val iniciais = nomeUsuario
        .split(" ")
        .mapNotNull {
            it.firstOrNull()?.toString()
        }
        .take(2)
        .joinToString("")
        .uppercase()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(FundoTela)
            .verticalScroll(rememberScrollState())
    ) {

        // HEADER

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(260.dp)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            AzulAguaBranca,
                            AzulEscuro
                        )
                    )
                )
        ) {

            IconButton(

                onClick = {

                    auth.signOut()

                    onLogout()
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

                    Box(
                        modifier = Modifier
                            .size(95.dp)
                            .clip(CircleShape)
                            .background(Branco),

                        contentAlignment = Alignment.Center
                    ) {

                        Text(
                            text = iniciais,
                            fontSize = 34.sp,
                            fontWeight = FontWeight.Bold,
                            color = AzulAguaBranca
                        )
                    }

                    Spacer(modifier = Modifier.width(18.dp))

                    Column {

                        Text(
                            text = nomeUsuario,
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold,
                            color = Branco
                        )

                        Spacer(modifier = Modifier.height(6.dp))

                        Text(
                            text = "Líder Operacional",
                            fontSize = 18.sp,
                            color = Branco.copy(alpha = 0.9f)
                        )

                        Spacer(modifier = Modifier.height(14.dp))

                        Surface(
                            shape = RoundedCornerShape(50.dp),
                            color = Branco.copy(alpha = 0.18f)
                        ) {

                            Text(
                                text = "Liderança Estratégica",

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

        // CARD INFORMAÇÕES

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
                    text = "Informações do líder",
                    fontSize = 23.sp,
                    fontWeight = FontWeight.Bold,
                    color = PretoTexto
                )

                Spacer(modifier = Modifier.height(24.dp))

                ItemLider(
                    icon = {

                        Icon(
                            imageVector = Icons.Outlined.Badge,
                            contentDescription = null,
                            tint = AzulAguaBranca
                        )
                    },

                    titulo = "Matrícula",
                    valor = "LD-2026-0027"
                )

                Spacer(modifier = Modifier.height(18.dp))

                ItemLider(
                    icon = {

                        Icon(
                            imageVector = Icons.Outlined.Business,
                            contentDescription = null,
                            tint = AzulAguaBranca
                        )
                    },

                    titulo = "Unidade",
                    valor = "Garagem Barra Funda"
                )

                Spacer(modifier = Modifier.height(18.dp))

                ItemLider(
                    icon = {

                        Icon(
                            imageVector = Icons.Outlined.Person,
                            contentDescription = null,
                            tint = AzulAguaBranca
                        )
                    },

                    titulo = "Equipe",
                    valor = "Operações e Manutenção"
                )

                Spacer(modifier = Modifier.height(18.dp))

                ItemLider(
                    icon = {

                        Icon(
                            imageVector = Icons.Outlined.WorkOutline,
                            contentDescription = null,
                            tint = AzulAguaBranca
                        )
                    },

                    titulo = "Cargo",
                    valor = "Líder Senior"
                )
            }
        }

        Spacer(modifier = Modifier.height(34.dp))

        // CARD RESUMO

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
                    text = "Resumo de liderança",
                    fontSize = 23.sp,
                    fontWeight = FontWeight.Bold,
                    color = PretoTexto
                )

                Spacer(modifier = Modifier.height(24.dp))

                LinhaResumoLider(
                    titulo = "Ideias acompanhadas",
                    valor = "24"
                )

                Spacer(modifier = Modifier.height(18.dp))

                LinhaResumoLider(
                    titulo = "Projetos ativos",
                    valor = "8"
                )

                Spacer(modifier = Modifier.height(18.dp))

                LinhaResumoLider(
                    titulo = "ROI operacional",
                    valor = "R$ 210 mil"
                )

                Spacer(modifier = Modifier.height(18.dp))

                LinhaResumoLider(
                    titulo = "Eficiência operacional",
                    valor = "+31%"
                )
            }
        }

        Spacer(modifier = Modifier.height(120.dp))
    }
}

@Composable
fun ItemLider(
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
                    color = AzulAguaBranca.copy(alpha = 0.12f),
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
fun LinhaResumoLider(
    titulo: String,
    valor: String
) {

    Row(
        modifier = Modifier.fillMaxWidth(),

        horizontalArrangement = Arrangement.SpaceBetween,

        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = titulo,
            fontSize = 17.sp,
            color = PretoTexto
        )

        Surface(
            shape = RoundedCornerShape(50.dp),
            color = AzulAguaBranca.copy(alpha = 0.12f)
        ) {

            Text(
                text = valor,

                modifier = Modifier.padding(
                    horizontal = 14.dp,
                    vertical = 8.dp
                ),

                color = AzulAguaBranca,
                fontWeight = FontWeight.Bold
            )
        }
    }
}