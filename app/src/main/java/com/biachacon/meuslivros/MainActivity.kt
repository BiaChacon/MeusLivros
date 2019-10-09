package com.biachacon.meuslivros

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val CODESALVA = 99
    val CODEDELETE = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cadastrarBt.setOnClickListener {
            var i = Intent(this, CadastrarLivrosActivity::class.java)
            startActivityForResult(i,CODESALVA)
        }

        listarBt.setOnClickListener {
            var i = Intent(this, ListarLivrosActivity::class.java)
            startActivity(i)
        }

        buscarBt.setOnClickListener {
            var i = Intent(this, BuscarLivroActivity::class.java)
            startActivity(i)
        }

        deletarBt.setOnClickListener {
            var i = Intent(this, DeletarLivrosActivity::class.java)
            startActivityForResult(i,CODEDELETE)
        }

         listViewBt.setOnClickListener {
             var i = Intent(this, ListViewActivity::class.java)
             startActivity(i)
         }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            CODESALVA ->{
                when(resultCode){
                    Activity.RESULT_OK->{
                        Toast.makeText(this, R.string.livro_salvo, Toast.LENGTH_SHORT).show()
                    }
                    Activity.RESULT_CANCELED->{
                        Toast.makeText(this, R.string.cancelado , Toast.LENGTH_SHORT).show()
                    }
                }
            }
            CODEDELETE ->{
                when(resultCode){
                    Activity.RESULT_OK->{
                        Toast.makeText(this, R.string.livro_deletado, Toast.LENGTH_SHORT).show()
                    }
                    Activity.RESULT_CANCELED->{
                        Toast.makeText(this, R.string.cancelado , Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

}
