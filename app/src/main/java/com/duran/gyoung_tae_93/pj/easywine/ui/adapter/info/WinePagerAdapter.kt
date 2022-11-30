package com.duran.gyoung_tae_93.pj.easywine.ui.adapter.info

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.duran.gyoung_tae_93.pj.easywine.ui.view.fragment.infofragment.tasting.*
import com.duran.gyoung_tae_93.pj.easywine.ui.view.fragment.infofragment.wine.*

class WinePagerAdapter(fm: Fragment): FragmentStateAdapter(fm) {

    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> WineVintageFragment()
            1 -> WineDecantingFragment()
            2 -> WineAromaFragment()
            else -> WineMarriageFragment()
        }
    }
}