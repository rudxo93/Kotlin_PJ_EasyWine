package com.duran.gyoung_tae_93.pj.easywine.ui.view.fragment.typefragment.style.sparkling

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.duran.gyoung_tae_93.pj.easywine.R
import com.duran.gyoung_tae_93.pj.easywine.data.model.type.style.SparklingModel
import com.duran.gyoung_tae_93.pj.easywine.databinding.FragmentSparklingBinding
import com.duran.gyoung_tae_93.pj.easywine.ui.adapter.type.style.StyleSparklingLVAdapter

class SparklingFragment : Fragment() {

    private lateinit var binding: FragmentSparklingBinding

    lateinit var lvAdapter: StyleSparklingLVAdapter
    val items = mutableListOf<SparklingModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        items.add(SparklingModel(0, "까바"))
        items.add(SparklingModel(1, "샴페인"))
        items.add(SparklingModel(2, "람브루스코"))
        items.add(SparklingModel(3, "프로세코"))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sparkling, container, false)
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
        lvAdapter = StyleSparklingLVAdapter(items)
        binding.lvTypeStyleSparkling.adapter = lvAdapter

        binding.lvTypeStyleSparkling.setOnItemClickListener { _, view, _, id ->
            when (id.toInt()) {
                0 -> {
                    view.findNavController()
                        .navigate(R.id.action_sparklingFragment_to_cavaFragment, bundleOf("item1" to item1, "item2" to item2, "item3" to "까바"))
                }
                1 -> {
                    view.findNavController()
                        .navigate(R.id.action_sparklingFragment_to_champagneFragment, bundleOf("item1" to item1, "item2" to item2, "item3" to "샴페인"))
                }
                2 -> {
                    view.findNavController()
                        .navigate(R.id.action_sparklingFragment_to_lambruscoFragment, bundleOf("item1" to item1, "item2" to item2, "item3" to "람브루스코"))
                }
                3 -> {
                    view.findNavController()
                        .navigate(R.id.action_sparklingFragment_to_proseccoFragment, bundleOf("item1" to item1, "item2" to item2, "item3" to "프로세코"))
                }
            }
        }
    }
}