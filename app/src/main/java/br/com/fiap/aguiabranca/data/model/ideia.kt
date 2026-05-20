package br.com.fiap.aguiabranca.data.model

data class Ideia(
    val id: String = "",
    val titulo: String = "",
    val descricao: String = "",
    val status: String = "",
    val categoria: String = "",
    val impactoEsperado: String = "",
    val estimativaGanho: Double = 0.0
)

