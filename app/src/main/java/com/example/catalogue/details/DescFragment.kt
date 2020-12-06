package com.example.catalogue.details

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.catalogue.R
import kotlinx.android.synthetic.main.desc_fragment.view.*

class DescFragment(name: String) : Fragment() {

    private val descId: Int

    init{
        descId = when(name) {
            "Shrek" -> R.string.shrek_desc
            "Spooktober" -> R.string.spooktober_desc
            "Hello there" -> R.string.hello_desc
            "The senate" -> R.string.senate_desc
            "The tragedy" -> R.string.tragedy_desc
            "Beka" -> R.string.beka_desc
            "Nie w4rto" -> R.string.w4rto_desc
            else -> R.string.scala_vs_ocaml_desc
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        requireActivity().setTitle(R.string.desc_title)
        val view = inflater.inflate(R.layout.desc_fragment, container, false)
        view.description.text = getText(descId)
        view.description.movementMethod = ScrollingMovementMethod()
        return view
    }
}