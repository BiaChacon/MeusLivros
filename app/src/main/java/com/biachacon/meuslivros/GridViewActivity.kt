package com.biachacon.meuslivros

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.room.Room
import com.biachacon.meuslivros.database.AppDatabase
import com.biachacon.meuslivros.model.Livro
import com.biachacon.meuslivros.model.LivroAdapter
import com.biachacon.meuslivros.model.LivroAdapterGV
import kotlinx.android.synthetic.main.activity_grid_view.*
import kotlinx.android.synthetic.main.activity_list_view.*

class GridViewActivity : AppCompatActivity() {

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
        setContentView(R.layout.activity_grid_view)

        listaLivros = db.livroDao().list()


        listviewG.adapter = LivroAdapterGV(this, listaLivros as List<Livro>)
        listviewG.setOnItemClickListener{adapterView, view, i, l ->
            var livroSelecionado = listaLivros?.get(i)
            Toast.makeText(this, "${livroSelecionado?.titulo} id=${livroSelecionado?.id}", Toast.LENGTH_SHORT).show()
        }

    }

}
