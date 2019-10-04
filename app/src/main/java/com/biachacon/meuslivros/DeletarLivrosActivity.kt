package com.biachacon.meuslivros

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import androidx.room.Room
import com.biachacon.meuslivros.database.AppDatabase
import com.biachacon.meuslivros.model.Livro
import kotlinx.android.synthetic.main.activity_buscar_livro.*
import kotlinx.android.synthetic.main.activity_deletar_livros.*

class DeletarLivrosActivity : AppCompatActivity() {

    val db: AppDatabase by lazy {
        Room.databaseBuilder(
            this,
            AppDatabase::class.java, "meus-livros")
            .allowMainThreadQueries()
            .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_deletar_livros)

        var livros = db.livroDao().listAll()
        livros.forEach { Log.i("APPROOM", it.toString()) }

        var size = livros.size

        var titulos = Array<String>(size, {i -> i.toString()})

        for (i in 0 until  livros.size){
            titulos[i] = livros[i].titulo
        }

        var livroToListAdapter = ArrayAdapter<String>(
            this,
            android.R.layout.simple_expandable_list_item_1,
            titulos)

        autocomplete2.setAdapter(livroToListAdapter)

        autocomplete2.setOnItemClickListener { adapterView, view, i, l ->
            var selected = adapterView.getItemAtPosition(i)

            mostrarLivroEscolhido(db.livroDao().findByName(selected.toString()))

            var li = db.livroDao().findByName(selected.toString())

            deletarDBt.visibility = View.VISIBLE
            cancelarDBt.visibility = View.VISIBLE

            deletarDBt.setOnClickListener {
                db.livroDao().delete(li)
                setResult(Activity.RESULT_OK)
                finish()
            }

            cancelarDBt.setOnClickListener {
                setResult(Activity.RESULT_CANCELED)
                finish()
            }

        }


    }

    fun mostrarLivroEscolhido(livro: Livro){

        tvD1.visibility = View.VISIBLE
        tvD2.visibility = View.VISIBLE
        tvD3.visibility = View.VISIBLE
        tvD4.visibility = View.VISIBLE

        tituloD.text = livro.titulo
        autorD.text = livro.autor
        anoD.text = livro.ano.toString()
        notaD.text = livro.nota.toString()

    }

}
