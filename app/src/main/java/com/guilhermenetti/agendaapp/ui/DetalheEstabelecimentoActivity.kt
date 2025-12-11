package com.guilhermenetti.agendaapp.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.guilhermenetti.agendaapp.R
import com.guilhermenetti.agendaapp.databinding.ActivityDetalheEstabelecimentoBinding
import com.guilhermenetti.agendaapp.model.Estabelecimentos

class DetalheEstabelecimentoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetalheEstabelecimentoBinding
    private lateinit var estabelecimentos : Estabelecimentos

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetalheEstabelecimentoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadData()
        setupViews()
        setupListeners()
    }

    private fun loadData()
    {
        estabelecimentos = intent.getSerializableExtra("estabelecimentos", Estabelecimentos::class.java) as Estabelecimentos
    }

    private fun setupViews()
    {
        binding.tvNome.text = estabelecimentos.nome
        binding.tvTelefone.text = estabelecimentos.telefone
        binding.tvLocalizacao.text = estabelecimentos.localizacao
        binding.imgFoto.setImageURI(estabelecimentos.foto.toUri())
    }

    private fun setupListeners() {
        binding.btnLigar.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = "tel:${estabelecimentos.telefone}".toUri()
            startActivity(intent)
        }

        binding.btnVoltar.setOnClickListener {
            finish()
        }

    }
}