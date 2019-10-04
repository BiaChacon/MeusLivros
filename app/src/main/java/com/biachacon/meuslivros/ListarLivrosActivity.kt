package com.biachacon.meuslivros

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.room.Room
import com.biachacon.meuslivros.database.AppDatabase
import kotlinx.android.synthetic.main.activity_listar_livros.*

class ListarLivrosActivity : AppCompatActivity() {

    val db: AppDatabase by lazy {
        Room.databaseBuilder(
            this,
            AppDatabase::class.java, "meus-livros")
            .allowMainThreadQueries()
            .build()
    }

    var livroAtual = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listar_livros)

        var livros = db.livroDao().listAll()
        livros.forEach { Log.i("APPROOM", it.toString()) }

        fun alterarDados(){

            Log.i("livro", livroAtual.toString())


            anterior.visibility = View.VISIBLE
            proximo.visibility = View.VISIBLE

            if (livroAtual == 0)
                anterior.visibility = View.INVISIBLE

            if (livroAtual == livros.size-1)
                proximo.visibility = View.INVISIBLE


            titulo.setText(livros[livroAtual].titulo)
            autor.setText(livros[livroAtual].autor)
            ano.setText(livros[livroAtual].ano.toString())
            nota.setText(livros[livroAtual].nota.toString())

        }

        if (livros.size < 1) {
            semLivro()
            nenhum.visibility = View.VISIBLE
            imageView.visibility = View.VISIBLE
            anterior.visibility = View.INVISIBLE
            proximo.visibility = View.INVISIBLE
        }else{
            imageView.visibility = View.INVISIBLE
            alterarDados()
        }

        anterior.setOnClickListener {
            if (livroAtual>0){
                livroAtual--
                alterarDados()
            }
        }

        proximo.setOnClickListener {
            if (livroAtual<=livros.size){
                livroAtual++
                alterarDados()
            }
        }

    }

    fun semLivro(){

        titulo.visibility = View.INVISIBLE
        autor.visibility = View.INVISIBLE
        ano.visibility = View.INVISIBLE
        nota.visibility = View.INVISIBLE

        textView10.visibility = View.INVISIBLE
        textView9.visibility = View.INVISIBLE
        textView7.visibility = View.INVISIBLE
        textView5.visibility = View.INVISIBLE

    }

}
