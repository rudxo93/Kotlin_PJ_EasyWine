package com.duran.gyoung_tae_93.pj.easywine.ui.view.fragment.typefragment.area.europe

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.duran.gyoung_tae_93.pj.easywine.R
import com.duran.gyoung_tae_93.pj.easywine.databinding.FragmentEuropeBinding
import com.duran.gyoung_tae_93.pj.easywine.ui.view.fragment.typefragment.style.fbodyred.SyrahFragment

class EuropeFragment : Fragment() {

    private lateinit var binding: FragmentEuropeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_europe, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}