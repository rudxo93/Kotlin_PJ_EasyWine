package com.duran.gyoung_tae_93.pj.easywine.ui.view.fragment.typefragment.style.mbodyred

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.duran.gyoung_tae_93.pj.easywine.R
import com.duran.gyoung_tae_93.pj.easywine.databinding.FragmentSangioveseBinding

class SangioveseFragment : Fragment() {

    private lateinit var binding: FragmentSangioveseBinding

    var topType: String? = null
    var item1: String? = null
    var item2: String? = null
    var item3: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sangiovese, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fg = this

        item1 = arguments?.getString("item1")
        item2 = arguments?.getString("item2")
        item3 = arguments?.getString("item3")

        setTopType()
        setFabButton()
    }

    /**
     *  상단 Type text
     */
    private fun setTopType() {
        topType = "$item1  >  $item2  >  $item3"
    }

    /**
     *  Fab 버튼 setting
     */
    private fun setFabButton() {
        binding.fabHome.setOnClickListener {
            it.findNavController().navigate(R.id.action_sangioveseFragment_to_fragment_type)
        }
    }

}