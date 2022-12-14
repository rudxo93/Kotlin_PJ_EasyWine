package com.duran.gyoung_tae_93.pj.easywine.ui.view.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.duran.gyoung_tae_93.pj.easywine.R
import com.duran.gyoung_tae_93.pj.easywine.databinding.ActivityIntroBinding
import com.duran.gyoung_tae_93.pj.easywine.ui.viewmodel.AuthViewModel
import com.duran.gyoung_tae_93.pj.easywine.util.FBAuth
import com.duran.gyoung_tae_93.pj.easywine.util.FBDocRef
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException

class IntroActivity : AppCompatActivity() {

    private lateinit var binding: ActivityIntroBinding
    private val viewModel by lazy { AuthViewModel() }
    private lateinit var googleSignInClient: GoogleSignInClient // 구글 로그인

    private val TAG = IntroActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_intro)

        initGoogleLoginClient()
        initGoogleLoginBtn()
    }

    /**
     *  Google Client
     */
    private fun initGoogleLoginClient() {
        // 구글 로그인 클라이언트 설정 및 생성
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id)) // 구글 API키 -> ID Token 가져온다.
            .requestEmail()
            .build()
        // 옵션값 구글로그인 클라이언트 세팅
        googleSignInClient = GoogleSignIn.getClient(this, gso)
    }

    /**
     *  Google Login Button Click
     */
    private fun initGoogleLoginBtn() {
        binding.googleLoginButton.setOnClickListener {
            Log.d(TAG, "Google Login - Google Login Button Click -> Start Google Login")
            googleLogin()
        }
    }

    /**
     *  Google Login
     */
    private fun googleLogin() {
        val intent = googleSignInClient.signInIntent
        googleLoginResult.launch(intent)
    }

    /**
     *  Google Login Result
     */
    private var googleLoginResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                try {
                    val account = task.getResult(ApiException::class.java)!!
                    viewModel.getUser(account.idToken!!)
                    Toast.makeText(this, "로그인 성공", Toast.LENGTH_SHORT).show()
                    nicknameCheck()

                } catch (e: ApiException) {
                    Toast.makeText(this, e.localizedMessage, Toast.LENGTH_SHORT).show()
                }
            }
        }

    private fun nicknameCheck() {

        FBDocRef.fbDB.collection("user").whereEqualTo("email", FBAuth.getEmail())
            .get().addOnSuccessListener { task ->

                if (task.isEmpty) {
                    Log.e("dddddd", "닉네임 없음")
                    moveNickname(FBAuth.getCurrentUser())
                } else {
                    Log.e("dddddd", "닉네임 있음")
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }

            }

    }

    /**
     *  Google Login Success -> Move Create Nickname Activity
     */
    private fun moveNickname(user: String) {

        if (user.isNotEmpty()) {
            val intent = Intent(this, CreateNicknameActivity::class.java)
            startActivity(intent)
        }
    }
}