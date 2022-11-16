package com.duran.gyoung_tae_93.pj.easywine.ui.view.fragment.typefragment.style.lbodyred

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.duran.gyoung_tae_93.pj.easywine.R
import com.duran.gyoung_tae_93.pj.easywine.data.model.type.style.VarietyModel
import com.duran.gyoung_tae_93.pj.easywine.databinding.FragmentLightBodyRedBinding
import com.duran.gyoung_tae_93.pj.easywine.ui.adapter.type.style.StyleRedLVAdapter

class LightBodyRedFragment : Fragment() {

    private lateinit var binding: FragmentLightBodyRedBinding

    lateinit var lvAdapter: StyleRedLVAdapter
    val items = mutableListOf<VarietyModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        items.add(VarietyModel(0, "가메"))
        items.add(VarietyModel(1, "피노 누아"))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_light_body_red, container, false)
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
        binding.lvTypeStyleLightBodyRed.adapter = lvAdapter

        binding.lvTypeStyleLightBodyRed.setOnItemClickListener { _, view, _, id ->
            when (id.toInt()) {
                0 -> {
                    view.findNavController()
                        .navigate(
                            R.id.action_lightBodyRedFragment_to_gamayFragment,
                            bundleOf("item1" to item1, "item2" to item2, "item3" to "가메")
                        )
                }
                1 -> {
                    view.findNavController()
                        .navigate(
                            R.id.action_lightBodyRedFragment_to_pinotNoirFragment,
                            bundleOf("item1" to item1, "item2" to item2, "item3" to "피노 누아")
                        )
                }
            }
        }
    }

}