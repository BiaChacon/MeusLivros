package com.biachacon.meuslivros.model

import android.view.View
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.biachacon.meuslivros.R

class LivroViewHolderRV(v: View) : RecyclerView.ViewHolder(v){

    val nomeLivro: TextView
    val nomeAutor: TextView
    val notaLivro: RatingBar
    val img: ImageView

    val layoutNormal: LinearLayout = v.findViewById(R.id.layout_normal)
    val layoutGone: LinearLayout = v.findViewById(R.id.layout_gone)

    val undoButton: Button = v.findViewById(R.id.undo_button)
    init {
        nomeLivro = v.findViewById<TextView>(R.id.nomeLivro)
        nomeAutor = v.findViewById<TextView>(R.id.nomeAutor)
        notaLivro = v.findViewById<RatingBar>(R.id.notaLivro)
        img = v.findViewById(R.id.imgLivro)
    }

}