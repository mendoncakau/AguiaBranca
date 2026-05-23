package br.com.fiap.aguiabranca.ui.operador.ideias

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.com.fiap.aguiabranca.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetalhesIdeiaScreen(
    navController: NavHostController,
    tituloIdeia: String
) {

    val status = when (tituloIdeia) {

        "Redução de papel nas garagens" -> "Em análise"

        "Otimização no processo de manutenção" -> "Aprovada"

        "Mais sinalização nas áreas operacionais" -> "Implementada"

        else -> "Rejeitada"
    }

    val descricao = when (tituloIdeia) {

        "Redução de papel nas garagens" ->
            "Implementar documentos digitais para reduzir o uso de papel nos processos diários das garagens."

        "Otimização no processo de manutenção" ->
            "Melhorar o fluxo operacional das manutenções preventivas para reduzir atrasos."

        "Mais sinalização nas áreas operacionais" ->
            "Adicionar placas e sinalizações para melhorar segurança e organização."

        else ->
            "Padronizar os documentos utilizados internamente."
    }

    val corStatus = when (status) {

        "Em análise" -> LaranjaAnalise

        "Aprovada" -> VerdeAprovado

        "Implementada" -> AzulPrincipal

        else -> VermelhoStatus
    }

    Scaffold(
        containerColor = Fundo,

        topBar = {

            TopAppBar(

                title = {

                    Text(
                        text = "Detalhes da ideia",
                        fontWeight = FontWeight.Bold
                    )
                },

                navigationIcon = {

                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        }
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
                .padding(horizontal = 22.dp)
                .verticalScroll(rememberScrollState())
        ) {

            Spacer(modifier = Modifier.height(12.dp))

            Surface(
                shape = RoundedCornerShape(30.dp),
                color = corStatus.copy(alpha = 0.12f)
            ) {

                Text(
                    text = status,
                    modifier = Modifier.padding(
                        horizontal = 18.dp,
                        vertical = 8.dp
                    ),
                    color = corStatus,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }

            Spacer(modifier = Modifier.height(28.dp))

            Text(
                text = tituloIdeia,
                fontSize = 34.sp,
                fontWeight = FontWeight.Bold,
                color = PretoTexto
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Enviada em 12/05/2026",
                fontSize = 18.sp,
                color = CinzaTexto
            )

            Spacer(modifier = Modifier.height(34.dp))

            CampoDetalhe(
                titulo = "Descrição",
                conteudo = descricao
            )

            CampoDetalhe(
                titulo = "Categoria",
                conteudo = "Operacional"
            )

            CampoDetalhe(
                titulo = "Tipo de impacto",
                conteudo = "Redução de custo"
            )

            CampoDetalhe(
                titulo = "Impacto esperado",
                conteudo = "Médio",
                corConteudo = corStatus
            )

            CampoDetalhe(
                titulo = "Estimativa de ganho",
                conteudo = "R$ 5.000 / mês"
            )

            CampoDetalhe(
                titulo = "Diretriz estratégica",
                conteudo = "Reduzir custos operacionais"
            )

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
fun CampoDetalhe(
    titulo: String,
    conteudo: String,
    corConteudo: Color = PretoTexto
) {

    Column {

        Text(
            text = titulo,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = PretoTexto
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = conteudo,
            fontSize = 18.sp,
            lineHeight = 30.sp,
            color = corConteudo
        )

        Spacer(modifier = Modifier.height(30.dp))
    }
}
