package com.guilhermenetti.agendaapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.guilhermenetti.agendaapp.databinding.ItemContatoBinding
import com.guilhermenetti.agendaapp.model.Contato

class ContatoAdapter(
    private val context: Context,
    private val lista: List<Contato>
) : ArrayAdapter<Contato>(context, 0, lista) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding: ItemContatoBinding
        val itemView: View

        if (convertView == null) {
            binding = ItemContatoBinding.inflate(LayoutInflater.from(context), parent, false)
            itemView = binding.root
            itemView.tag = binding
        } else {
            itemView = convertView
            binding = itemView.tag as ItemContatoBinding
        }

        val contato = lista[position]

        binding.imgFoto.setImageResource(contato.foto)
        binding.tvNome.text = contato.nome
        binding.tvTelefone.text = contato.telefone
        binding.tvEmail.text = contato.email

        return itemView
    }

}