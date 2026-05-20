package br.com.fiap.aguiabranca.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HomeOperadorViewModel : ViewModel() {

    private val _nomeUsuario = MutableStateFlow("João")

    val nomeUsuario: StateFlow<String> = _nomeUsuario



    private val _quantidadeIdeias = MutableStateFlow(7)

    val quantidadeIdeias: StateFlow<Int> = _quantidadeIdeias



    private val _quantidadeEmAnalise = MutableStateFlow(2)

    val quantidadeEmAnalise: StateFlow<Int> = _quantidadeEmAnalise



    private val _quantidadeAprovadas = MutableStateFlow(3)

    val quantidadeAprovadas: StateFlow<Int> = _quantidadeAprovadas



    private val _quantidadeViraramProjeto = MutableStateFlow(2)

    val quantidadeViraramProjeto: StateFlow<Int> = _quantidadeViraramProjeto
}
