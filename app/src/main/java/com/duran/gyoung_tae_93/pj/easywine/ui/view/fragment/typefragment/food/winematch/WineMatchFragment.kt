package com.duran.gyoung_tae_93.pj.easywine.ui.view.fragment.typefragment.food.winematch

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.duran.gyoung_tae_93.pj.easywine.R
import com.duran.gyoung_tae_93.pj.easywine.databinding.FragmentWineMatchBinding
import com.duran.gyoung_tae_93.pj.easywine.ui.adapter.type.food.TypeFoodRVAdapter
import com.duran.gyoung_tae_93.pj.easywine.ui.adapter.type.food.TypeWineRVAdapter

class WineMatchFragment : Fragment() {

    private lateinit var binding: FragmentWineMatchBinding

    lateinit var rvAdapter: TypeWineRVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_wine_match, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val list = arrayListOf("스파클링", "상큼한 화이트", "향이 풍부한 화이트", "강한 화이트", "로제", "가벼운 레드", "부드러운 레드", "강한 레드", "무알뢰, 리코뢰", "뱅 두 나튀렐")

        setRVSetting(list)

    }

    private fun setRVSetting(list: ArrayList<String>) {
        val rv = binding.rvMatchFood

        rvAdapter = TypeWineRVAdapter(list)
        rv.adapter = rvAdapter
        rv.layoutManager = GridLayoutManager(context, 2)


        rvAdapter.setItemClickListener(object : TypeWineRVAdapter.ItemClickListener {
            override fun onClick(view: View, position: Int) {
                when(position) {
                    0 -> {
                        view.findNavController().navigate(R.id.action_wineMatchFragment_to_matchSparklingFragment)
                    }
                    1 -> {
                        view.findNavController().navigate(R.id.action_wineMatchFragment_to_matchLightWhiteFragment)
                    }
                    2 -> {
                        view.findNavController().navigate(R.id.action_wineMatchFragment_to_matchMediumWhiteFragment)
                    }
                    3 -> {
                        view.findNavController().navigate(R.id.action_wineMatchFragment_to_matchFullWhiteFragment)
                    }
                    4 -> {
                        view.findNavController().navigate(R.id.action_wineMatchFragment_to_matchRoseFragment)
                    }
                    5 -> {
                        view.findNavController().navigate(R.id.action_wineMatchFragment_to_matchLightRedFragment)
                    }
                    6 -> {
                        view.findNavController().navigate(R.id.action_wineMatchFragment_to_matchMediumRedFragment)
                    }
                    7 -> {
                        view.findNavController().navigate(R.id.action_wineMatchFragment_to_matchFullRedFragment)
                    }
                    8 -> {
                        view.findNavController().navigate(R.id.action_wineMatchFragment_to_matchMoelleuxFragment)
                    }
                    9 -> {
                        view.findNavController().navigate(R.id.action_wineMatchFragment_to_matchVDNFragment)
                    }
                }
            }

        })

    }

}