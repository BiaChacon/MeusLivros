package com.biachacon.meuslivros.model

import android.view.View
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.biachacon.meuslivros.R

class LivroViewHolderRV(v: View) : RecyclerView.ViewHolder(v){

    val nomeLivro: TextView
    val nomeAutor: TextView
    val notaLivro: RatingBar
    val img: ImageView

    init {
        nomeLivro = v.findViewById<TextView>(R.id.nomeLivroC)
        nomeAutor = v.findViewById<TextView>(R.id.nomeAutorC)
        notaLivro = v.findViewById<RatingBar>(R.id.notaLivroC)
        img = v.findViewById(R.id.imgLivroC)
    }

}