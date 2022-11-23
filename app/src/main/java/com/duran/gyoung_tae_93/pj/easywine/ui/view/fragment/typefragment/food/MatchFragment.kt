package com.duran.gyoung_tae_93.pj.easywine.ui.view.fragment.typefragment.food

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.duran.gyoung_tae_93.pj.easywine.R
import com.duran.gyoung_tae_93.pj.easywine.databinding.FragmentMatchBinding

class MatchFragment : Fragment() {

    private lateinit var binding: FragmentMatchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_match, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setLayoutClick()

    }

    private fun setLayoutClick() {
        binding.layoutFoodArea.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_matchFragment_to_foodMatchFragment)
        }

        binding.layoutWineArea.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_matchFragment_to_wineMatchFragment)
        }
    }

}