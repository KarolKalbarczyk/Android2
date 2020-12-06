package com.example.catalogue.list

import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RectShape
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.compose.ui.res.colorResource
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.catalogue.R
import kotlinx.android.synthetic.main.list_fragment.view.*

class ListFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var viewModel: MemeListViewModel

    private var lastButton: Button? = null

    @RequiresApi(Build.VERSION_CODES.M)
    private fun setLast(b: Button, filter: () -> Unit){
        lastButton?.setBackgroundColor(requireActivity().getColor(R.color.purple_500))
        if (b == lastButton) {
            viewModel.restore()
            lastButton = null
        } else{
            lastButton = b
            filter()
        }
        lastButton?.setBackgroundColor(requireActivity().getColor(R.color.pressed))
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.list_fragment, container, false)
        viewManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        viewModel = ViewModelProvider(this).get(MemeListViewModel::class.java)

        viewAdapter = MemeEntryAdapter(viewModel.liveData.value!!, resources, viewModel, findNavController())

        view.apply {
            pwrButton.setOnClickListener { setLast(pwrButton) { viewModel.filterByCategory(Category.PWR) } }
            prequelButton.setOnClickListener { setLast(prequelButton) { viewModel.filterByCategory(Category.PrequelMemes) } }
            miscButton.setOnClickListener { setLast(miscButton) { viewModel.filterByCategory(Category.Miscellanous) } }
            favButton.setOnClickListener { setLast(favButton) { viewModel.filterByFavourite() } }
        }

        configureRecycler(view)
        return view
    }

    private fun configureRecycler(view: View) {
        recyclerView = view.my_recycler_view.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
        //ta ilosc bojlerplejtu ;_____;
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            ).also { deco ->
                with(ShapeDrawable(RectShape())) {
                    intrinsicHeight = (resources.displayMetrics.density).toInt()
                    alpha = resources.getInteger(R.integer.alpha)
                    deco.setDrawable(this)
                }
            })

        viewModel.liveData.observe(requireActivity()) { viewAdapter.notifyDataSetChanged() }

        val touchHelper =
            ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ) = false

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    viewModel.removeMeme(viewHolder.layoutPosition)
                }
            })
        touchHelper.attachToRecyclerView(recyclerView)
    }

}

