package com.guilhermenetti.agendaapp.model

import java.io.Serializable

data class Estabelecimentos(
    val foto: Int,
    val nome: String,
    val telefone: String,
    val localizacao: String
) : Serializable