package com.biachacon.meuslivros

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.room.Room
import com.biachacon.meuslivros.database.AppDatabase
import com.biachacon.meuslivros.model.Livro
import kotlinx.android.synthetic.main.activity_cadastrar_livros.*

class CadastrarLivrosActivity : AppCompatActivity() {

    val db:AppDatabase by lazy {
        Room.databaseBuilder(
            this,
            AppDatabase::class.java, "meus-livros")
            .allowMainThreadQueries()
            .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastrar_livros)

        salvarBt.setOnClickListener {
            db.livroDao().insert(
                Livro(
                    tituloEdit.text.toString(),
                    autorEdit.text.toString(),
                    anoEdit.text.toString().toInt(),
                    estrelinha.rating,
                    R.drawable.lendo
                )
            )

            Log.i("cadastro"," Livro Salvo"+"["+
                    Livro(
                        tituloEdit.text.toString(),
                        autorEdit.text.toString(),
                        anoEdit.text.toString().toInt(),
                        estrelinha.rating,
                        R.drawable.lendo
                    ) +"]")

            setResult(Activity.RESULT_OK)
            finish()
        }

        cancelarBt.setOnClickListener {
            setResult(Activity.RESULT_CANCELED)
            finish()
        }
    }

}
