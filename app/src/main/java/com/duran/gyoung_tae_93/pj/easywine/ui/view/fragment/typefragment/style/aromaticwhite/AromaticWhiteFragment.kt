package com.duran.gyoung_tae_93.pj.easywine.ui.view.fragment.typefragment.style.aromaticwhite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.duran.gyoung_tae_93.pj.easywine.R
import com.duran.gyoung_tae_93.pj.easywine.data.model.type.style.VarietyModel
import com.duran.gyoung_tae_93.pj.easywine.databinding.FragmentAromaticWhiteBinding
import com.duran.gyoung_tae_93.pj.easywine.databinding.FragmentFullBodyWhiteBinding
import com.duran.gyoung_tae_93.pj.easywine.ui.adapter.type.style.StyleWhiteLVAdapter

class AromaticWhiteFragment : Fragment() {

    private lateinit var binding: FragmentAromaticWhiteBinding

    lateinit var lvAdapter: StyleWhiteLVAdapter
    val items = mutableListOf<VarietyModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        items.add(VarietyModel(0, "슈냉 블랑"))
        items.add(VarietyModel(1, "게브르츠트라미너"))
        items.add(VarietyModel(2, "뮈스카 블랑"))
        items.add(VarietyModel(3, "리슬링"))
        items.add(VarietyModel(4, "토론테스"))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_aromatic_white, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val item1 = arguments?.getString("item1")
        val item2 = arguments?.getString("item2")

        getListView(item1, item2)
    }

    /**
     *  ListView setting, Navigation 이동 구현
     */
    private fun getListView(item1: String?, item2: String?) {
        lvAdapter = StyleWhiteLVAdapter(items)
        binding.lvTypeStyleAromaticWhite.adapter = lvAdapter

        binding.lvTypeStyleAromaticWhite.setOnItemClickListener { _, view, _, id ->
            when (id.toInt()) {
                0 -> {
                    view.findNavController()
                        .navigate(R.id.action_aromaticWhiteFragment_to_cheninBlancFragment, bundleOf("item1" to item1, "item2" to item2, "item3" to "슈냉 블랑"))
                }
                1 -> {
                    view.findNavController()
                        .navigate(R.id.action_aromaticWhiteFragment_to_gewurztraminerFragment, bundleOf("item1" to item1, "item2" to item2, "item3" to "게브르츠트라미너"))
                }
                2 -> {
                    view.findNavController()
                        .navigate(R.id.action_aromaticWhiteFragment_to_muscatBlancFragment, bundleOf("item1" to item1, "item2" to item2, "item3" to "뮈스카 블랑"))
                }
                3 -> {
                    view.findNavController()
                        .navigate(R.id.action_aromaticWhiteFragment_to_rieslingFragment, bundleOf("item1" to item1, "item2" to item2, "item3" to "리슬링"))
                }
                4 -> {
                    view.findNavController()
                        .navigate(R.id.action_aromaticWhiteFragment_to_torrontesFragment, bundleOf("item1" to item1, "item2" to item2, "item3" to "토론테스"))
                }
            }
        }
    }

}