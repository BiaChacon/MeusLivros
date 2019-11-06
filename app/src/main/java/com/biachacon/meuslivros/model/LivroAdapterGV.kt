package com.biachacon.meuslivros.model

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.biachacon.meuslivros.R

class LivroAdapterGV(var context: Context, var livro: List<Livro>): BaseAdapter(){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var holder:LivroViewHolderGV
        var view: View

        if (convertView == null) {
            Log.i("TESTE", "Inicializou Holder")
            view = LayoutInflater.from(context).inflate(R.layout.livro_inflater_gv, parent, false)
            holder = LivroViewHolderGV(view)
            view.tag = holder
        }else{
            view = convertView
            holder = convertView.tag as LivroViewHolderGV
        }


        var livroAtual = livro.get(position)

        holder.nomeLivro.text = livroAtual.titulo
        holder.nomeAutor.text = livroAtual.autor

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