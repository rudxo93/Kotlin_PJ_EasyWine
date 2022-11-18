package com.duran.gyoung_tae_93.pj.easywine.ui.view.fragment.typefragment.area.northamerica

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
import com.duran.gyoung_tae_93.pj.easywine.databinding.FragmentNorthAmericaBinding
import com.duran.gyoung_tae_93.pj.easywine.databinding.FragmentOceaniaBinding
import com.duran.gyoung_tae_93.pj.easywine.ui.adapter.type.TypeAreaLVAdapter

class NorthAmericaFragment : Fragment() {

    private lateinit var binding: FragmentNorthAmericaBinding

    lateinit var lvAdapter: TypeAreaLVAdapter
    val items = mutableListOf<TypeModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        items.add(TypeModel(0, "미국"))
        items.add(TypeModel(1, "미국 : 캘리포니아"))
        items.add(TypeModel(2, "미국 : 북서부"))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_north_america, container, false)
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
        lvAdapter = TypeAreaLVAdapter(items)
        binding.lvTypeAreaNorthAmerica.adapter = lvAdapter

        binding.lvTypeAreaNorthAmerica.setOnItemClickListener { _, view, _, id ->
            when(id.toInt()) {
                0 -> {
                    view.findNavController().navigate(R.id.action_northAmericaFragment_to_USAFragment, bundleOf("item1" to item1, "item2" to item2, "item3" to "미국"))
                }
                1 -> {
                    view.findNavController().navigate(R.id.action_northAmericaFragment_to_USACaliforniaFragment, bundleOf("item1" to item1, "item2" to item2, "item3" to "미국 : 캘리포니아"))
                }
                2 -> {
                    view.findNavController().navigate(R.id.action_northAmericaFragment_to_USANorthwestFragment, bundleOf("item1" to item1, "item2" to item2, "item3" to "미국 : 북서부"))
                }
            }
        }

    }

}