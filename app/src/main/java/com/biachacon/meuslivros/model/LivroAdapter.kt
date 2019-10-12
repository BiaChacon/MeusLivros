package com.biachacon.meuslivros.model

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.biachacon.meuslivros.R

class LivroAdapter(var context: Context, var livro: List<Livro>): BaseAdapter(){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var holder:LivroViewHolder
        var view:View

        if (convertView == null) {
            Log.i("TESTE", "Inicializou Holder")
            view = LayoutInflater.from(context).inflate(R.layout.livro_layout, parent, false)
            holder = LivroViewHolder(view)
            view.tag = holder
        }else{
            view = convertView
            holder = convertView.tag as LivroViewHolder
        }


        var livroAtual = livro.get(position)

        holder.nomeLivro.text = livroAtual.titulo
        holder.nomeAutor.text = livroAtual.autor
        holder.notaLivro.rating = livroAtual.nota

        return view
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