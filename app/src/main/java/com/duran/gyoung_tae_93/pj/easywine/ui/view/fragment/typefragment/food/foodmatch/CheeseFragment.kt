package com.duran.gyoung_tae_93.pj.easywine.ui.view.fragment.typefragment.food.foodmatch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.duran.gyoung_tae_93.pj.easywine.R
import com.duran.gyoung_tae_93.pj.easywine.databinding.FragmentCheeseBinding
import com.duran.gyoung_tae_93.pj.easywine.databinding.FragmentShellfishBinding

class CheeseFragment : Fragment() {

    private lateinit var binding: FragmentCheeseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_cheese, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setFabButton()
    }

    /**
     *  FloatingActionButton Setting
     */
    private fun setFabButton() {
        binding.fabHome.setOnClickListener {
            it.findNavController().navigate(R.id.action_cheeseFragment_to_fragment_type)
        }
    }

}