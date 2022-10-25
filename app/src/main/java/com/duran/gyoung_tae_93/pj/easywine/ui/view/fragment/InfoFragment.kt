package com.duran.gyoung_tae_93.pj.easywine.ui.view.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.duran.gyoung_tae_93.pj.easywine.R
import com.duran.gyoung_tae_93.pj.easywine.databinding.FragmentInfoBinding
import com.duran.gyoung_tae_93.pj.easywine.ui.view.activity.information.*

class InfoFragment : Fragment(), View.OnClickListener {

    private lateinit var binding: FragmentInfoBinding

    private val wineGlass by lazy { binding.linearTitle1 }
    private val wineOpen by lazy { binding.linearTitle2 }
    private val champagneOpen by lazy { binding.linearTitle3 }
    private val noOpener by lazy { binding.linearTitle4 }
    private val wineChilling by lazy { binding.linearTitle5 }
    private val wineDrink by lazy { binding.linearTitle6 }
    private val wineOrder by lazy { binding.linearTitle7 }
    private val wineKeep by lazy { binding.linearTitle8 }

    private val TAG = InfoFragment::class.java.simpleName

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.e(TAG, "Start onAttach Fragment")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e(TAG, "Start onCreate Fragment")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_info, container, false)
        Log.e(TAG, "Start onCreateView Fragment")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.e(TAG, "Start onViewCreated Fragment")

        wineGlass.setOnClickListener(this)
        wineOpen.setOnClickListener(this)
        champagneOpen.setOnClickListener(this)
        noOpener.setOnClickListener(this)
        wineChilling.setOnClickListener(this)
        wineDrink.setOnClickListener(this)
        wineOrder.setOnClickListener(this)
        wineKeep.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        if (v != null) {
            when (v.id) {
                R.id.linear_title_1 -> {
                    val intent = Intent(context, WineGlassActivity::class.java)
                    startActivity(intent)
                }
                R.id.linear_title_2 -> {
                    val intent = Intent(context, WineOpenActivity::class.java)
                    startActivity(intent)
                }
                R.id.linear_title_3 -> {
                    val intent = Intent(context, ChampagneOpenActivity::class.java)
                    startActivity(intent)
                }
                R.id.linear_title_4 -> {
                    val intent = Intent(context, NoOpenerActivity::class.java)
                    startActivity(intent)
                }
                R.id.linear_title_5 -> {
                    val intent = Intent(context, WineChillingActivity::class.java)
                    startActivity(intent)
                }
                R.id.linear_title_6 -> {
                    val intent = Intent(context, WineDrinkActivity::class.java)
                    startActivity(intent)
                }
                R.id.linear_title_7 -> {
                    val intent = Intent(context, WineOrderActivity::class.java)
                    startActivity(intent)
                }
                R.id.linear_title_8 -> {
                    val intent = Intent(context, WineKeepActivity::class.java)
                    startActivity(intent)
                }

            }
        }
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        Log.e(TAG, "Start onViewStateRestored Fragment")
    }

    override fun onStart() {
        super.onStart()
        Log.e(TAG, "Start onStart Fragment")
    }

    override fun onResume() {
        super.onResume()
        Log.e(TAG, "Start onResume Fragment")
    }

    override fun onPause() {
        super.onPause()
        Log.e(TAG, "Start onPause Fragment")
    }

}