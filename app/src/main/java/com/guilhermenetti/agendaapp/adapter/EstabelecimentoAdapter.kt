package com.guilhermenetti.agendaapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.guilhermenetti.agendaapp.databinding.ItemEstabelecimentoBinding
import com.guilhermenetti.agendaapp.model.Estabelecimentos

class EstabelecimentoAdapter(
    private var estabelecimentos: List<Estabelecimentos>,
    private val onClick: (Estabelecimentos) -> Unit
) : RecyclerView.Adapter<EstabelecimentoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemEstabelecimentoBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(estabelecimentos[position])
    }

    override fun getItemCount(): Int = estabelecimentos.size

    fun updateLista(novosEstabelecimentos: List<Estabelecimentos>) {
        this.estabelecimentos = novosEstabelecimentos
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: ItemEstabelecimentoBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(estabelecimentos: Estabelecimentos) {
            binding.imgFoto.setImageURI(estabelecimentos.foto.toUri())
            binding.tvNome.text = estabelecimentos.nome
            binding.tvTelefone.text = estabelecimentos.telefone
            binding.tvLocalizacao.text = estabelecimentos.localizacao
            binding.root.setOnClickListener { onClick(estabelecimentos) }
        }
    }
}