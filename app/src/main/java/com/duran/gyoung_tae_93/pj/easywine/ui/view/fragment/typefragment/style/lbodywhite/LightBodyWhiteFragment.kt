package com.duran.gyoung_tae_93.pj.easywine.ui.view.fragment.typefragment.style.lbodywhite

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
import com.duran.gyoung_tae_93.pj.easywine.databinding.FragmentLightBodyWhiteBinding
import com.duran.gyoung_tae_93.pj.easywine.databinding.FragmentSparklingBinding
import com.duran.gyoung_tae_93.pj.easywine.ui.adapter.type.style.StyleSparklingLVAdapter
import com.duran.gyoung_tae_93.pj.easywine.ui.adapter.type.style.StyleWhiteLVAdapter

class LightBodyWhiteFragment : Fragment() {

    private lateinit var binding: FragmentLightBodyWhiteBinding

    lateinit var lvAdapter: StyleWhiteLVAdapter
    val items = mutableListOf<VarietyModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        items.add(VarietyModel(0, "알바리뇨"))
        items.add(VarietyModel(1, "그뤼너 펠트리너"))
        items.add(VarietyModel(2, "뮈스카데"))
        items.add(VarietyModel(3, "피노 그리"))
        items.add(VarietyModel(4, "쇼비뇽 블랑"))
        items.add(VarietyModel(5, "소아베"))
        items.add(VarietyModel(6, "베르멘티노"))

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_light_body_white, container, false)
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
        binding.lvTypeStyleLightBodyWhite.adapter = lvAdapter

        binding.lvTypeStyleLightBodyWhite.setOnItemClickListener { _, view, _, id ->
            when (id.toInt()) {
                0 -> {
                    view.findNavController()
                        .navigate(R.id.action_lightBodyWhiteFragment_to_albarinoFragment, bundleOf("item1" to item1, "item2" to item2, "item3" to "알바리뇨"))
                }
                1 -> {
                    view.findNavController()
                        .navigate(R.id.action_lightBodyWhiteFragment_to_gruenerFragment, bundleOf("item1" to item1, "item2" to item2, "item3" to "그뤼너 펠트리너"))
                }
                2 -> {
                    view.findNavController()
                        .navigate(R.id.action_lightBodyWhiteFragment_to_muscadetFragment, bundleOf("item1" to item1, "item2" to item2, "item3" to "뮈스카데"))
                }
                3 -> {
                    view.findNavController()
                        .navigate(R.id.action_lightBodyWhiteFragment_to_pinotGrisFragment, bundleOf("item1" to item1, "item2" to item2, "item3" to "피노 그리"))
                }
                4 -> {
                    view.findNavController()
                        .navigate(R.id.action_lightBodyWhiteFragment_to_sauvignonBlancFragment, bundleOf("item1" to item1, "item2" to item2, "item3" to "쇼비뇽 블랑"))
                }
                5 -> {
                    view.findNavController()
                        .navigate(R.id.action_lightBodyWhiteFragment_to_soaveFragment, bundleOf("item1" to item1, "item2" to item2, "item3" to "소아베"))
                }
                6 -> {
                    view.findNavController()
                        .navigate(R.id.action_lightBodyWhiteFragment_to_vermentinoFragment, bundleOf("item1" to item1, "item2" to item2, "item3" to "베르멘티노"))
                }
            }
        }
    }

}