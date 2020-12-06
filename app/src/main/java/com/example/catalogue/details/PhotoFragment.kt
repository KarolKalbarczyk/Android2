package com.example.catalogue.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.catalogue.R

class PhotoFragment(name: String) : Fragment() {

    private val drawableId: Int

    init{
        drawableId = when(name) {
            "Shrek" -> R.drawable.shrek
            "Spooktober" -> R.drawable.spooktober
            "Hello there" -> R.drawable.heelo_there
            "The senate" -> R.drawable.the_senate
            "The tragedy" -> R.drawable.the_tragedy
            "Beka" -> R.drawable.beka
            "Nie w4rto" -> R.drawable.nie_w4rto
            else -> R.drawable.ocaml_vs_scala
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        requireActivity().setTitle(R.string.gallery_title)
        val view = inflater.inflate(R.layout.fragment_photo_list, container, false)

        (view as RecyclerView).apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = PhotoRecyclerViewAdapter((1..4).map { drawableId })
        }

        return view
    }

}

