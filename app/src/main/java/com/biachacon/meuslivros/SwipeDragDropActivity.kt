package com.biachacon.meuslivros

import android.graphics.Canvas
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.biachacon.meuslivros.database.AppDatabase
import com.biachacon.meuslivros.model.Livro
import com.biachacon.meuslivros.model.LivroAdapterRV
import com.biachacon.meuslivros.model.LivroAdapterSDD
import kotlinx.android.synthetic.main.activity_swipe_drag_drop.*

class SwipeDragDropActivity : AppCompatActivity() {

    val db: AppDatabase by lazy {
        Room.databaseBuilder(
            this,
            AppDatabase::class.java, "meus-livros")
            .allowMainThreadQueries()
            .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_swipe_drag_drop)

        var listaLivros:MutableList<Livro> = db.livroDao().list()

        var adapter = LivroAdapterSDD(this,listaLivros )
        recyclerview2.adapter = adapter

        val layout = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        recyclerview2.layoutManager = layout

        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN, ItemTouchHelper.START or ItemTouchHelper.END )
        {

            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {

                val fromPosition = viewHolder.adapterPosition
                val toPosition = target.adapterPosition
                val adapter = recyclerView.adapter as LivroAdapterSDD
                adapter.mover(fromPosition, toPosition)

                return true

            }
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                var posicao = viewHolder.adapterPosition
                var adapter = recyclerview2.adapter as LivroAdapterSDD

                adapter.removerComTempo(posicao)

            }

            override fun onChildDraw(
                c: Canvas,
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                dX: Float,
                dY: Float,
                actionState: Int,
                isCurrentlyActive: Boolean
            ){
                val itemView = viewHolder.itemView
                val background = ColorDrawable(resources.getColor(R.color.red))

                if (viewHolder.adapterPosition === -1) {
                    return
                }

                if (dX < 0) {
                    background.setBounds(
                        (itemView.right + dX).toInt(),
                        itemView.top,
                        itemView.right,
                        itemView.bottom
                    )
                }else if (dX > 0) {
                    background.setBounds(
                        itemView.left,
                        itemView.top,
                        (dX).toInt(),
                        itemView.bottom
                    )
                }

                background.draw(c)

                super.onChildDraw(
                    c,
                    recyclerView,
                    viewHolder,
                    dX,
                    dY,
                    actionState,
                    isCurrentlyActive
                )

            }

            override fun isLongPressDragEnabled(): Boolean {
                return true
            }

            override fun isItemViewSwipeEnabled(): Boolean {
                return true
            }

        })

        itemTouchHelper.attachToRecyclerView(recyclerview2)

    }

}

