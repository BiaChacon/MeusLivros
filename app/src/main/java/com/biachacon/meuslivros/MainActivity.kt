package com.biachacon.meuslivros

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val CODE = 99

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cadastrarBt.setOnClickListener {
            var i = Intent(this, CadastrarLivrosActivity::class.java)
            startActivityForResult(i,CODE)
        }

        listarBt.setOnClickListener {
            var i = Intent(this, ListarLivrosActivity::class.java)
            startActivity(i)
        }

        buscarBt.setOnClickListener {
            var i = Intent(this, BuscarLivroActivity::class.java)
            startActivity(i)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            CODE ->{
                when(resultCode){
                    Activity.RESULT_OK->{
                        Toast.makeText(this, R.string.livro_salvo, Toast.LENGTH_SHORT).show()
                    }
                    Activity.RESULT_CANCELED->{
                        Toast.makeText(this, R.string.cadastro_cancelado , Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

}
