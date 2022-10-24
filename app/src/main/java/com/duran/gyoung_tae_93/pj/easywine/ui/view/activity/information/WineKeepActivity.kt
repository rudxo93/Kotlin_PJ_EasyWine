package com.duran.gyoung_tae_93.pj.easywine.ui.view.activity.information

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.duran.gyoung_tae_93.pj.easywine.R
import com.duran.gyoung_tae_93.pj.easywine.databinding.ActivityWineKeepBinding

class WineKeepActivity : AppCompatActivity() {

    private lateinit var binding : ActivityWineKeepBinding

    private val topToolbar by lazy { binding.wkToolbar }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_wine_keep)
        getToolbarSetting()
    }

    private fun getToolbarSetting() {
        topToolbar.setTitle("남은 와인 보관하기")
        supportActionBar?.setDisplayShowTitleEnabled(true)
    }
}