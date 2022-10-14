package com.duran.gyoung_tae_93.pj.easywine.ui.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.duran.gyoung_tae_93.pj.easywine.R
import com.duran.gyoung_tae_93.pj.easywine.data.model.InfoItemModel
import com.duran.gyoung_tae_93.pj.easywine.databinding.FragmentInfoBinding
import com.duran.gyoung_tae_93.pj.easywine.ui.adapter.InfoRvAdapter

class InfoFragment : Fragment() {

    private lateinit var binding: FragmentInfoBinding

    private val infoRV by lazy { binding.rvInfo }

    private val titleList = arrayListOf<InfoItemModel>(
        InfoItemModel("와인 글라스 선택하기"),
        InfoItemModel("와인 오픈하는 법"),
        InfoItemModel("샴페인 오픈하는 법"),
        InfoItemModel("오프너가 없을때"),
        InfoItemModel("코르크 마개가 부러졌을때"),
        InfoItemModel("와인 빠르게 칠링하는 법"),
        InfoItemModel("와인 맛있게 마시는 방법"),
        InfoItemModel("와인 마시는 순서"),
        InfoItemModel("남은 와인 보관하기")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_info, container, false)

        initRecyclerViewSetting()

        return binding.root
    }

    private fun initRecyclerViewSetting() {
        val rvAdapter = InfoRvAdapter(titleList)
        infoRV.adapter = rvAdapter
        infoRV.layoutManager = LinearLayoutManager(context)
    }

}