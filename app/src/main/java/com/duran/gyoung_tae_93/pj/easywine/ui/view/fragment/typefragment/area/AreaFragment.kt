package com.duran.gyoung_tae_93.pj.easywine.ui.view.fragment.typefragment.area

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
import com.duran.gyoung_tae_93.pj.easywine.databinding.FragmentAreaBinding
import com.duran.gyoung_tae_93.pj.easywine.ui.adapter.type.area.TypeAreaLVAdapter
import com.duran.gyoung_tae_93.pj.easywine.ui.view.activity.MainActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class AreaFragment : Fragment() {

    private lateinit var binding: FragmentAreaBinding

    lateinit var lvAdapter: TypeAreaLVAdapter
    val items = mutableListOf<TypeModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        items.add(TypeModel(0, "유럽"))
        items.add(TypeModel(1, "오세아니아"))
        items.add(TypeModel(2, "북아메리카"))
        items.add(TypeModel(3, "남아메리카"))
        items.add(TypeModel(4, "아프리카"))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_area, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val item1 = arguments?.getString("item1")

        setHideBar()
        getListView(item1)
    }

    /**
     *  Navigation 이동 시 Top Toolbar, BottomNavigationView 숨기기
     */
    private fun setHideBar() {
        (requireActivity() as MainActivity).supportActionBar!!.hide()

        val navBar = activity?.findViewById<BottomNavigationView>(R.id.bottom_navigation)
        navBar?.visibility = View.GONE
    }

    /**
     *  ListView setting, Navigation 이동 구현
     */
    private fun getListView(item1: String?) {
        lvAdapter = TypeAreaLVAdapter(items)
        binding.lvTypeArea.adapter = lvAdapter

        binding.lvTypeArea.setOnItemClickListener { _, view, _, id ->
            when(id.toInt()) {
                0 -> {
                    view.findNavController().navigate(R.id.action_areaFragment_to_europeFragment, bundleOf("item1" to item1, "item2" to "유럽"))
                }
                1 -> {
                    view.findNavController().navigate(R.id.action_areaFragment_to_oceaniaFragment, bundleOf("item1" to item1, "item2" to "오세아니아"))
                }
                2 -> {
                    view.findNavController().navigate(R.id.action_areaFragment_to_northAmericaFragment, bundleOf("item1" to item1, "item2" to "북아메리카"))
                }
                3-> {
                    view.findNavController().navigate(R.id.action_areaFragment_to_southAmericaFragment, bundleOf("item1" to item1, "item2" to "남아메리카"))
                }
                4 -> {
                    view.findNavController().navigate(R.id.action_areaFragment_to_africaFragment, bundleOf("item1" to item1, "item2" to "아프리카"))
                }
            }
        }

    }

}