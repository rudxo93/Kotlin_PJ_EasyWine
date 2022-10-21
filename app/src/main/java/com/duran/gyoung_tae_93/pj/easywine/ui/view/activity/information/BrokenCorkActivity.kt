package com.duran.gyoung_tae_93.pj.easywine.ui.view.activity.information

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.duran.gyoung_tae_93.pj.easywine.R
import com.duran.gyoung_tae_93.pj.easywine.databinding.ActivityBrokenCorkBinding

class BrokenCorkActivity : AppCompatActivity() {

    private lateinit var binding : ActivityBrokenCorkBinding

    private val topToolbar by lazy { binding.bwToolbar }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_broken_cork)
        getToolbarSetting()
    }

    private fun getToolbarSetting() {
        topToolbar.setTitle("코르크 마개가 부러졌을 때")
        supportActionBar?.setDisplayShowTitleEnabled(true)
    }
}