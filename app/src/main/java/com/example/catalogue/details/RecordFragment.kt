package com.example.catalogue.details

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.catalogue.R
import kotlinx.android.synthetic.main.record_fragment.view.*

class RecordFragment(val name: String) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        requireActivity().setTitle(R.string.record_title)
        var s = requireActivity().getText(R.string.interesting_facts).toString() + '\n'
        val array = resources.getStringArray(R.array.array)

        for (fact in array){
            s += "•  ${fact}\n"
        }

        val view =  inflater.inflate(R.layout.record_fragment, container, false)
        view.record.text = s
        view.record.movementMethod = ScrollingMovementMethod()
        return view
    }

}