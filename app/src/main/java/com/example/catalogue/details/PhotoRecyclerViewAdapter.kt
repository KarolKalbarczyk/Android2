package com.example.catalogue.details

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.catalogue.R
import kotlinx.android.synthetic.main.fragment_photo.view.*

class PhotoRecyclerViewAdapter(
    private val photoIds: List<Int>,
) : RecyclerView.Adapter<PhotoRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_photo, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = photoIds[position]
        holder.view.picture.setImageResource(item)
    }

    override fun getItemCount(): Int = photoIds.size

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view)
}