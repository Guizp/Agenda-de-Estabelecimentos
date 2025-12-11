package com.guilhermenetti.agendaapp.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.guilhermenetti.agendaapp.model.Estabelecimentos

@Dao
interface EstabelecimentoDao {
    @Insert
    suspend fun inserir(estabelecimentos: Estabelecimentos)
    @Query("SELECT * FROM estabelecimentos ORDER BY nome ASC")
    suspend fun listarTodos(): List<Estabelecimentos>
    @Query("SELECT * FROM estabelecimentos WHERE nome LIKE :filtro ORDER BY nome ASC")
    suspend fun filtrarPorNome(filtro: String): List<Estabelecimentos>
    @Delete
    suspend fun deletar(estabelecimentos: Estabelecimentos)
    @Update
    suspend fun atualizar(estabelecimentos: Estabelecimentos)
}