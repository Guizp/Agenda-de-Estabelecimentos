package com.guilhermenetti.agendaapp.model

import java.io.Serializable

data class Contato(
    val foto: Int,
    val nome: String,
    val telefone: String,
    val email: String
) : Serializable