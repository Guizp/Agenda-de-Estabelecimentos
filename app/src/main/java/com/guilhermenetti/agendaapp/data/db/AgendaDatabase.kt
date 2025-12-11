package com.guilhermenetti.agendaapp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.guilhermenetti.agendaapp.data.dao.EstabelecimentoDao
import com.guilhermenetti.agendaapp.model.Estabelecimentos

@Database(entities = [Estabelecimentos::class], version = 1)
abstract class AgendaDatabase : RoomDatabase() {
    abstract fun estabelecimentoDao(): EstabelecimentoDao
    companion object {
        @Volatile
        private var INSTANCE: AgendaDatabase? = null
        fun getInstance(context: Context): AgendaDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AgendaDatabase::class.java,
                    "agenda_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}