package com.duran.gyoung_tae_93.pj.easywine.ui.view.fragment.readyfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.duran.gyoung_tae_93.pj.easywine.R
import com.duran.gyoung_tae_93.pj.easywine.databinding.FragmentWineDrinkBinding
import com.duran.gyoung_tae_93.pj.easywine.ui.view.activity.MainActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class WineDrinkFragment : Fragment() {

    private lateinit var binding: FragmentWineDrinkBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_wine_drink, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getToolbarSetting()
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

    private fun getToolbarSetting() {
        binding.wdToolbar.setTitle("와인 즐기는 방법")
        activity?.actionBar?.setDisplayShowTitleEnabled(true)
    }

}