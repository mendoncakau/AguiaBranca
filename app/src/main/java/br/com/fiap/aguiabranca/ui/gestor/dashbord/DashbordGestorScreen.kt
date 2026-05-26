package br.com.fiap.aguiabranca.ui.gestor.dashboard

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val AzulPrincipal = Color(0xFF0B57D0)
private val AzulEscuro = Color(0xFF071B52)
private val AzulClaro = Color(0xFFEAF2FF)
private val Verde = Color(0xFF14AE5C)
private val Vermelho = Color(0xFFE53935)
private val Roxo = Color(0xFF7B61FF)
private val Fundo = Color(0xFFF5F7FB)
private val Branco = Color.White
private val Texto = Color(0xFF172033)
private val Cinza = Color(0xFF6E7891)

@Composable
fun DashboardGestorScreen(
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Fundo)
            .verticalScroll(rememberScrollState())
    ) {


        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            AzulPrincipal,
                            AzulEscuro
                        )
                    )
                )
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(22.dp)
            ) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement =
                        Arrangement.SpaceBetween,
                    verticalAlignment =
                        Alignment.CenterVertically
                ) {

                    Column {

                        Text(
                            text = "Dashboard",
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Bold,
                            color = Branco
                        )

                        Spacer(
                            modifier = Modifier.height(6.dp)
                        )

                        Text(
                            text =
                                "Visão estratégica da inovação",
                            fontSize = 16.sp,
                            color = Branco.copy(alpha = 0.85f)
                        )
                    }

                    Box(
                        modifier = Modifier
                            .size(52.dp)
                            .clip(CircleShape)
                            .background(
                                Branco.copy(alpha = 0.15f)
                            ),
                        contentAlignment = Alignment.Center
                    ) {

                        Icon(
                            imageVector =
                                Icons.Outlined.Notifications,
                            contentDescription = null,
                            tint = Branco
                        )
                    }
                }

                Spacer(
                    modifier = Modifier.height(30.dp)
                )

                Card(
                    shape = RoundedCornerShape(24.dp),
                    colors = CardDefaults.cardColors(
                        containerColor =
                            Branco.copy(alpha = 0.14f)
                    )
                ) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp),

                        verticalAlignment =
                            Alignment.CenterVertically
                    ) {

                        Box(
                            modifier = Modifier
                                .size(54.dp)
                                .clip(CircleShape)
                                .background(
                                    Branco.copy(alpha = 0.2f)
                                ),

                            contentAlignment =
                                Alignment.Center
                        ) {

                            Icon(
                                imageVector =
                                    Icons.Default.TrendingUp,

                                contentDescription = null,
                                tint = Branco
                            )
                        }

                        Spacer(
                            modifier = Modifier.width(16.dp)
                        )

                        Column {

                            Text(
                                text = "ROI Total",
                                color =
                                    Branco.copy(alpha = 0.8f),

                                fontSize = 15.sp
                            )

                            Spacer(
                                modifier =
                                    Modifier.height(4.dp)
                            )

                            Text(
                                text = "R$ 45.000",
                                color = Branco,
                                fontSize = 28.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(22.dp))


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 18.dp),

            horizontalArrangement =
                Arrangement.spacedBy(12.dp)
        ) {

            DashboardCard(
                titulo = "Ideias recebidas",
                valor = "23",
                cor = AzulPrincipal,
                modifier = Modifier.weight(1f)
            )

            DashboardCard(
                titulo = "Aprovadas",
                valor = "10",
                cor = Verde,
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 18.dp),

            horizontalArrangement =
                Arrangement.spacedBy(12.dp)
        ) {

            DashboardCard(
                titulo = "Em andamento",
                valor = "8",
                cor = Roxo,
                modifier = Modifier.weight(1f)
            )

            DashboardCard(
                titulo = "Projetos",
                valor = "6",
                cor = AzulEscuro,
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(28.dp))


        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 18.dp),

            shape = RoundedCornerShape(28.dp),

            colors = CardDefaults.cardColors(
                containerColor = Branco
            )
        ) {

            Column(
                modifier = Modifier.padding(22.dp)
            ) {

                Text(
                    text = "Impacto financeiro",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Texto
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text =
                        "Indicadores estratégicos da operação",
                    color = Cinza,
                    fontSize = 15.sp
                )

                Spacer(modifier = Modifier.height(24.dp))

                FinanceiroItem(
                    titulo = "Investimento total",
                    valor = "R$ 120.000",
                    cor = AzulPrincipal
                )

                Spacer(modifier = Modifier.height(16.dp))

                FinanceiroItem(
                    titulo = "Retorno estimado",
                    valor = "R$ 165.000",
                    cor = Verde
                )

                Spacer(modifier = Modifier.height(16.dp))

                FinanceiroItem(
                    titulo = "Lucro estimado",
                    valor = "R$ 45.000",
                    cor = Roxo
                )
            }
        }

        Spacer(modifier = Modifier.height(28.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 18.dp),

            shape = RoundedCornerShape(28.dp),

            colors = CardDefaults.cardColors(
                containerColor = Branco
            )
        ) {

            Column(
                modifier = Modifier.padding(22.dp)
            ) {

                Text(
                    text = "Ideias por status",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Texto
                )

                Spacer(modifier = Modifier.height(26.dp))

                Column(
                    horizontalAlignment =
                        Alignment.CenterHorizontally,

                    modifier = Modifier.fillMaxWidth()
                ) {

                    DonutChartPremium()

                    Spacer(
                        modifier = Modifier.height(26.dp)
                    )

                    LegendPremium(
                        cor = AzulPrincipal,
                        titulo = "Em análise",
                        valor = "8 ideias"
                    )

                    Spacer(
                        modifier = Modifier.height(14.dp)
                    )

                    LegendPremium(
                        cor = Verde,
                        titulo = "Aprovadas",
                        valor = "10 ideias"
                    )

                    Spacer(
                        modifier = Modifier.height(14.dp)
                    )

                    LegendPremium(
                        cor = Vermelho,
                        titulo = "Rejeitadas",
                        valor = "3 ideias"
                    )

                    Spacer(
                        modifier = Modifier.height(14.dp)
                    )

                    LegendPremium(
                        cor = Roxo,
                        titulo = "Projetos",
                        valor = "2 ideias"
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(28.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 18.dp),

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
                            .size(48.dp)
                            .clip(CircleShape)
                            .background(
                                Roxo.copy(alpha = 0.12f)
                            ),
                        contentAlignment = Alignment.Center
                    ) {

                        Icon(
                            imageVector = Icons.Default.Flag,
                            contentDescription = null,
                            tint = Roxo
                        )
                    }

                    Spacer(modifier = Modifier.width(14.dp))

                    Column {

                        Text(
                            text = "Diretrizes ativas",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = Texto
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            text = "Direcionamentos estratégicos da empresa",
                            color = Cinza,
                            fontSize = 15.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                DiretrizGestorCard(
                    titulo = "Reduzir custos operacionais",
                    descricao = "Buscar soluções para otimizar recursos e reduzir desperdícios.",
                    categoria = "Estratégica",
                    prioridade = "Alta prioridade"
                )

                Spacer(modifier = Modifier.height(14.dp))

                DiretrizGestorCard(
                    titulo = "Melhorar experiência do cliente",
                    descricao = "Focar em ideias que aumentem satisfação e qualidade.",
                    categoria = "Operacional",
                    prioridade = "Média prioridade"
                )

                Spacer(modifier = Modifier.height(14.dp))

                DiretrizGestorCard(
                    titulo = "Aumentar eficiência logística",
                    descricao = "Melhorias em rotas, processos e produtividade.",
                    categoria = "Financeira",
                    prioridade = "Baixa prioridade"
                )
            }
        }

        Spacer(modifier = Modifier.height(120.dp))
    }
}

@Composable
fun DashboardCard(
    titulo: String,
    valor: String,
    cor: Color,
    modifier: Modifier = Modifier
) {

    Card(
        modifier = modifier,

        shape = RoundedCornerShape(24.dp),

        colors = CardDefaults.cardColors(
            containerColor = Branco
        )
    ) {

        Column(
            modifier = Modifier.padding(20.dp)
        ) {

            Box(
                modifier = Modifier
                    .size(14.dp)
                    .clip(CircleShape)
                    .background(cor)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = valor,
                fontSize = 34.sp,
                fontWeight = FontWeight.Bold,
                color = cor
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = titulo,
                color = Cinza,
                fontSize = 14.sp
            )
        }
    }
}

@Composable
fun DiretrizGestorCard(
    titulo: String,
    descricao: String,
    categoria: String,
    prioridade: String
) {

    Card(
        modifier = Modifier.fillMaxWidth(),

        shape = RoundedCornerShape(24.dp),

        colors = CardDefaults.cardColors(
            containerColor = AzulClaro
        )
    ) {

        Column(
            modifier = Modifier.padding(20.dp)
        ) {

            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {

                Surface(
                    shape = RoundedCornerShape(50.dp),

                    color = when (prioridade) {

                        "Alta prioridade" ->
                            Vermelho.copy(alpha = 0.12f)

                        "Média prioridade" ->
                            Roxo.copy(alpha = 0.12f)

                        else ->
                            Verde.copy(alpha = 0.12f)
                    }
                ) {

                    Text(
                        text = prioridade,

                        modifier = Modifier.padding(
                            horizontal = 14.dp,
                            vertical = 8.dp
                        ),

                        fontWeight = FontWeight.Bold,

                        color = when (prioridade) {

                            "Alta prioridade" -> Vermelho

                            "Média prioridade" -> Roxo

                            else -> Verde
                        }
                    )
                }

                Surface(
                    shape = RoundedCornerShape(50.dp),
                    color = AzulPrincipal.copy(alpha = 0.12f)
                ) {

                    Text(
                        text = categoria,

                        modifier = Modifier.padding(
                            horizontal = 14.dp,
                            vertical = 8.dp
                        ),

                        color = AzulPrincipal,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }

            Spacer(modifier = Modifier.height(18.dp))

            Text(
                text = titulo,
                fontSize = 21.sp,
                fontWeight = FontWeight.Bold,
                color = Texto
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = descricao,
                color = Cinza,
                lineHeight = 24.sp,
                fontSize = 15.sp
            )
        }
    }
}

@Composable
fun FinanceiroItem(
    titulo: String,
    valor: String,
    cor: Color
) {

    Row(
        modifier = Modifier.fillMaxWidth(),

        horizontalArrangement =
            Arrangement.SpaceBetween,

        verticalAlignment =
            Alignment.CenterVertically
    ) {

        Text(
            text = titulo,
            color = Texto,
            fontSize = 16.sp
        )

        Surface(
            shape = RoundedCornerShape(50.dp),
            color = cor.copy(alpha = 0.12f)
        ) {

            Text(
                text = valor,

                modifier = Modifier.padding(
                    horizontal = 16.dp,
                    vertical = 8.dp
                ),

                color = cor,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun DonutChartPremium() {

    Box(
        contentAlignment = Alignment.Center
    ) {

        Canvas(
            modifier = Modifier.size(180.dp)
        ) {

            val stroke =
                androidx.compose.ui.graphics.drawscope.Stroke(
                    width = 30.dp.toPx()
                )

            rotate(-90f) {

                drawArc(
                    color = AzulPrincipal,
                    startAngle = 0f,
                    sweepAngle = 120f,
                    useCenter = false,
                    style = stroke
                )

                drawArc(
                    color = Verde,
                    startAngle = 120f,
                    sweepAngle = 150f,
                    useCenter = false,
                    style = stroke
                )

                drawArc(
                    color = Vermelho,
                    startAngle = 270f,
                    sweepAngle = 40f,
                    useCenter = false,
                    style = stroke
                )

                drawArc(
                    color = Roxo,
                    startAngle = 310f,
                    sweepAngle = 50f,
                    useCenter = false,
                    style = stroke
                )
            }
        }

        Column(
            horizontalAlignment =
                Alignment.CenterHorizontally
        ) {

            Text(
                text = "23",
                fontSize = 34.sp,
                fontWeight = FontWeight.Bold,
                color = Texto
            )

            Text(
                text = "Ideias",
                color = Cinza
            )
        }
    }
}

@Composable
fun LegendPremium(
    cor: Color,
    titulo: String,
    valor: String
) {

    Row(
        modifier = Modifier.fillMaxWidth(),

        horizontalArrangement =
            Arrangement.SpaceBetween,

        verticalAlignment =
            Alignment.CenterVertically
    ) {

        Row(
            verticalAlignment =
                Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .size(14.dp)
                    .clip(CircleShape)
                    .background(cor)
            )

            Spacer(
                modifier = Modifier.width(12.dp)
            )

            Text(
                text = titulo,
                fontSize = 15.sp,
                color = Texto
            )
        }

        Text(
            text = valor,
            fontWeight = FontWeight.Bold,
            color = Texto
        )
    }
}