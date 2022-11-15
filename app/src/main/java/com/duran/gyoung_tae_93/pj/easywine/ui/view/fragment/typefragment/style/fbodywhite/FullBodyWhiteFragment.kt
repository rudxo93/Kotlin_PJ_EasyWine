package com.duran.gyoung_tae_93.pj.easywine.ui.view.fragment.typefragment.style.fbodywhite

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
import com.duran.gyoung_tae_93.pj.easywine.databinding.FragmentFullBodyWhiteBinding
import com.duran.gyoung_tae_93.pj.easywine.databinding.FragmentLightBodyWhiteBinding
import com.duran.gyoung_tae_93.pj.easywine.ui.adapter.type.style.StyleWhiteLVAdapter

class FullBodyWhiteFragment : Fragment() {

    private lateinit var binding: FragmentFullBodyWhiteBinding

    lateinit var lvAdapter: StyleWhiteLVAdapter
    val items = mutableListOf<VarietyModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        items.add(VarietyModel(0, "샤르도네"))
        items.add(VarietyModel(1, "마르산 블렌드"))
        items.add(VarietyModel(2, "세미용"))
        items.add(VarietyModel(3, "비오니에"))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_full_body_white, container, false)
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
        binding.lvTypeStyleFullBodyWhite.adapter = lvAdapter

        binding.lvTypeStyleFullBodyWhite.setOnItemClickListener { _, view, _, id ->
            when (id.toInt()) {
                0 -> {
                    view.findNavController()
                        .navigate(R.id.action_fullBodyWhiteFragment_to_chardonnayFragment, bundleOf("item1" to item1, "item2" to item2, "item3" to "샤르도네"))
                }
                1 -> {
                    view.findNavController()
                        .navigate(R.id.action_fullBodyWhiteFragment_to_marsanneFragment, bundleOf("item1" to item1, "item2" to item2, "item3" to "마르산 블렌드"))
                }
                2 -> {
                    view.findNavController()
                        .navigate(R.id.action_fullBodyWhiteFragment_to_semillonFragment, bundleOf("item1" to item1, "item2" to item2, "item3" to "세미용"))
                }
                3 -> {
                    view.findNavController()
                        .navigate(R.id.action_fullBodyWhiteFragment_to_viognierFragment, bundleOf("item1" to item1, "item2" to item2, "item3" to "비오니에"))
                }
            }
        }
    }

}