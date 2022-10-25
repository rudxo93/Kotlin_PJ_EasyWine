package com.duran.gyoung_tae_93.pj.easywine.ui.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.duran.gyoung_tae_93.pj.easywine.R
import com.duran.gyoung_tae_93.pj.easywine.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    /*private lateinit var navController: NavController*/

    private val bottomNav by lazy { binding.bottomNavigation }
    private val topToolbar by lazy { binding.topToolbar }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        bottomNav.itemIconTintList = null // custom한 icon이 나오도록

        setupJetpackNavigation()
        setSupportActionBar(topToolbar)
    }

    private fun setupJetpackNavigation() {
        val host = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = host.navController
        bottomNav.setupWithNavController(navController)


    }

    /**
     *  Toolbar 상단 Mypage 아이콘 클릭 시 MyPage Actiivty 이동 구현
     */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.activity_myPage -> {
                startActivity(Intent(this, MypageActivity::class.java))
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}