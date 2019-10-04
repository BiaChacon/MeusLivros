package com.biachacon.meuslivros

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.room.Room
import com.biachacon.meuslivros.database.AppDatabase
import com.biachacon.meuslivros.model.Livro
import kotlinx.android.synthetic.main.activity_buscar_livro.*

class BuscarLivroActivity : AppCompatActivity() {

    val db: AppDatabase by lazy {
        Room.databaseBuilder(
            this,
            AppDatabase::class.java, "meus-livros")
            .allowMainThreadQueries()
            .build()
    }

    var titulos = arrayOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buscar_livro)

        var livros = db.livroDao().listAll()
        livros.forEach { Log.i("APPROOM", it.toString()) }

        /*for (i in 0 until  livros.size){
            titulos[i] = livros[i].titulo
        }*/

        var livroToListAdapter = ArrayAdapter<String>(
            this,
            android.R.layout.simple_expandable_list_item_1,
            titulos)

        autocomplete.setAdapter(livroToListAdapter)

        autocomplete.setOnItemClickListener { adapterView, view, i, l ->
            var selected = adapterView.getItemAtPosition(i)

            Toast.makeText(this, "$selected $l", Toast.LENGTH_SHORT).show()

            mostrarLivro(db.livroDao().findByName(selected.toString()))

        }

    }

    fun mostrarLivro(livro: Livro){

        tv1.visibility = View.VISIBLE
        tv2.visibility = View.VISIBLE
        tv3.visibility = View.VISIBLE
        tv4.visibility = View.VISIBLE

        titulo2.text = livro.titulo
        autor2.text = livro.autor
        ano2.text = livro.ano.toString()
        nota2.text = livro.nota.toString()

    }

}
