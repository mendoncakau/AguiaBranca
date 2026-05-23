package br.com.fiap.aguiabranca.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

data class Diretriz(
    val id: Int,
    val titulo: String,
    val descricao: String
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
                "Reduzir custos operacionais em 15%",
                "Foco em eficiência e otimização de processos."
            ),
            Diretriz(
                2,
                "Melhorar experiência do cliente",
                "Iniciativas voltadas para satisfação."
            ),
            Diretriz(
                3,
                "Aumentar eficiência operacional",
                "Buscar produtividade em áreas internas."
            )
        )
    )

    val diretrizes: StateFlow<List<Diretriz>> = _diretrizes

    fun adicionarDiretriz(
        titulo: String,
        descricao: String
    ) {
        ultimoId++

        _diretrizes.update {
            it + Diretriz(
                ultimoId,
                titulo,
                descricao
            )
        }
    }

    fun editarDiretriz(
        id: Int,
        titulo: String,
        descricao: String
    ) {
        _diretrizes.update { lista ->
            lista.map {
                if (it.id == id) {
                    it.copy(
                        titulo = titulo,
                        descricao = descricao
                    )
                } else it
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