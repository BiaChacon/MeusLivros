package com.biachacon.meuslivros.model

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.biachacon.meuslivros.R

class LivroAdapter(var context: Context, var livro: List<Livro>): BaseAdapter(){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var v = LayoutInflater.from(context).inflate(R.layout.livro_layout, parent, false)
        var nomeLivro = v.findViewById<TextView>(R.id.nomeLivro)
        var nomeAutor = v.findViewById<TextView>(R.id.nomeAutor)
        var notaLivro = v.findViewById<TextView>(R.id.notaLivro)

        var livroAtual = livro.get(position)

        nomeLivro.text = livroAtual.titulo
        nomeAutor.text = livroAtual.autor
        notaLivro.text = livroAtual.nota.toString()

        return v
    }

    override fun getItem(position: Int): Any {
        return livro.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return livro.size
    }
}