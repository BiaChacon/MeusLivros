package com.biachacon.meuslivros.model

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.biachacon.meuslivros.R

class LivroAdapterRV(var c:Context, var livros:List<Livro>) : RecyclerView.Adapter<LivroViewHolderRV>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LivroViewHolderRV {
        val view = LayoutInflater.from(c).inflate(R.layout.livro_card_layout, parent, false)
        return LivroViewHolderRV(view)

    }

    override fun getItemCount(): Int {
        return livros.size
    }

    override fun onBindViewHolder(holder: LivroViewHolderRV, position: Int) {

        val livroAtual = livros[position]

        holder.nomeLivro.text = livroAtual.titulo
        holder.nomeAutor.text = livroAtual.autor
        holder.notaLivro.rating = livroAtual.nota
        holder.img.setImageResource(livroAtual.img)

        if (livroAtual.bitten){
            holder.img.setImageResource(R.drawable.lido)
        }else{
            holder.img.setImageResource(R.drawable.lendo)
        }

        holder.img.setOnClickListener {
            livroAtual.bitten = true
            notifyItemChanged(position)
        }

    }

}