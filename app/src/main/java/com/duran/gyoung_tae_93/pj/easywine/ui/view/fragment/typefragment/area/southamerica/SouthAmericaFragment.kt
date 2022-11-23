package com.duran.gyoung_tae_93.pj.easywine.ui.view.fragment.typefragment.area.southamerica

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
import com.duran.gyoung_tae_93.pj.easywine.databinding.FragmentSouthAmericaBinding
import com.duran.gyoung_tae_93.pj.easywine.ui.adapter.type.area.TypeAreaLVAdapter

class SouthAmericaFragment : Fragment() {

    private lateinit var binding: FragmentSouthAmericaBinding

    lateinit var lvAdapter: TypeAreaLVAdapter
    val items = mutableListOf<TypeModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        items.add(TypeModel(0, "칠레"))
        items.add(TypeModel(1, "아르헨티나"))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_south_america, container, false)
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
        binding.lvTypeAreaSouthAmerica.adapter = lvAdapter

        binding.lvTypeAreaSouthAmerica.setOnItemClickListener { _, view, _, id ->
            when(id.toInt()) {
                0 -> {
                    view.findNavController().navigate(R.id.action_southAmericaFragment_to_chileFragment, bundleOf("item1" to item1, "item2" to item2, "item3" to "칠레"))
                }
                1 -> {
                    view.findNavController().navigate(R.id.action_southAmericaFragment_to_argentinaFragment, bundleOf("item1" to item1, "item2" to item2, "item3" to "아르헨티나"))
                }
            }
        }
    }

}