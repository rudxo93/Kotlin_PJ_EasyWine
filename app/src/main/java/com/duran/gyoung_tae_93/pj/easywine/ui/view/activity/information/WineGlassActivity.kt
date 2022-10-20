package com.duran.gyoung_tae_93.pj.easywine.ui.view.activity.information

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.duran.gyoung_tae_93.pj.easywine.R
import com.duran.gyoung_tae_93.pj.easywine.databinding.ActivityWineGlassBinding

class WineGlassActivity : AppCompatActivity() {

    private lateinit var binding : ActivityWineGlassBinding

    private val topToolbar by lazy { binding.wgToolbar }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_wine_glass)

        getToolbarSetting()
    }

    private fun getToolbarSetting() {
        topToolbar.setTitle("와인 글라스 선택하기")
        supportActionBar?.setDisplayShowTitleEnabled(true)
    }
}