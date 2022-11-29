package com.duran.gyoung_tae_93.pj.easywine.ui.view.fragment.infofragment

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.duran.gyoung_tae_93.pj.easywine.R
import com.duran.gyoung_tae_93.pj.easywine.databinding.FragmentTastingTermsBinding
import com.duran.gyoung_tae_93.pj.easywine.ui.adapter.info.TastingPagerAdapter
import com.duran.gyoung_tae_93.pj.easywine.ui.view.activity.MainActivity
import com.duran.gyoung_tae_93.pj.easywine.ui.view.fragment.infofragment.tasting.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayoutMediator

class TastingTermsFragment : Fragment() {

    private lateinit var binding: FragmentTastingTermsBinding

    private val tabTitles = arrayListOf("타닌", "바디", "당도", "산도", "도수")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_tasting_terms, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setTabWithViewPager()
        setHideBar()
    }

    /**
     *  Navigation 이동 시 Top Toolbar, BottomNavigationView 숨기기
     */
    private fun setHideBar() {
        (requireActivity() as MainActivity).supportActionBar!!.hide()

        val navBar = activity?.findViewById<BottomNavigationView>(R.id.bottom_navigation)
        navBar?.visibility = View.GONE
    }

    private fun setTabWithViewPager() {
        binding.tastingViewPager.adapter = TastingPagerAdapter(this)
        TabLayoutMediator(binding.tabTastingTitle, binding.tastingViewPager) { tab, position ->
            tab.text = tabTitles[position]
        }.attach()

        for(i in 0..4) {
            val textView = LayoutInflater.from(requireContext()).inflate(R.layout.tab_tasting_title, null) as TextView

            binding.tabTastingTitle.getTabAt(i)?.customView = textView
        }
    }


}