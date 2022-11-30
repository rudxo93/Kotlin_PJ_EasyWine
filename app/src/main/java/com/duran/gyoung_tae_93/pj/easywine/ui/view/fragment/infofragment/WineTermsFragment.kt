package com.duran.gyoung_tae_93.pj.easywine.ui.view.fragment.infofragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.duran.gyoung_tae_93.pj.easywine.R
import com.duran.gyoung_tae_93.pj.easywine.databinding.FragmentWineTermsBinding
import com.duran.gyoung_tae_93.pj.easywine.ui.adapter.info.WinePagerAdapter
import com.duran.gyoung_tae_93.pj.easywine.ui.view.activity.MainActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayoutMediator

class WineTermsFragment : Fragment() {

    private lateinit var binding: FragmentWineTermsBinding

    private val closeBtn by lazy { binding.btnWineClose }
    private val tabTitles = arrayListOf("빈티지", "디켄딩", "아로마&부케", "마리아주")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_wine_terms, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setCloseBtnClick()
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

    /**
     *  ViewPager & TabLayout Setting
     */
    @SuppressLint("InflateParams")
    private fun setTabWithViewPager() {
        binding.wineViewPager.adapter = WinePagerAdapter(this)
        TabLayoutMediator(binding.tabWineTitle, binding.wineViewPager) { tab, position ->
            tab.text = tabTitles[position]
        }.attach()

        for (i in 0..3) {
            val textView = LayoutInflater.from(requireContext())
                .inflate(R.layout.tab_tasting_title, null) as TextView

            binding.tabWineTitle.getTabAt(i)?.customView = textView
        }
    }

    private fun setCloseBtnClick() {
        closeBtn.setOnClickListener {
            it.findNavController().navigate(R.id.action_wineTermsFragment_to_fragment_info)
        }
    }

}