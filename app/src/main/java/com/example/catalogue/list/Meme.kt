package com.example.catalogue.list

import com.example.catalogue.R

enum class Category(val textId: Int, val colorId: Int){
    PrequelMemes(R.string.prequel_memes, R.color.prequel_memes),
    Miscellanous(R.string.miscellaneous, R.color.miscellanous),
    PWR(R.string.pwr, R.color.pwr)
}

data class Meme(val name: String, val photo: Int, val category: Category, var isFavourite: Boolean = false)


