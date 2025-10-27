package com.guilhermenetti.agendaapp.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.guilhermenetti.agendaapp.model.Estabelecimentos
import com.guilhermenetti.agendaapp.adapter.EstabelecimentoAdapter
import com.guilhermenetti.agendaapp.R
import com.guilhermenetti.agendaapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var estabelecimentos: List<Estabelecimentos>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadData()
        setupViews()
        setupListeners()
    }
    fun loadData(){
        estabelecimentos = listOf(
            Estabelecimentos(
                R.drawable.tayrona,
                "Tayrona",
                "(16) 99737-9113",
                "R. Maurício Galli, 446 - Vila Sedenho"
            ),
            Estabelecimentos(
                R.drawable.mabo,
                "Mabô",
                "(16) 99377-6336",
                "Av. Irmã Antônia de Arruda Camargo, 331 - Vila Harmonia"
            ),
            Estabelecimentos(
                R.drawable.bossanova,
                "Bossa Nova",
                "(16) 99964-4426",
                "Av. Queiroz Filho, 347 - Vila Sedenho"
            ),
            Estabelecimentos(
                R.drawable.consertabikes,
                "Conserta Bikes",
                "(16) 99779-9927",
                "Av. Irmã de Antonia, Av. Irmã Antônia de Arruda Camargo, 370 - Jardim Vale das Rosas"
            ),
            Estabelecimentos(
                R.drawable.flor,
                "Por Onde Flor",
                "(16) 3461-3472",
                "Av. Luís Alberto, 938 - Vila Velosa"
            ),
            Estabelecimentos(
                R.drawable.gastro,
                "Gastro Vita",
                "(16) 3324-9000",
                "Av. José Ziliolli, 807 - Vila Sedenho"
            )
        ).sortedBy { it.nome }
    }
    fun setupViews(){
        val adapter = EstabelecimentoAdapter(this, estabelecimentos)
        binding.listViewEstabelecimentos.adapter = adapter
    }
    fun setupListeners(){
        binding.listViewEstabelecimentos.setOnItemClickListener { _, _, position, _ ->
            val intent = Intent(this, DetalheEstabelecimentoActivity::class.java)
            intent.putExtra("estabelecimentos", estabelecimentos[position])
            startActivity(intent)
        }
    }
}