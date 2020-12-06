package com.example.catalogue.list

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.catalogue.R

class MemeListViewModel : ViewModel(){

    private val source = mutableListOf(
                    Meme("Shrek", R.drawable.shrek, Category.Miscellanous, false),
                    Meme("Spooktober", R.drawable.spooktober, Category.Miscellanous, false),
                    Meme("Hello there", R.drawable.heelo_there, Category.PrequelMemes, false),
                    Meme("The senate", R.drawable.the_senate, Category.PrequelMemes, false),
                    Meme("The tragedy", R.drawable.the_tragedy, Category.PrequelMemes, false),
                    Meme("Beka", R.drawable.beka, Category.PWR, false),
                    Meme("Nie w4rto", R.drawable.nie_w4rto, Category.PWR, false),
                    Meme("Ocaml vs scala", R.drawable.ocaml_vs_scala, Category.PWR, false)).apply { shuffle() }

    private val usedList = mutableListOf<Meme>()
    val liveData = MutableLiveData<List<Meme>>(usedList)

    init{
        usedList.addAll(source)
    }

    fun removeMeme(i : Int){
        val meme = usedList[i]
        source.remove(meme)
        usedList.removeAt(i)
        liveData.value = usedList
    }

    fun changeFavourite(i : Int){
        usedList[i].isFavourite = !usedList[i].isFavourite
        liveData.value = usedList
    }

    private fun <T> filterBy( t: T, f: (Meme) -> T){
        usedList.clear()
        usedList.addAll(source.filterTo(mutableListOf()) { f(it) == t })
        liveData.value = usedList
    }

    fun filterByCategory(cat: Category){
        filterBy(cat) { it.category }
    }

    fun filterByFavourite(){
        filterBy(true) { it.isFavourite }
    }

    fun restore(){
        usedList.clear()
        usedList.addAll(source)
        liveData.value = usedList
    }
}
