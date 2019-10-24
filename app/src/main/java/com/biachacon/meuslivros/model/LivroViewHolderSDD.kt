package com.biachacon.meuslivros.model

import android.view.View
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.biachacon.meuslivros.R

class LivroViewHolderSDD(v: View) : RecyclerView.ViewHolder(v){

    val nomeLivro: TextView
    val img: ImageView

    val layoutNormal: LinearLayout
    val layoutGone: LinearLayout
    val undoButton: Button

    init {
        layoutNormal = v.findViewById(R.id.layout_normal)
        layoutGone = v.findViewById(R.id.layout_gone)
        undoButton = v.findViewById(R.id.undo_button)
        nomeLivro = v.findViewById<TextView>(R.id.nomeLivro)
        img = v.findViewById(R.id.imgLivro)
    }

}