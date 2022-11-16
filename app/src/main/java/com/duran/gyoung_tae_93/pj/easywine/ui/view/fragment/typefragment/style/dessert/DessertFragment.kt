package com.duran.gyoung_tae_93.pj.easywine.ui.view.fragment.typefragment.style.dessert

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
import com.duran.gyoung_tae_93.pj.easywine.databinding.FragmentDessertBinding
import com.duran.gyoung_tae_93.pj.easywine.databinding.FragmentFullBodyRedBinding
import com.duran.gyoung_tae_93.pj.easywine.ui.adapter.type.style.StyleRedLVAdapter

class DessertFragment : Fragment() {

    private lateinit var binding: FragmentDessertBinding

    lateinit var lvAdapter: StyleRedLVAdapter
    val items = mutableListOf<VarietyModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        items.add(VarietyModel(0, "마데이라"))
        items.add(VarietyModel(1, "마르살라"))
        items.add(VarietyModel(2, "포트"))
        items.add(VarietyModel(3, "소테른"))
        items.add(VarietyModel(4, "쉐리"))
        items.add(VarietyModel(5, "빈 산토"))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_dessert, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val item1 = arguments?.getString("item1")
        val item2 = arguments?.getString("item2")

        getListView(item1, item2)
    }

    private fun getListView(item1: String?, item2: String?) {
        lvAdapter = StyleRedLVAdapter(items)
        binding.lvTypeStyleDessert.adapter = lvAdapter

        binding.lvTypeStyleDessert.setOnItemClickListener { _, view, _, id ->
            when (id.toInt()) {
                0 -> {
                    view.findNavController()
                        .navigate(R.id.action_dessertFragment_to_madeiraFragment, bundleOf("item1" to item1, "item2" to item2, "item3" to "마데이라"))
                }
                1 -> {
                    view.findNavController()
                        .navigate(R.id.action_dessertFragment_to_marsalaFragment, bundleOf("item1" to item1, "item2" to item2, "item3" to "마르살라"))
                }
                2 -> {
                    view.findNavController()
                        .navigate(R.id.action_dessertFragment_to_portFragment, bundleOf("item1" to item1, "item2" to item2, "item3" to "포트"))
                }
                3 -> {
                    view.findNavController()
                        .navigate(R.id.action_dessertFragment_to_sauterneFragment, bundleOf("item1" to item1, "item2" to item2, "item3" to "소테른"))
                }
                4 -> {
                    view.findNavController()
                        .navigate(R.id.action_dessertFragment_to_sherryFragment, bundleOf("item1" to item1, "item2" to item2, "item3" to "쉐리"))
                }
                5 -> {
                    view.findNavController()
                        .navigate(R.id.action_dessertFragment_to_vinsantoFragment, bundleOf("item1" to item1, "item2" to item2, "item3" to "빈 산토"))
                }
            }
        }
    }

}