package com.guilhermenetti.agendaapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "estabelecimentos")
data class Estabelecimentos(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val foto: String,
    val nome: String,
    val telefone: String,
    val localizacao: String
) : Serializable