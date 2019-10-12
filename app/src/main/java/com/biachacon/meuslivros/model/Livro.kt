package com.biachacon.meuslivros.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Livro (
    var titulo:String,
    var autor:String,
    var ano:Int,
    var nota:Float,
    var img: Int
    ){
    var bitten: Boolean = false
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0
}
