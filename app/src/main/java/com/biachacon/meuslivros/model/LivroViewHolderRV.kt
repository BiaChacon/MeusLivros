package com.biachacon.meuslivros.model

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.biachacon.meuslivros.R

class LivroViewHolderRV(v: View) : RecyclerView.ViewHolder(v){

    val nomeLivro: TextView
    val nomeAutor: TextView
    val notaLivro: TextView

    init {
        nomeLivro = v.findViewById<TextView>(R.id.nomeLivro)
        nomeAutor = v.findViewById<TextView>(R.id.nomeAutor)
        notaLivro = v.findViewById<TextView>(R.id.notaLivro)

    }

}