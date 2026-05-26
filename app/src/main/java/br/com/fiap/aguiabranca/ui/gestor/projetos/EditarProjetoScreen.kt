package br.com.fiap.aguiabranca.ui.gestor.projetos

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*

private val Azul = Color(0xFF0047C7)
private val AzulEscuro = Color(0xFF06143A)
private val Fundo = Color(0xFFF7F9FC)
private val Borda = Color(0xFFE8ECF4)

@Composable
fun EditarProjetoScreen(
    modifier: Modifier = Modifier,
    nomeInicial: String,
    responsavelInicial: String,
    prioridadeInicial: String,
    statusInicial: String,
    progressoInicial: Float,
    investimentoInicial: String,
    retornoInicial: String,

    onFechar: () -> Unit,

    onSalvar: (
        nome: String,
        prioridade: String,
        status: String,
        progresso: Float,
        investimento: String,
        retorno: String
    ) -> Unit
) {

    var nome by remember {
        mutableStateOf(nomeInicial)
    }

    var prioridade by remember {
        mutableStateOf(prioridadeInicial)
    }

    var status by remember {
        mutableStateOf(statusInicial)
    }

    var progresso by remember {
        mutableFloatStateOf(progressoInicial)
    }

    var investimento by remember {
        mutableStateOf(investimentoInicial)
    }

    var retorno by remember {
        mutableStateOf(retornoInicial)
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Fundo)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 18.dp, vertical = 18.dp)
    ) {

        HeaderEditarProjeto(

            onFechar = {
                onFechar()
            },

            onSalvar = {

                onSalvar(
                    nome,
                    prioridade,
                    status,
                    progresso,
                    investimento,
                    retorno
                )
            }
        )

        Spacer(modifier = Modifier.height(20.dp))

        CampoTexto(
            titulo = "Nome do projeto",
            valor = nome,
            onChange = {
                nome = it
            }
        )

        CampoTextoBloqueado(
            titulo = "Responsável",
            valor = responsavelInicial
        )

        Text(
            text = "Prioridade",
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold,
            color = AzulEscuro
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {

            BotaoOpcao(
                texto = "Baixa",
                selecionado = prioridade == "Baixa",
                modifier = Modifier.weight(1f)
            ) {
                prioridade = "Baixa"
            }

            BotaoOpcao(
                texto = "Média",
                selecionado = prioridade == "Média",
                modifier = Modifier.weight(1f)
            ) {
                prioridade = "Média"
            }

            BotaoOpcao(
                texto = "Alta",
                selecionado = prioridade == "Alta",
                modifier = Modifier.weight(1f)
            ) {
                prioridade = "Alta"
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Status",
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold,
            color = AzulEscuro
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            BotaoOpcao(
                texto = "Aprovado",
                selecionado = status == "Aprovado",
                modifier = Modifier.weight(1f)
            ) {
                status = "Aprovado"
            }

            BotaoOpcao(
                texto = "Andamento",
                selecionado = status == "Andamento",
                modifier = Modifier.weight(1f)
            ) {
                status = "Andamento"
            }

            BotaoOpcao(
                texto = "Rejeitado",
                selecionado = status == "Rejeitado",
                modifier = Modifier.weight(1f)
            ) {
                status = "Rejeitado"
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Progresso",
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold,
            color = AzulEscuro
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {

            Slider(
                value = progresso,

                onValueChange = {
                    progresso = it
                },

                modifier = Modifier.weight(1f),

                colors = SliderDefaults.colors(
                    thumbColor = Azul,
                    activeTrackColor = Azul
                )
            )

            Spacer(modifier = Modifier.width(10.dp))

            Text(
                text = "${(progresso * 100).toInt()}%",
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = AzulEscuro
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        CampoTexto(
            titulo = "Investimento (R$)",
            valor = investimento,
            onChange = {
                investimento = it
            }
        )

        CampoTexto(
            titulo = "Retorno estimado (R$)",
            valor = retorno,
            onChange = {
                retorno = it
            }
        )

        Spacer(modifier = Modifier.height(120.dp))
    }
}

@Composable
private fun HeaderEditarProjeto(
    onFechar: () -> Unit,
    onSalvar: () -> Unit
) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        IconButton(
            onClick = onFechar
        ) {

            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "Fechar",
                tint = AzulEscuro
            )
        }

        Text(
            text = "Editar projeto",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = AzulEscuro
        )

        TextButton(
            onClick = onSalvar
        ) {

            Text(
                text = "Salvar",
                color = Azul,
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp
            )
        }
    }
}

@Composable
private fun CampoTexto(
    titulo: String,
    valor: String,
    onChange: (String) -> Unit
) {

    Text(
        text = titulo,
        fontSize = 13.sp,
        fontWeight = FontWeight.Bold,
        color = AzulEscuro
    )

    Spacer(modifier = Modifier.height(6.dp))

    OutlinedTextField(
        value = valor,

        onValueChange = onChange,

        modifier = Modifier.fillMaxWidth(),

        shape = RoundedCornerShape(12.dp),

        singleLine = true,

        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Azul,
            unfocusedBorderColor = Borda,
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White
        )
    )

    Spacer(modifier = Modifier.height(14.dp))
}

@Composable
private fun CampoTextoBloqueado(
    titulo: String,
    valor: String
) {

    Text(
        text = titulo,
        fontSize = 13.sp,
        fontWeight = FontWeight.Bold,
        color = AzulEscuro
    )

    Spacer(modifier = Modifier.height(6.dp))

    OutlinedTextField(
        value = valor,
        onValueChange = {},

        modifier = Modifier.fillMaxWidth(),

        shape = RoundedCornerShape(12.dp),

        singleLine = true,

        readOnly = true,
        enabled = false
    )

    Spacer(modifier = Modifier.height(14.dp))
}

@Composable
private fun BotaoOpcao(
    texto: String,
    selecionado: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {

    Button(
        onClick = onClick,

        modifier = modifier.height(42.dp),

        shape = RoundedCornerShape(12.dp),

        colors = ButtonDefaults.buttonColors(
            containerColor =
                if (selecionado) Azul else Color.White,

            contentColor =
                if (selecionado) Color.White else AzulEscuro
        )
    ) {

        Text(
            text = texto,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold
        )
    }
}