package com.duran.gyoung_tae_93.pj.easywine.ui.view.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.duran.gyoung_tae_93.pj.easywine.R
import com.duran.gyoung_tae_93.pj.easywine.util.FBAuth
import com.duran.gyoung_tae_93.pj.easywine.util.FBDocRef

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private val handler = Handler(Looper.getMainLooper())
    private var SPLASH_TIME: Long = 3000
    private var TAG = SplashActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        initGetUserNk()
    }

    /**
     * 현재 사용자의 uid를 사용해서 닉네임이 있는지 조회 -> 없다면 intro 이동 닉네임 생성 / 있다면 intro 생략 main 이동
     */
    private fun initGetUserNk() {
        val docRef = FBDocRef.fbDB.collection("user").document(FBAuth.getUid())
        docRef.get()
            .addOnSuccessListener { document ->
                if (document.data != null) { // data가 조회되었다면 mainActivity
                    Log.d(TAG, "DocumentSnapshot data: ${document.data}")
                    handler.postDelayed({
                        Log.d(TAG, "This Uid have a data. Go to MainActivity")
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }, SPLASH_TIME)
                } else { // data가 조회되지 않는다면 introActivity
                    Log.d(TAG, "No such document")
                    handler.postDelayed({
                        Log.d(TAG, "This Uid does not have a data. Go to IntroActivity")
                        val intent = Intent(this, IntroActivity::class.java)
                        startActivity(intent)
                        finish()
                    }, SPLASH_TIME)
                }
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "get failed with ", exception)
            }
    }
}