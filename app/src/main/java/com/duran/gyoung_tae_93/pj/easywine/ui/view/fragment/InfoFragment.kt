package com.duran.gyoung_tae_93.pj.easywine.ui.view.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.duran.gyoung_tae_93.pj.easywine.R
import com.duran.gyoung_tae_93.pj.easywine.data.model.InfoTitleModel
import com.duran.gyoung_tae_93.pj.easywine.databinding.FragmentInfoBinding
import com.duran.gyoung_tae_93.pj.easywine.ui.adapter.InfoLVAdapter
import com.duran.gyoung_tae_93.pj.easywine.ui.view.activity.information.*

class InfoFragment : Fragment() {

    private lateinit var binding: FragmentInfoBinding

    private val TAG = InfoFragment::class.java.simpleName
    lateinit var lvAdapter: InfoLVAdapter

    val items = mutableListOf<InfoTitleModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "Create InfoFragment")
        items.add(InfoTitleModel(0, "와인 글라스 선택하기"))
        items.add(InfoTitleModel(1, "와인 오픈하는 법"))
        items.add(InfoTitleModel(2, "샴페인 오픈하는 법"))
        items.add(InfoTitleModel(3, "오프너가 없을 때"))
        items.add(InfoTitleModel(4, "와인 빠르게 칠링하는 법"))
        items.add(InfoTitleModel(5, "와인 즐기는 방법"))
        items.add(InfoTitleModel(6, "와인 마시는 순서"))
        items.add(InfoTitleModel(7, "남은 와인 보관하기"))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_info, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getListViewSetting()
    }

    /**
     *  Info 탭 ListView Adapter 연결, 이동 구현
     */
    private fun getListViewSetting() {
        lvAdapter = InfoLVAdapter(items)
        binding.lvInfo.adapter = lvAdapter

        binding.lvInfo.setOnItemClickListener { parent, view, position, id ->
            Log.e(TAG, id.toString())
            when (id.toInt()) {
                0 -> {
                    Log.d(TAG, "Move InfoFragment to WineGlassActivity")
                    val intent = Intent(context, WineGlassActivity::class.java)
                    startActivity(intent)
                }
                1 -> {
                    Log.d(TAG, "Move InfoFragment to WineOpenActivity")
                    val intent = Intent(context, WineOpenActivity::class.java)
                    startActivity(intent)
                }
                2 -> {
                    Log.d(TAG, "Move InfoFragment to ChampagneOpenActivity")
                    val intent = Intent(context, ChampagneOpenActivity::class.java)
                    startActivity(intent)
                }
                3 -> {
                    Log.d(TAG, "Move InfoFragment to NoOpenerActivity")
                    val intent = Intent(context, NoOpenerActivity::class.java)
                    startActivity(intent)
                }
                4 -> {
                    Log.d(TAG, "Move InfoFragment to WineChillingActivity")
                    val intent = Intent(context, WineChillingActivity::class.java)
                    startActivity(intent)
                }
                5 -> {
                    Log.d(TAG, "Move InfoFragment to WineDrinkActivity")
                    val intent = Intent(context, WineDrinkActivity::class.java)
                    startActivity(intent)
                }
                6 -> {
                    Log.d(TAG, "Move InfoFragment to WineOrderActivity")
                    val intent = Intent(context, WineOrderActivity::class.java)
                    startActivity(intent)
                }
                7 -> {
                    Log.d(TAG, "Move InfoFragment to WineKeepActivity")
                    val intent = Intent(context, WineKeepActivity::class.java)
                    startActivity(intent)
                }

            }
        }
    }

}