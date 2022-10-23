package com.duran.gyoung_tae_93.pj.easywine.ui.view.activity.information

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.duran.gyoung_tae_93.pj.easywine.R
import com.duran.gyoung_tae_93.pj.easywine.databinding.ActivityWineDrinkBinding

class WineDrinkActivity : AppCompatActivity() {

    private lateinit var binding : ActivityWineDrinkBinding

    private val topToolbar by lazy { binding.wdToolbar }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_wine_drink)
        getToolbarSetting()
    }

    private fun getToolbarSetting() {
        topToolbar.setTitle("와인 맛있게 마시는 방법")
        supportActionBar?.setDisplayShowTitleEnabled(true)
    }
}