package com.duran.gyoung_tae_93.pj.easywine.ui.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.duran.gyoung_tae_93.pj.easywine.R
import com.duran.gyoung_tae_93.pj.easywine.data.model.info.InfoTitleModel
import com.duran.gyoung_tae_93.pj.easywine.databinding.FragmentReadyBinding
import com.duran.gyoung_tae_93.pj.easywine.ui.adapter.ready.ReadyLVAdapter
import com.duran.gyoung_tae_93.pj.easywine.ui.view.activity.MainActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class ReadyFragment : Fragment() {

    private lateinit var binding: FragmentReadyBinding

    private val TAG = ReadyFragment::class.java.simpleName
    lateinit var lvAdapter: ReadyLVAdapter

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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_ready, container, false)

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
        lvAdapter = ReadyLVAdapter(items)
        binding.lvInfo.adapter = lvAdapter

        binding.lvInfo.setOnItemClickListener { _, _, _, id ->
            Log.e(TAG, id.toString())
            when (id.toInt()) {
                0 -> {
                    view?.findNavController()
                        ?.navigate(R.id.action_fragment_ready_to_wineGlassFragment)
                }
                1 -> {
                    view?.findNavController()
                        ?.navigate(R.id.action_fragment_ready_to_wineOpenFragment)
                }
                2 -> {
                    view?.findNavController()
                        ?.navigate(R.id.action_fragment_ready_to_champagneOpenFragment)
                }
                3 -> {
                    view?.findNavController()
                        ?.navigate(R.id.action_fragment_ready_to_noOpenerFragment)
                }
                4 -> {
                    view?.findNavController()
                        ?.navigate(R.id.action_fragment_ready_to_wineChillingFragment)
                }
                5 -> {
                    view?.findNavController()
                        ?.navigate(R.id.action_fragment_ready_to_wineDrinkFragment)
                }
                6 -> {
                    view?.findNavController()
                        ?.navigate(R.id.action_fragment_ready_to_wineOrderFragment)
                }
                7 -> {
                    view?.findNavController()
                        ?.navigate(R.id.action_fragment_ready_to_wineKeepFragment)
                }

            }
        }
    }

    override fun onResume() {
        super.onResume()
        (requireActivity() as MainActivity).supportActionBar!!.show()

        val navBar = activity?.findViewById<BottomNavigationView>(R.id.bottom_navigation)
        navBar?.visibility = View.VISIBLE
    }

}