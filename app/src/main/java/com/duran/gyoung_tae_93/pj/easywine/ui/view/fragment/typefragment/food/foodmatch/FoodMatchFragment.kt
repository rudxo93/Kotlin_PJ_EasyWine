package com.duran.gyoung_tae_93.pj.easywine.ui.view.fragment.typefragment.food.foodmatch

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.duran.gyoung_tae_93.pj.easywine.R
import com.duran.gyoung_tae_93.pj.easywine.databinding.FragmentFoodMatchBinding
import com.duran.gyoung_tae_93.pj.easywine.ui.adapter.note.NoteRVAdapter
import com.duran.gyoung_tae_93.pj.easywine.ui.adapter.type.food.TypeFoodRVAdapter
import kotlin.collections.ArrayList

class FoodMatchFragment : Fragment() {

    private lateinit var binding: FragmentFoodMatchBinding

    lateinit var rvAdapter: TypeFoodRVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_food_match, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val list = arrayListOf("고기 요리", "가금육", "생선 요리", "갑각류", "채소", "향신료", "치즈와 디저트")

        setRVSetting(list)

    }

    private fun setRVSetting(list: ArrayList<String>) {
        val rv = binding.rvMatchFood

        rvAdapter = TypeFoodRVAdapter(list)
        rv.adapter = rvAdapter
        rv.layoutManager = GridLayoutManager(context, 2)

        rvAdapter.setItemClickListener(object : TypeFoodRVAdapter.ItemClickListener {
            override fun onClick(view: View, position: Int) {
                when(position) {
                    0 -> {
                        view.findNavController().navigate(R.id.action_foodMatchFragment_to_meatFragment)
                    }
                    1 -> {
                        view.findNavController().navigate(R.id.action_foodMatchFragment_to_poultryMeatFragment)
                    }
                    2 -> {
                        view.findNavController().navigate(R.id.action_foodMatchFragment_to_fishFragment)
                    }
                    3 -> {
                        view.findNavController().navigate(R.id.action_foodMatchFragment_to_shellFishFragment)
                    }
                    4 -> {
                        view.findNavController().navigate(R.id.action_foodMatchFragment_to_vegetableFragment)
                    }
                    5 -> {
                        view.findNavController().navigate(R.id.action_foodMatchFragment_to_spiceFragment)
                    }
                    6 -> {
                        view.findNavController().navigate(R.id.action_foodMatchFragment_to_cheeseFragment)
                    }
                }
            }

        })

    }

}