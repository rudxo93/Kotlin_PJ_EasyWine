package com.duran.gyoung_tae_93.pj.easywine.ui.view.fragment.typefragment.area.europe

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.duran.gyoung_tae_93.pj.easywine.R
import com.duran.gyoung_tae_93.pj.easywine.data.model.type.TypeModel
import com.duran.gyoung_tae_93.pj.easywine.databinding.FragmentEuropeBinding
import com.duran.gyoung_tae_93.pj.easywine.ui.adapter.type.area.TypeAreaLVAdapter

class EuropeFragment : Fragment() {

    private lateinit var binding: FragmentEuropeBinding

    lateinit var lvAdapter: TypeAreaLVAdapter
    val items = mutableListOf<TypeModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        items.add(TypeModel(0, "오스트리아"))
        items.add(TypeModel(1, "프랑스"))
        items.add(TypeModel(2, "프랑스 : 보르도"))
        items.add(TypeModel(3, "프랑스 : 부르고뉴"))
        items.add(TypeModel(4, "프랑스 : 론 밸리"))
        items.add(TypeModel(5, "독일"))
        items.add(TypeModel(6, "이탈리아"))
        items.add(TypeModel(7, "이탈리아 : 토스카나"))
        items.add(TypeModel(8, "포르투갈"))
        items.add(TypeModel(9, "스페인"))

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

        val item1 = arguments?.getString("item1")
        val item2 = arguments?.getString("item2")

        getListView(item1, item2)
    }

    private fun getListView(item1: String?, item2: String?) {
        lvAdapter = TypeAreaLVAdapter(items)
        binding.lvTypeAreaEurope.adapter = lvAdapter

        binding.lvTypeAreaEurope.setOnItemClickListener { _, view, _, id ->
            when(id.toInt()) {
                0 -> {
                    view.findNavController().navigate(R.id.action_europeFragment_to_austriaFragment, bundleOf("item1" to item1, "item2" to item2, "item3" to "오스트리아"))
                }
                1 -> {
                    view.findNavController().navigate(R.id.action_europeFragment_to_franceFragment, bundleOf("item1" to item1, "item2" to item2, "item3" to "프랑스"))
                }
                2 -> {
                    view.findNavController().navigate(R.id.action_europeFragment_to_franceBordeauxFragment, bundleOf("item1" to item1, "item2" to item2, "item3" to "프랑스 : 보르도"))
                }
                3 -> {
                    view.findNavController().navigate(R.id.action_europeFragment_to_franceBourgogneFragment, bundleOf("item1" to item1, "item2" to item2, "item3" to "프랑스 : 부르고뉴"))
                }
                4 -> {
                    view.findNavController().navigate(R.id.action_europeFragment_to_franceRhoneFragment, bundleOf("item1" to item1, "item2" to item2, "item3" to "프랑스 : 론 밸리"))
                }
                5 -> {
                    view.findNavController().navigate(R.id.action_europeFragment_to_germanyFragment, bundleOf("item1" to item1, "item2" to item2, "item3" to "독일"))
                }
                6 -> {
                    view.findNavController().navigate(R.id.action_europeFragment_to_italiaFragment, bundleOf("item1" to item1, "item2" to item2, "item3" to "이탈리아"))
                }
                7 -> {
                    view.findNavController().navigate(R.id.action_europeFragment_to_italiaToscanaFragment, bundleOf("item1" to item1, "item2" to item2, "item3" to "이탈리아 : 토스카나"))
                }
                8 -> {
                    view.findNavController().navigate(R.id.action_europeFragment_to_portugalFragment, bundleOf("item1" to item1, "item2" to item2, "item3" to "포르투갈"))
                }
                9 -> {
                    view.findNavController().navigate(R.id.action_europeFragment_to_spainFragment, bundleOf("item1" to item1, "item2" to item2, "item3" to "스페인"))
                }
            }
        }
    }

}