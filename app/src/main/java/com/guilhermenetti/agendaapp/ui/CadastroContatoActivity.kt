package com.guilhermenetti.agendaapp.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.guilhermenetti.agendaapp.databinding.ActivityCadastroBinding
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.guilhermenetti.agendaapp.R
import com.guilhermenetti.agendaapp.data.db.AgendaDatabase
import com.guilhermenetti.agendaapp.model.Estabelecimentos
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CadastroContatoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCadastroBinding
    private lateinit var launcherGaleria: ActivityResultLauncher<Array<String>>
    private lateinit var uriSelecionada: String
    private lateinit var db: AgendaDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db = AgendaDatabase.getInstance(this)
        setupLauncher()
        setupListeners()
    }
    private fun setupLauncher(){
        launcherGaleria = registerForActivityResult(
            ActivityResultContracts.OpenDocument()
        ) { uri ->
            if (uri != null) {
                contentResolver.takePersistableUriPermission(
                    uri,
                    Intent.FLAG_GRANT_READ_URI_PERMISSION
                )
                uriSelecionada = uri.toString()
                binding.imgFoto.setImageURI(uri)
            }
        }
    }
    private fun setupListeners() {
        binding.imgFoto.setOnClickListener {
            launcherGaleria.launch(arrayOf("image/*"))
        }
        binding.btnSalvar.setOnClickListener {
            val foto = uriSelecionada.toString()
            val nome = binding.edtNome.text.toString()
            val telefone = binding.edtTelefone.text.toString()
            val localizacao = binding.edtLocalizacao.text.toString()
            if (nome.isNotBlank() && telefone.isNotBlank() && localizacao.isNotBlank()) {
                val novoEstabelecimentos = Estabelecimentos(
                    foto = foto,
                    nome = nome,
                    telefone = telefone,
                    localizacao = localizacao
                )
                salvarNoBanco(novoEstabelecimentos)
            }
            else{
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
            }
        }
        binding.btnVoltar.setOnClickListener {
            setResult(Activity.RESULT_CANCELED)
            finish()
        }
    }
    private fun salvarNoBanco(estabelecimentos: Estabelecimentos) {
        lifecycleScope.launch(Dispatchers.IO) {
            db.estabelecimentoDao().inserir(estabelecimentos)
            setResult(Activity.RESULT_OK)
            finish()
        }
    }
}