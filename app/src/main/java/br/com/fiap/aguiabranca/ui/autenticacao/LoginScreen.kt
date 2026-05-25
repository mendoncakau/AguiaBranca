package br.com.fiap.aguiabranca.ui.autenticacao

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.com.fiap.aguiabranca.R
import br.com.fiap.aguiabranca.ui.theme.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun LoginScreen(
    navController: NavHostController
) {

    val context = LocalContext.current

    val auth = FirebaseAuth.getInstance()

    val firestore = FirebaseFirestore.getInstance()

    var email by remember {
        mutableStateOf("")
    }

    var senha by remember {
        mutableStateOf("")
    }

    var carregando by remember {
        mutableStateOf(false)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Fundo)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 28.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(70.dp))

            // LOGO

            Image(
                painter = painterResource(
                    id = R.drawable.logo_inovabox
                ),
                contentDescription = null,
                modifier = Modifier
                    .width(250.dp)
                    .height(120.dp)
            )

            Spacer(modifier = Modifier.height(40.dp))

            Text(
                text = "Bem-vindo!",
                fontSize = 34.sp,
                fontWeight = FontWeight.Bold,
                color = AzulPrincipal
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Faça login para continuar",
                fontSize = 18.sp,
                color = CinzaTexto
            )

            Spacer(modifier = Modifier.height(42.dp))

            // EMAIL

            OutlinedTextField(
                value = email,

                onValueChange = {
                    email = it
                },

                modifier = Modifier.fillMaxWidth(),

                shape = RoundedCornerShape(18.dp),

                singleLine = true,

                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email
                ),

                leadingIcon = {

                    Icon(
                        imageVector = Icons.Outlined.Email,
                        contentDescription = null,
                        tint = AzulPrincipal
                    )
                },

                placeholder = {
                    Text("E-mail")
                },

                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = AzulPrincipal,
                    unfocusedBorderColor =
                        CinzaTexto.copy(alpha = 0.4f)
                )
            )

            Spacer(modifier = Modifier.height(22.dp))

            // SENHA

            OutlinedTextField(
                value = senha,

                onValueChange = {
                    senha = it
                },

                modifier = Modifier.fillMaxWidth(),

                shape = RoundedCornerShape(18.dp),

                singleLine = true,

                visualTransformation =
                    PasswordVisualTransformation(),

                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password
                ),

                leadingIcon = {

                    Icon(
                        imageVector = Icons.Outlined.Lock,
                        contentDescription = null,
                        tint = AzulPrincipal
                    )
                },

                placeholder = {
                    Text("Senha")
                },

                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = AzulPrincipal,
                    unfocusedBorderColor =
                        CinzaTexto.copy(alpha = 0.4f)
                )
            )

            Spacer(modifier = Modifier.height(10.dp))

            // ESQUECI SENHA

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {

                TextButton(

                    onClick = {

                        if (email.isBlank()) {

                            Toast.makeText(
                                context,
                                "Digite seu e-mail primeiro",
                                Toast.LENGTH_SHORT
                            ).show()

                        } else {

                            auth.sendPasswordResetEmail(
                                email.trim()
                            )
                                .addOnSuccessListener {

                                    Toast.makeText(
                                        context,
                                        "Email de recuperação enviado",
                                        Toast.LENGTH_LONG
                                    ).show()
                                }

                                .addOnFailureListener {

                                    Toast.makeText(
                                        context,
                                        "Erro ao enviar email",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                        }
                    }

                ) {

                    Text(
                        text = "Esqueci minha senha",
                        color = AzulPrincipal
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // BOTÃO LOGIN

            Button(

                onClick = {

                    if (
                        email.isBlank() ||
                        senha.isBlank()
                    ) {

                        Toast.makeText(
                            context,
                            "Preencha todos os campos",
                            Toast.LENGTH_SHORT
                        ).show()

                        return@Button
                    }

                    carregando = true

                    auth.signInWithEmailAndPassword(
                        email.trim(),
                        senha.trim()
                    )

                        .addOnSuccessListener { result ->

                            val uid =
                                result.user?.uid ?: ""

                            firestore.collection("users")
                                .document(uid)
                                .get()

                                .addOnSuccessListener { document ->

                                    carregando = false

                                    if (!document.exists()) {

                                        Toast.makeText(
                                            context,
                                            "Usuário sem permissão",
                                            Toast.LENGTH_SHORT
                                        ).show()

                                        return@addOnSuccessListener
                                    }

                                    val tipo =
                                        document.getString("tipo")

                                    when (tipo) {

                                        "operador" -> {

                                            navController.navigate(
                                                "homeOperador"
                                            ) {

                                                popUpTo("login") {
                                                    inclusive = true
                                                }
                                            }
                                        }

                                        "gestor" -> {

                                            navController.navigate(
                                                "homeGestor"
                                            )
                                        }

                                        "lider" -> {

                                            navController.navigate(
                                                "homeLider"
                                            )
                                        }

                                        else -> {

                                            Toast.makeText(
                                                context,
                                                "Tipo de usuário inválido",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }
                                    }
                                }
                        }

                        .addOnFailureListener {

                            carregando = false

                            Toast.makeText(
                                context,
                                "Email ou senha inválidos",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                },

                modifier = Modifier
                    .fillMaxWidth()
                    .height(62.dp)
                    .shadow(
                        elevation = 8.dp,
                        shape = RoundedCornerShape(18.dp)
                    ),

                shape = RoundedCornerShape(18.dp),

                colors = ButtonDefaults.buttonColors(
                    containerColor = AzulPrincipal
                )
            ) {

                if (carregando) {

                    CircularProgressIndicator(
                        color = Branco
                    )

                } else {

                    Text(
                        text = "Entrar",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = Branco
                    )
                }
            }

            Spacer(modifier = Modifier.height(26.dp))

            Text(
                text = "Cadastro realizado pela empresa",
                color = CinzaTexto,
                fontSize = 15.sp
            )
        }
    }

}
