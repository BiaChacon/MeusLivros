package com.biachacon.meuslivros.model

import android.content.Context
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.biachacon.meuslivros.R
import java.util.*

class LivroAdapterSDD(var c:Context, var livros:MutableList<Livro>) : RecyclerView.Adapter<LivroViewHolderSDD>() {

    private val PENDING_REMOVAL_TIMEOUT:Long = 3000
    var itemsPendingRemoval = ArrayList<Livro>()

    private val handler = Handler()
    var pendingRunnables: HashMap<Livro, Runnable> = HashMap()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LivroViewHolderSDD {
        val view = LayoutInflater.from(c).inflate(R.layout.novo_livro_inflater, parent, false)
        return LivroViewHolderSDD(view)
    }

    override fun getItemCount(): Int {
        return livros.size
    }

    override fun onBindViewHolder(holder: LivroViewHolderSDD, position: Int) {

        val livroAtual = livros[position]

        holder.nomeLivro.text = livroAtual.titulo
        holder.img.setImageResource(livroAtual.img)

        if (livroAtual.bitten){
            holder.img.setImageResource(R.drawable.lido)
        }else{
            holder.img.setImageResource(R.drawable.lendo)
        }
        if (itemsPendingRemoval.contains(livroAtual)) {

            holder.layoutNormal.setVisibility(View.GONE)
            holder.layoutGone.setVisibility(View.VISIBLE)
            holder.undoButton.setVisibility(View.VISIBLE)

            holder.undoButton.setOnClickListener {
                val pendingRemovalRunnable = pendingRunnables[livroAtual]
                pendingRunnables.remove(livroAtual)
                if (pendingRemovalRunnable != null) {
                    handler.removeCallbacks(pendingRemovalRunnable)
                }
                itemsPendingRemoval.remove(livroAtual)
                notifyItemChanged(livros.indexOf(livroAtual))
            }

        }else {
            holder.nomeLivro.setText(livroAtual.titulo)
            holder.layoutNormal.setVisibility(View.VISIBLE)
            holder.layoutGone.setVisibility(View.GONE)
            holder.undoButton.setVisibility(View.GONE)
            holder.undoButton.setOnClickListener(null)
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

    fun remover (position: Int){

        var livro = livros[position]

        if (livros.contains(livro)){
            livros.removeAt(position)
            notifyItemRemoved(position)
        }

    }

    fun removerComTempo(position: Int) {

        val livro = livros[position]

        if (!itemsPendingRemoval.contains(livro)) {
            itemsPendingRemoval.add(livro)
            notifyItemChanged(position)
            var pendingRemovalRunnable = Runnable {
                remover(position)
            }
            handler.postDelayed(pendingRemovalRunnable, PENDING_REMOVAL_TIMEOUT)
            pendingRunnables[livro] = pendingRemovalRunnable
        }

    }

    fun mover(fromPosition: Int, toPosition: Int) {

        if (fromPosition < toPosition) {
            for (i in fromPosition until toPosition) {
                Collections.swap(livros, i, i + 1)
            }
        } else {
            for (i in fromPosition downTo toPosition + 1) {
                Collections.swap(livros, i, i - 1)
            }
        }

        notifyItemMoved(fromPosition, toPosition)
        notifyItemChanged(toPosition)
        notifyItemChanged(fromPosition)

    }

}