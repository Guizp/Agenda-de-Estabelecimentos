package com.guilhermenetti.agendaapp.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.guilhermenetti.agendaapp.model.Estabelecimentos
import com.guilhermenetti.agendaapp.adapter.EstabelecimentoAdapter
import com.guilhermenetti.agendaapp.R
import com.guilhermenetti.agendaapp.data.db.AgendaDatabase
import com.guilhermenetti.agendaapp.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var estabelecimentos: MutableList<Estabelecimentos>
    private lateinit var adapter: EstabelecimentoAdapter
    private lateinit var launcherCadastro: ActivityResultLauncher<Intent>
    private lateinit var db: AgendaDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadData()
        setupRecyclerView()
        setupLauncherCadastro()
        setupListeners()
    }
    fun loadData(){
        estabelecimentos = mutableListOf()
        db = AgendaDatabase.getInstance(this)
        lifecycleScope.launch(Dispatchers.IO) {
            estabelecimentos = db.estabelecimentoDao().listarTodos().toMutableList()
            withContext(Dispatchers.Main) {
                adapter.updateLista(estabelecimentos)
            }
        }
    }
    private fun setupRecyclerView() {
        adapter = EstabelecimentoAdapter(estabelecimentos) { estabelecimento ->
            val intent = Intent(this, DetalheEstabelecimentoActivity::class.java)
            intent.putExtra("estabelecimento", estabelecimento)
            startActivity(intent)
        }
        binding.listViewEstabelecimentos.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.adapter
            addItemDecoration(
                DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
            )
        }
    }
    private fun setupLauncherCadastro() {
        launcherCadastro = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                loadData()
            }
        }
    }
    private fun setupListeners() {
        binding.btnAdicionar.setOnClickListener {
            val intent = Intent(this, CadastroEstabelecimentoActivity::class.java)
            launcherCadastro.launch(intent)
        }
        binding.edtFiltro.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val filtro = s.toString().lowercase()
                val filtrados = estabelecimentos.filter {
                    it.nome.lowercase().contains(filtro)
                }
                adapter.updateLista(filtrados)
            }
            override fun afterTextChanged(s: Editable?) {}
        })
    }
}