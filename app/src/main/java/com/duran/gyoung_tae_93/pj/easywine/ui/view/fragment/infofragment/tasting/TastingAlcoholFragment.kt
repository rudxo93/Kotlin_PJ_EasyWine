package com.duran.gyoung_tae_93.pj.easywine.ui.view.fragment.infofragment.tasting

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.duran.gyoung_tae_93.pj.easywine.R
import com.duran.gyoung_tae_93.pj.easywine.databinding.FragmentTastingAcidityBinding
import com.duran.gyoung_tae_93.pj.easywine.databinding.FragmentTastingAlcoholBinding

class TastingAlcoholFragment : Fragment() {

    private lateinit var binding: FragmentTastingAlcoholBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_tasting_alcohol, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.linearNextWebview.setOnClickListener {
            it.findNavController().navigate(
                R.id.action_tastingTermsFragment_to_searchTermsFragment,
                bundleOf("item1" to "도수")
            )
        }
    }

}