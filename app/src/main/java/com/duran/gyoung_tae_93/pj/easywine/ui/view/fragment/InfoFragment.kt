package com.duran.gyoung_tae_93.pj.easywine.ui.view.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.duran.gyoung_tae_93.pj.easywine.R
import com.duran.gyoung_tae_93.pj.easywine.databinding.FragmentInfoBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class InfoFragment : Fragment() {

    private lateinit var binding: FragmentInfoBinding

    private val moveWineGlass by lazy { binding.linearTitle1 }
    private val moveWineOpen by lazy { binding.linearTitle2 }
    private val moveChampagneOpen by lazy { binding.linearTitle3 }
    private val moveNoOpener by lazy { binding.linearTitle4 }
    private val moveBrokenCork by lazy { binding.linearTitle5 }
    private val moveWineChilling by lazy { binding.linearTitle6 }
    private val moveWineDrink by lazy { binding.linearTitle7 }
    private val moveWineOrder by lazy { binding.linearTitle8 }
    private val moveWineKeep by lazy { binding.linearTitle9 }

    private val TAG = InfoFragment::class.java.simpleName

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
        moveWineGlass.setOnClickListener {
            Log.d(TAG, "와인 글라스 선택하기 클릭 -> 와인 글라스 선택하기 Fragment로 이동합니다.")
            it.findNavController().navigate(R.id.action_fragment_info_to_wineGlassFragment)
        }

        /*initMoveWineGlass()*/
    }

    private fun initMoveWineGlass() {
        moveWineGlass.setOnClickListener {
            Log.d(TAG, "와인 글라스 선택하기 클릭 -> 와인 글라스 선택하기 Fragment로 이동합니다.")
            view?.findNavController()?.navigate(R.id.action_fragment_info_to_wineGlassFragment)
        }
    }

}