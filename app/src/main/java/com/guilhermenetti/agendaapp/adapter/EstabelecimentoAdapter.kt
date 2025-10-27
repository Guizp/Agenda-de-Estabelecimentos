package com.guilhermenetti.agendaapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.guilhermenetti.agendaapp.databinding.ItemEstabelecimentoBinding
import com.guilhermenetti.agendaapp.model.Estabelecimentos

class EstabelecimentoAdapter(
    private val context: Context,
    private val lista: List<Estabelecimentos>
) : ArrayAdapter<Estabelecimentos>(context, 0, lista) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding: ItemEstabelecimentoBinding
        val itemView: View

        if (convertView == null) {
            binding = ItemEstabelecimentoBinding.inflate(LayoutInflater.from(context), parent, false)
            itemView = binding.root
            itemView.tag = binding
        } else {
            itemView = convertView
            binding = itemView.tag as ItemEstabelecimentoBinding
        }

        val contato = lista[position]

        binding.imgFoto.setImageResource(contato.foto)
        binding.tvNome.text = contato.nome
        binding.tvTelefone.text = contato.telefone
        binding.tvLocalizacao.text = contato.localizacao

        return itemView
    }

}