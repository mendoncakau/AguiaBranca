package br.com.fiap.aguiabranca.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class Diretriz(
    val id: Int,
    val titulo: String,
    val descricao: String
)

data class ProjetoLider(
    val id: Int,
    val nome: String,
    val responsavel: String,
    val prioridade: String,
    val status: String,
    val investimento: Double,
    val retorno: Double
)

class LiderViewModel : ViewModel() {

    private val _totalIdeias = MutableStateFlow(56)
    val totalIdeias: StateFlow<Int> = _totalIdeias

    private val _ideiasAprovadas = MutableStateFlow(28)
    val ideiasAprovadas: StateFlow<Int> = _ideiasAprovadas

    private val _projetosConcluidos = MutableStateFlow(12)
    val projetosConcluidos: StateFlow<Int> = _projetosConcluidos

    private val _investimentoTotal = MutableStateFlow(120000.0)
    val investimentoTotal: StateFlow<Double> = _investimentoTotal

    private val _retornoTotal = MutableStateFlow(165000.0)
    val retornoTotal: StateFlow<Double> = _retornoTotal

    val roiTotal: Double
        get() = _retornoTotal.value - _investimentoTotal.value

    private val _diretrizes = MutableStateFlow(
        listOf(
            Diretriz(1, "Reduzir custos operacionais", "Buscar eficiência e diminuir desperdícios."),
            Diretriz(2, "Melhorar experiência do cliente", "Criar soluções para melhorar o atendimento."),
            Diretriz(3, "Aumentar eficiência operacional", "Automatizar processos internos.")
        )
    )
    val diretrizes: StateFlow<List<Diretriz>> = _diretrizes

    private val _projetos = MutableStateFlow(
        listOf(
            ProjetoLider(1, "Otimização no processo de manutenção", "Ana Paula", "Alta", "Em andamento", 50000.0, 80000.0),
            ProjetoLider(2, "Redução de papel nas garagens", "Marta Ferreira", "Média", "Concluído", 15000.0, 35000.0),
            ProjetoLider(3, "Central de atendimento digital", "Carlos Lima", "Baixa", "Em andamento", 30000.0, 50000.0)
        )
    )
    val projetos: StateFlow<List<ProjetoLider>> = _projetos

    fun adicionarDiretriz(titulo: String, descricao: String) {
        val novaDiretriz = Diretriz(
            id = _diretrizes.value.size + 1,
            titulo = titulo,
            descricao = descricao
        )

        _diretrizes.value = _diretrizes.value + novaDiretriz
    }

    fun excluirDiretriz(id: Int) {
        _diretrizes.value = _diretrizes.value.filter { it.id != id }
    }
}