package com.duran.gyoung_tae_93.pj.easywine.ui.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.duran.gyoung_tae_93.pj.easywine.R
import com.duran.gyoung_tae_93.pj.easywine.databinding.FragmentInfoBinding
import com.duran.gyoung_tae_93.pj.easywine.ui.adapter.info.InfoRVAdapter
import com.duran.gyoung_tae_93.pj.easywine.ui.view.activity.MainActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class InfoFragment : Fragment() {

    private lateinit var binding: FragmentInfoBinding

    lateinit var rvAdapter: InfoRVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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

        val list = arrayListOf("테이스팅 용어", "와인 용어")

        setRVSetting(list)

    }

    private fun setRVSetting(list: ArrayList<String>) {
        val rv = binding.rvWineInfo

        rvAdapter = InfoRVAdapter(list)
        rv.adapter = rvAdapter
        rv.layoutManager = GridLayoutManager(context, 2)

        rvAdapter.setItemClickListener(object : InfoRVAdapter.ItemClickListener {
            override fun onClick(view: View, position: Int) {
                when(position) {
                    0 -> {
                        view.findNavController().navigate(R.id.action_fragment_info_to_tastingTermsFragment)
                    }
                    1 -> {
                        view.findNavController().navigate(R.id.action_fragment_info_to_wineTermsFragment)
                    }
                }
            }

        })
    }

    override fun onResume() {
        super.onResume()
        (requireActivity() as MainActivity).supportActionBar!!.show()

        val navBar = activity?.findViewById<BottomNavigationView>(R.id.bottom_navigation)
        navBar?.visibility = View.VISIBLE
    }
}