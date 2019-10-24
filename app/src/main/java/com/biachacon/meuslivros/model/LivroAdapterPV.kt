package com.biachacon.meuslivros.model

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.biachacon.meuslivros.R

class LivroAdapterPV(var c: Context, var livros:List<Livro>) : PagerAdapter(){

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        val view = LayoutInflater.from(c).inflate(R.layout.livro_inflater_pv, container, false)

        val img:ImageView = view.findViewById(R.id.imageLivro)
        img.setImageResource(livros[position].img)
        container.addView(view)

        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
        container.removeView(obj as View)
    }

    override fun isViewFromObject(view: View, obj: Any): Boolean {
        return view == obj
    }

    override fun getCount(): Int {
        return livros.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        return livros[position].titulo
    }

}