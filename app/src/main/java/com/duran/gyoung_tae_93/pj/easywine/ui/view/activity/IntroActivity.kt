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
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class IntroActivity : AppCompatActivity() {

    private lateinit var binding: ActivityIntroBinding
    private val viewModel by lazy { AuthViewModel() }
    private lateinit var auth: FirebaseAuth // 계정인증
    private lateinit var googleSignInClient: GoogleSignInClient // 구글 로그인

    private val TAG = IntroActivity::class.java.simpleName

    private val btn_google by lazy { binding.googleLoginButton }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_intro)

        auth = FirebaseAuth.getInstance()

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
        btn_google.setOnClickListener {
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
                    moveNickname(auth.currentUser)

                } catch (e: ApiException) {
                    Toast.makeText(this, e.localizedMessage, Toast.LENGTH_SHORT).show()
                }
            }
        }

    /**
     *  Google Login Success -> Move Create Nickname Activity
     */
    private fun moveNickname(user: FirebaseUser?) {
        if (user != null) {
            startActivity(Intent(this, CreateNicknameActivity::class.java))
        }
    }
}