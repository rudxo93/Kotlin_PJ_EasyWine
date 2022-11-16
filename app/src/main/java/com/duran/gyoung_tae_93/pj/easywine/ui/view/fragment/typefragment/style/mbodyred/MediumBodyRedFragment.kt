package com.duran.gyoung_tae_93.pj.easywine.ui.view.fragment.typefragment.style.mbodyred

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
import com.duran.gyoung_tae_93.pj.easywine.databinding.FragmentLightBodyRedBinding
import com.duran.gyoung_tae_93.pj.easywine.databinding.FragmentMediumBodyRedBinding
import com.duran.gyoung_tae_93.pj.easywine.ui.adapter.type.style.StyleRedLVAdapter

class MediumBodyRedFragment : Fragment() {

    private lateinit var binding: FragmentMediumBodyRedBinding

    lateinit var lvAdapter: StyleRedLVAdapter
    val items = mutableListOf<VarietyModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        items.add(VarietyModel(0, "바르베라"))
        items.add(VarietyModel(1, "까베르네 프랑"))
        items.add(VarietyModel(2, "까리냥"))
        items.add(VarietyModel(3, "까르메네르"))
        items.add(VarietyModel(4, "그르나쉬"))
        items.add(VarietyModel(5, "멘시아"))
        items.add(VarietyModel(6, "멜롯"))
        items.add(VarietyModel(7, "몬테풀치아노"))
        items.add(VarietyModel(8, "네그로 아마로"))
        items.add(VarietyModel(9, "론 / GSM 블렌드"))
        items.add(VarietyModel(10, "산지오베제"))
        items.add(VarietyModel(11, "발폴리첼라 블렌드"))
        items.add(VarietyModel(12, "진판델"))

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_medium_body_red, container, false)
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
        lvAdapter = StyleRedLVAdapter(items)
        binding.lvTypeStyleMediumBodyRed.adapter = lvAdapter

        binding.lvTypeStyleMediumBodyRed.setOnItemClickListener { _, view, _, id ->
            when (id.toInt()) {
                0 -> {
                    view.findNavController()
                        .navigate(R.id.action_mediumBodyRedFragment_to_barberaFragment, bundleOf("item1" to item1, "item2" to item2, "item3" to "바르베라"))
                }
                1 -> {
                    view.findNavController()
                        .navigate(R.id.action_mediumBodyRedFragment_to_cabernetFrancFragment, bundleOf("item1" to item1, "item2" to item2, "item3" to "까베르네 프랑"))
                }
                2 -> {
                    view.findNavController()
                        .navigate(R.id.action_mediumBodyRedFragment_to_carignanFragment, bundleOf("item1" to item1, "item2" to item2, "item3" to "까리냥"))
                }
                3 -> {
                    view.findNavController()
                        .navigate(R.id.action_mediumBodyRedFragment_to_carmenereFragment, bundleOf("item1" to item1, "item2" to item2, "item3" to "까르메네르"))
                }
                4 -> {
                    view.findNavController()
                        .navigate(R.id.action_mediumBodyRedFragment_to_grenacheFragment, bundleOf("item1" to item1, "item2" to item2, "item3" to "그르나쉬"))
                }
                5 -> {
                    view.findNavController()
                        .navigate(R.id.action_mediumBodyRedFragment_to_menciaFragment, bundleOf("item1" to item1, "item2" to item2, "item3" to "멘시아"))
                }
                6 -> {
                    view.findNavController()
                        .navigate(R.id.action_mediumBodyRedFragment_to_merlotFragment, bundleOf("item1" to item1, "item2" to item2, "item3" to "멜롯"))
                }
                7 -> {
                    view.findNavController()
                        .navigate(R.id.action_mediumBodyRedFragment_to_montepulcianoFragment, bundleOf("item1" to item1, "item2" to item2, "item3" to "몬테풀치아노"))
                }
                8 -> {
                    view.findNavController()
                        .navigate(R.id.action_mediumBodyRedFragment_to_negroamaroFragment, bundleOf("item1" to item1, "item2" to item2, "item3" to "네그로 아마로"))
                }
                9 -> {
                    view.findNavController()
                        .navigate(R.id.action_mediumBodyRedFragment_to_rhoneFragment, bundleOf("item1" to item1, "item2" to item2, "item3" to "론 / GSM 블렌드"))
                }
                10 -> {
                    view.findNavController()
                        .navigate(R.id.action_mediumBodyRedFragment_to_sangioveseFragment, bundleOf("item1" to item1, "item2" to item2, "item3" to "산지오베제"))
                }
                11 -> {
                    view.findNavController()
                        .navigate(R.id.action_mediumBodyRedFragment_to_valpolicellaFragment, bundleOf("item1" to item1, "item2" to item2, "item3" to "발폴리첼라 블렌드"))
                }
                12 -> {
                    view.findNavController()
                        .navigate(R.id.action_mediumBodyRedFragment_to_zinfandelFragment, bundleOf("item1" to item1, "item2" to item2, "item3" to "진판델"))
                }

            }
        }
    }

}