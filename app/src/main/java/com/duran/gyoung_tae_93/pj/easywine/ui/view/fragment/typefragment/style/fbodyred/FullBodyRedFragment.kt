package com.duran.gyoung_tae_93.pj.easywine.ui.view.fragment.typefragment.style.fbodyred

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
import com.duran.gyoung_tae_93.pj.easywine.databinding.FragmentFullBodyRedBinding
import com.duran.gyoung_tae_93.pj.easywine.databinding.FragmentMediumBodyRedBinding
import com.duran.gyoung_tae_93.pj.easywine.ui.adapter.type.style.StyleRedLVAdapter

class FullBodyRedFragment : Fragment() {

    private lateinit var binding: FragmentFullBodyRedBinding

    lateinit var lvAdapter: StyleRedLVAdapter
    val items = mutableListOf<VarietyModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        items.add(VarietyModel(0, "알리아니코"))
        items.add(VarietyModel(1, "보르도 블렌드"))
        items.add(VarietyModel(2, "까베르네 쇼비뇽"))
        items.add(VarietyModel(3, "말벡"))
        items.add(VarietyModel(4, "무르베드르"))
        items.add(VarietyModel(5, "네비올라"))
        items.add(VarietyModel(6, "네로 다볼라"))
        items.add(VarietyModel(7, "쁘띠 베르도"))
        items.add(VarietyModel(8, "쁘띠 쉬라"))
        items.add(VarietyModel(9, "피노타지"))
        items.add(VarietyModel(10, "쉬라"))
        items.add(VarietyModel(11, "템프라니오"))
        items.add(VarietyModel(12, "토우리가 나시오날"))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_full_body_red, container, false)
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
        binding.lvTypeStyleFullBodyRed.adapter = lvAdapter

        binding.lvTypeStyleFullBodyRed.setOnItemClickListener { _, view, _, id ->
            when (id.toInt()) {
                0 -> {
                    view.findNavController()
                        .navigate(R.id.action_fullBodyRedFragment_to_aglianicoFragment, bundleOf("item1" to item1, "item2" to item2, "item3" to "알리아니코"))
                }
                1 -> {
                    view.findNavController()
                        .navigate(R.id.action_fullBodyRedFragment_to_bordeauxFragment, bundleOf("item1" to item1, "item2" to item2, "item3" to "보르도 블렌드"))
                }
                2 -> {
                    view.findNavController()
                        .navigate(R.id.action_fullBodyRedFragment_to_cabernetSauvignonFragment, bundleOf("item1" to item1, "item2" to item2, "item3" to "까베르네 쇼비뇽"))
                }
                3 -> {
                    view.findNavController()
                        .navigate(R.id.action_fullBodyRedFragment_to_malbecFragment, bundleOf("item1" to item1, "item2" to item2, "item3" to "말벡"))
                }
                4 -> {
                    view.findNavController()
                        .navigate(R.id.action_fullBodyRedFragment_to_mourvedreFragment, bundleOf("item1" to item1, "item2" to item2, "item3" to "무르베드르"))
                }
                5 -> {
                    view.findNavController()
                        .navigate(R.id.action_fullBodyRedFragment_to_nebbioloFragment, bundleOf("item1" to item1, "item2" to item2, "item3" to "네비올라"))
                }
                6 -> {
                    view.findNavController()
                        .navigate(R.id.action_fullBodyRedFragment_to_nerodAvolaFragment, bundleOf("item1" to item1, "item2" to item2, "item3" to "네로 다볼라"))
                }
                7 -> {
                    view.findNavController()
                        .navigate(R.id.action_fullBodyRedFragment_to_petitVerdotFragment, bundleOf("item1" to item1, "item2" to item2, "item3" to "쁘띠 베르도"))
                }
                8 -> {
                    view.findNavController()
                        .navigate(R.id.action_fullBodyRedFragment_to_petiteSirahFragment, bundleOf("item1" to item1, "item2" to item2, "item3" to "쁘띠 쉬라"))
                }
                9 -> {
                    view.findNavController()
                        .navigate(R.id.action_fullBodyRedFragment_to_pinotageFragment, bundleOf("item1" to item1, "item2" to item2, "item3" to "피노타지"))
                }
                10 -> {
                    view.findNavController()
                        .navigate(R.id.action_fullBodyRedFragment_to_syrahFragment, bundleOf("item1" to item1, "item2" to item2, "item3" to "쉬라"))
                }
                11 -> {
                    view.findNavController()
                        .navigate(R.id.action_fullBodyRedFragment_to_tempranilloFragment, bundleOf("item1" to item1, "item2" to item2, "item3" to "템프라니오"))
                }
                12 -> {
                    view.findNavController()
                        .navigate(R.id.action_fullBodyRedFragment_to_tourigaNacionalFragment, bundleOf("item1" to item1, "item2" to item2, "item3" to "토우리가 나시오날"))
                }

            }
        }
    }

}