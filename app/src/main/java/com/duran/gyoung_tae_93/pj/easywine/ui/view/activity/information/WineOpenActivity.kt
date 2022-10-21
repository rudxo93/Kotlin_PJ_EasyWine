package com.duran.gyoung_tae_93.pj.easywine.ui.view.activity.information

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.duran.gyoung_tae_93.pj.easywine.R
import com.duran.gyoung_tae_93.pj.easywine.databinding.ActivityWineOpenBinding

class WineOpenActivity : AppCompatActivity() {

    private lateinit var binding : ActivityWineOpenBinding

    private val topToolbar by lazy { binding.woToolbar }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_wine_open)
        getToolbarSetting()
    }

    private fun getToolbarSetting() {
        topToolbar.setTitle("와인 오픈하는 법")
        supportActionBar?.setDisplayShowTitleEnabled(true)
    }
}