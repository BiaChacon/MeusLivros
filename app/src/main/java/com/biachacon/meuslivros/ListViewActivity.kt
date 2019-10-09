package com.biachacon.meuslivros

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.room.Room
import com.biachacon.meuslivros.database.AppDatabase
import com.biachacon.meuslivros.model.Livro
import com.biachacon.meuslivros.model.LivroAdapter
import kotlinx.android.synthetic.main.activity_list_view.*

class ListViewActivity : AppCompatActivity() {

    val db: AppDatabase by lazy {
        Room.databaseBuilder(
            this,
            AppDatabase::class.java, "meus-livros")
            .allowMainThreadQueries()
            .build()
    }

    var listaLivros:List<Livro>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view)

        listaLivros = db.livroDao().list()


        listview.adapter = LivroAdapter(this, listaLivros as List<Livro>)
        listview.setOnItemClickListener{adapterView, view, i, l ->
            var livroSelecionado = listaLivros?.get(i)
            Toast.makeText(this, "${livroSelecionado?.titulo} id=${livroSelecionado?.id}", Toast.LENGTH_SHORT).show()
        }

    }
}
