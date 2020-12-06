package com.example.catalogue.list

import android.content.res.Resources
import android.graphics.Color
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.content.res.ResourcesCompat
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.example.catalogue.R
import com.example.catalogue.details.MEME_KEY
import kotlinx.android.synthetic.main.meme_element.view.*

class MemeEntryAdapter(private val dataset: List<Meme>,
                       private val res: Resources,
                       private val viewModel: MemeListViewModel,
                       private val navController: NavController
) : RecyclerView.Adapter<MemeEntryAdapter.BMIViewHolder>() {

    class BMIViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BMIViewHolder {
        val textView = LayoutInflater.from(parent.context)
            .inflate(R.layout.meme_element, parent, false)
        return BMIViewHolder(textView)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: BMIViewHolder, position: Int) {
        val meme = dataset[position]
        holder.itemView.setOnClickListener {
            navController.navigate(
                R.id.action_listActivity_to_detailsActivity2,
                bundleOf(MEME_KEY to meme.name)
            )
        }
        holder.view.addToFavourites.setOnClickListener { viewModel.changeFavourite(position) }
        holder.view.addToFavourites.setBackgroundColor(Color.TRANSPARENT)
        holder.view.addToFavourites.foreground = res.getDrawable(if (meme.isFavourite) R.mipmap.hearth_foreground else R.drawable.broken_heart_foreground, res.newTheme())
        holder.view.setBackgroundColor(res.getColor(meme.category.colorId, res.newTheme()))
        holder.view.categoryName.text = res.getText(meme.category.textId)
        holder.view.title.text = meme.name
        holder.view.photo.setImageResource(meme.photo)
    }

    override fun getItemCount() = dataset.size
}