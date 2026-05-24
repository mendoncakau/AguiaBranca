package br.com.fiap.aguiabranca.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

data class Diretriz(
    val id: Int,
    val titulo: String,
    val descricao: String,
    val categoria: String,
    val prioridade: String
)

data class Projeto(
    val nome: String,
    val responsavel: String,
    val prioridade: String,
    val status: String,
    val investimento: Double,
    val retorno: Double
)

class LiderViewModel : ViewModel() {

    private var ultimoId = 4

    private val _diretrizes = MutableStateFlow(
        listOf(
            Diretriz(
                1,
                "Reduzir custos operacionais",
                "Foco em eficiência e otimização de processos.",
                "Estratégica",
                "Alta prioridade"
            ),
            Diretriz(
                2,
                "Melhorar experiência do cliente",
                "Iniciativas voltadas para satisfação.",
                "Estratégica",
                "Alta prioridade"
            ),
            Diretriz(
                3,
                "Aumentar eficiência operacional",
                "Buscar produtividade em áreas internas.",
                "Estratégica",
                "Alta prioridade"
            )
        )
    )

    val diretrizes: StateFlow<List<Diretriz>> = _diretrizes

    fun adicionarDiretriz(
        titulo: String,
        descricao: String,
        categoria: String,
        prioridade: String
    ) {
        ultimoId++

        _diretrizes.value = _diretrizes.value + Diretriz(
            id = ultimoId,
            titulo = titulo,
            descricao = descricao,
            categoria = categoria,
            prioridade = prioridade
        )
    }

    fun editarDiretriz(
        id: Int,
        titulo: String,
        descricao: String,
        categoria: String,
        prioridade: String
    ) {
        _diretrizes.value = _diretrizes.value.map {
            if (it.id == id) {
                it.copy(
                    titulo = titulo,
                    descricao = descricao,
                    categoria = categoria,
                    prioridade = prioridade
                )
            } else {
                it
            }
        }
    }

    fun excluirDiretriz(id: Int) {
        _diretrizes.update {
            it.filter { item ->
                item.id != id
            }
        }
    }

    val projetos = MutableStateFlow(
        listOf(
            Projeto(
                "Otimização da manutenção",
                "Ana Paula",
                "Alta",
                "Em andamento",
                45000.0,
                85000.0
            ),
            Projeto(
                "Redução de papel nas garagens",
                "Marcos",
                "Alta",
                "Concluído",
                30000.0,
                90000.0
            )
        )
    )
}