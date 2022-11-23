package com.duran.gyoung_tae_93.pj.easywine.ui.view.fragment.typefragment.food

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.duran.gyoung_tae_93.pj.easywine.R
import com.duran.gyoung_tae_93.pj.easywine.databinding.FragmentFoodBinding
import com.duran.gyoung_tae_93.pj.easywine.ui.view.activity.MainActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class FoodFragment : Fragment() {

    private lateinit var binding: FragmentFoodBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_food, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val item = arguments?.getString("item")

        Log.e("dddddd", item.toString())

        setHideBar()
        setMoveFoodInfoDialog()
        setNextArrowClick()
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
     *  음식과 와인 매칭이란? 클릭 -> 간단한 정보 알려주는 Dialog 열기
     */
    private fun setMoveFoodInfoDialog() {
        binding.tvFoodInfoDialog.setOnClickListener {
            val dialog = DialogFoodInfoFragment()
            dialog.show(parentFragmentManager, "FoodInfoDialog")
        }
    }

    private fun setNextArrowClick() {
        binding.linearMoreWineMatch.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_foodFragment_to_matchFragment)
        }
    }

}