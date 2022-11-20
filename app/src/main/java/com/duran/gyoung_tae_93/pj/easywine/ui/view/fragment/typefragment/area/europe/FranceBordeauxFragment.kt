package com.duran.gyoung_tae_93.pj.easywine.ui.view.fragment.typefragment.area.europe

import android.animation.ObjectAnimator
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.duran.gyoung_tae_93.pj.easywine.R
import com.duran.gyoung_tae_93.pj.easywine.databinding.FragmentArgentinaBinding
import com.duran.gyoung_tae_93.pj.easywine.databinding.FragmentFranceBordeauxBinding

class FranceBordeauxFragment : Fragment() {

    private lateinit var binding: FragmentFranceBordeauxBinding

    private var isWineArrow = false // Fab 버튼 default는 닫힘

    var topType: String? = null
    var item1: String? = null
    var item2: String? = null
    var item3: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_france_bordeaux, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fg = this

        item1 = arguments?.getString("item1")
        item2 = arguments?.getString("item2")
        item3 = arguments?.getString("item3")

        setTopType()
        setWineProduceVisible()
        setFabButton()

    }

    /**
     *  상단 Type text
     */
    private fun setTopType() {
        topType = "$item1  >  $item2  >  $item3"
    }

    /**
     *  지도의 화살표 모양 클릭 시 생산품종 보여주기
     */
    private fun setWineProduceVisible() {
        binding.ivWineArrow.setOnClickListener {
            if (isWineArrow) {
                ObjectAnimator.ofFloat(binding.ivWineArrow, "rotationY", 180f, 0f).apply { start() }
                binding.linearWineProduceContent.visibility = View.GONE
            } else {
                ObjectAnimator.ofFloat(binding.ivWineArrow, "rotationY", 0f, 180f).apply { start() }
                binding.linearWineProduceContent.visibility = View.VISIBLE
            }

            isWineArrow = !isWineArrow
        }
    }

    /**
     *  FloatingActionButton Setting
     */
    private fun setFabButton() {
        binding.fabHome.setOnClickListener {
            it.findNavController().navigate(R.id.action_franceBordeauxFragment_to_fragment_type)
        }
    }

}