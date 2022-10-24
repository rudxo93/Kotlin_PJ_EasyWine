package com.duran.gyoung_tae_93.pj.easywine.ui.view.activity.information

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.duran.gyoung_tae_93.pj.easywine.R
import com.duran.gyoung_tae_93.pj.easywine.databinding.ActivityWineOrderBinding

class WineOrderActivity : AppCompatActivity() {

    private lateinit var binding : ActivityWineOrderBinding

    private val topToolbar by lazy { binding.woToolbar }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_wine_order)
        getToolbarSetting()
    }

    private fun getToolbarSetting() {
        topToolbar.setTitle("와인 마시는 순서")
        supportActionBar?.setDisplayShowTitleEnabled(true)
    }
}