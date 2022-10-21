package com.duran.gyoung_tae_93.pj.easywine.ui.view.activity.information

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.duran.gyoung_tae_93.pj.easywine.R
import com.duran.gyoung_tae_93.pj.easywine.databinding.ActivityChampagneOpenBinding

class ChampagneOpenActivity : AppCompatActivity() {

    private lateinit var binding : ActivityChampagneOpenBinding

    private val topToolbar by lazy { binding.coToolbar }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_champagne_open)
        getToolbarSetting()
    }

    private fun getToolbarSetting() {
        topToolbar.setTitle("샴페인 오픈하는 법")
        supportActionBar?.setDisplayShowTitleEnabled(true)
    }
}