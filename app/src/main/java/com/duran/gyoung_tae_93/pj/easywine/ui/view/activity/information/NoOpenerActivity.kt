package com.duran.gyoung_tae_93.pj.easywine.ui.view.activity.information

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.duran.gyoung_tae_93.pj.easywine.R
import com.duran.gyoung_tae_93.pj.easywine.databinding.ActivityNoOpenerBinding

class NoOpenerActivity : AppCompatActivity() {

    private lateinit var binding : ActivityNoOpenerBinding

    private val topToolbar by lazy { binding.noToolbar }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_no_opener)
        getToolbarSetting()
    }

    private fun getToolbarSetting() {
        topToolbar.setTitle("오프너가 없을 때")
        supportActionBar?.setDisplayShowTitleEnabled(true)
    }
}