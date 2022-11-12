package com.duran.gyoung_tae_93.pj.easywine.ui.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toolbar
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.duran.gyoung_tae_93.pj.easywine.R
import com.duran.gyoung_tae_93.pj.easywine.databinding.FragmentTypeBinding
import com.duran.gyoung_tae_93.pj.easywine.ui.view.activity.MainActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class TypeFragment : Fragment() {

    private lateinit var binding : FragmentTypeBinding

    private var isInfoOpen = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_type, container, false)

        setTypeInfo()

        binding.btnTypeStyle.setOnClickListener {
            it.findNavController().navigate(R.id.action_fragment_type_to_styleFragment, bundleOf("item1" to "스타일"))
        }
        binding.btnTypeCountry.setOnClickListener {
            it.findNavController().navigate(R.id.action_fragment_type_to_areaFragment, bundleOf("item1" to "생산지"))
        }
        binding.btnTypeFood.setOnClickListener {
            it.findNavController().navigate(R.id.action_fragment_type_to_foodFragment, bundleOf("item1" to "음식"))
        }

        return binding.root
    }

    private fun setTypeInfo() {
        binding.btnTypeInfo.setOnClickListener {
            val typeInfoContent = binding.tvTypeInfoContent

            isInfoOpen = !isInfoOpen
            if(isInfoOpen) {
                typeInfoContent.visibility = View.GONE
            } else {
                typeInfoContent.visibility = View.VISIBLE
            }
        }
    }

    override fun onResume() {
        super.onResume()
        (requireActivity() as MainActivity).supportActionBar!!.show()

        val navBar = activity?.findViewById<BottomNavigationView>(R.id.bottom_navigation)
        navBar?.visibility = View.VISIBLE
    }

}