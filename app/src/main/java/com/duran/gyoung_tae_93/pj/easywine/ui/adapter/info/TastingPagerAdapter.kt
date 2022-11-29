package com.duran.gyoung_tae_93.pj.easywine.ui.adapter.info

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.duran.gyoung_tae_93.pj.easywine.ui.view.fragment.infofragment.tasting.*

class TastingPagerAdapter(fm: Fragment): FragmentStateAdapter(fm) {

    override fun getItemCount(): Int = 5

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> TastingTanninFragment()
            1 -> TastingBodyFragment()
            2 -> TastingSweetFragment()
            3 -> TastingAcidityFragment()
            else -> TastingAlcoholFragment()
        }
    }
}