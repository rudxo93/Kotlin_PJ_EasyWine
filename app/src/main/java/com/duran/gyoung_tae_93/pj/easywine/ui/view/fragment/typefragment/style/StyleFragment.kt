package com.duran.gyoung_tae_93.pj.easywine.ui.view.fragment.typefragment.style

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.duran.gyoung_tae_93.pj.easywine.R
import com.duran.gyoung_tae_93.pj.easywine.data.model.type.TypeStyleModel
import com.duran.gyoung_tae_93.pj.easywine.databinding.FragmentStyleBinding
import com.duran.gyoung_tae_93.pj.easywine.ui.adapter.type.TypeStyleLVAdapter
import com.duran.gyoung_tae_93.pj.easywine.ui.view.activity.MainActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class StyleFragment : Fragment() {

    private lateinit var binding: FragmentStyleBinding

    lateinit var lvAdapter: TypeStyleLVAdapter
    val items = mutableListOf<TypeStyleModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        items.add(TypeStyleModel(0, "스파클링 와인"))
        items.add(TypeStyleModel(1, "라이트 바디 화이트 와인"))
        items.add(TypeStyleModel(2, "풀 바디 화이트 와인"))
        items.add(TypeStyleModel(3, "아로마틱 화이트 와인"))
        items.add(TypeStyleModel(4, "로제 와인"))
        items.add(TypeStyleModel(5, "라이트 바디 레드 와인"))
        items.add(TypeStyleModel(6, "미디엄 바디 레드 와인"))
        items.add(TypeStyleModel(7, "풀 바디 레드 와인"))
        items.add(TypeStyleModel(8, "디저트 와인"))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_style, container, false)
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
        lvAdapter = TypeStyleLVAdapter(items)
        binding.lvTypeStyle.adapter = lvAdapter

        binding.lvTypeStyle.setOnItemClickListener { _, view, _, id ->
            when(id.toInt()) {
                0 -> {
                    view.findNavController().navigate(R.id.action_styleFragment_to_sparklingFragment, bundleOf("item1" to item1, "item2" to "스파클링 와인"))
                }
                1 -> {

                }
                2 -> {

                }
                3-> {

                }
                4 -> {

                }
                5 -> {

                }
                6 -> {

                }
                7 -> {

                }
                8 -> {

                }
            }
        }

    }

}