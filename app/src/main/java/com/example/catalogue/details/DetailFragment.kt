package com.example.catalogue.details

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.catalogue.R
import com.example.catalogue.list.MemeListViewModel

private const val NUM_PAGES = 3
const val MEME_KEY = "meme"

public class DetailFragment : Fragment() {

    private lateinit var viewPager: ViewPager2
    private lateinit var name: String

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.details_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewPager = view.findViewById(R.id.pager)
        name = arguments?.getString(MEME_KEY) ?: ""
        val pagerAdapter = ScreenSlidePagerAdapter(this)
        viewPager.adapter = pagerAdapter
    }

    private inner class ScreenSlidePagerAdapter(fa: Fragment) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = NUM_PAGES

        override fun createFragment(position: Int): Fragment = when(position){
            1 -> DescFragment(name)
            2 -> RecordFragment(name)
            else -> PhotoFragment(name)
        }
    }
}
