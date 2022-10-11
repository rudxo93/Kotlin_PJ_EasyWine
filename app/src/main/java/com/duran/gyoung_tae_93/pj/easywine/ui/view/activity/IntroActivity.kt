package com.duran.gyoung_tae_93.pj.easywine.ui.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import com.duran.gyoung_tae_93.pj.easywine.R
import com.duran.gyoung_tae_93.pj.easywine.databinding.ActivityIntroBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider

class IntroActivity : AppCompatActivity() {

    private lateinit var binding : ActivityIntroBinding
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

    // ======================================= 구글 클라이언트 =======================================
    private fun initGoogleLoginClient() {
        // 구글 로그인 클라이언트 설정 및 생성
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id)) // 구글 API키 -> ID Token 가져온다.
            .requestEmail()
            .build()
        // 옵션값 구글로그인 클라이언트 세팅
        googleSignInClient = GoogleSignIn.getClient(this, gso)
    }

    // ======================================= 구글 로그인 =======================================
    // 구글 로그인 버튼 클릭
    private fun initGoogleLoginBtn() {
        btn_google.setOnClickListener {
            Log.d(TAG, "Google Login - Google Login Button Click -> Start Google Login")
            googleLogin()
        }
    }

    // 구글 로그인
    private fun googleLogin() {
        val intent = googleSignInClient.signInIntent
        googleLoginResult.launch(intent)
    }

    // 구글 로그인 결과
    private var googleLoginResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            val data = result.data // ActivityResult객체 result로 data를 받아온다.
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            val account = task.getResult(ApiException::class.java)
            firebaseAuthGoogle(account.idToken)
            /*initGoogleNicknameSelect(account.idToken)*/
        }

    // 구글 로그인 결과
    private fun firebaseAuthGoogle(idToken: String?) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential).addOnCompleteListener {
                task ->
            if (task.isSuccessful) {
                if (auth.currentUser!!.isEmailVerified) {
                    // 구글 로그인 인증되었을때
                    Log.d(TAG, "Google Login Success")
                    Toast.makeText(this, "구글 로그인 성공", Toast.LENGTH_SHORT).show()
                    moveMain(auth.currentUser)
                } else {
                    // 구글 로그인 인증 실패
                    Log.d(TAG, "Google Login Fail")
                    Toast.makeText(this, "구글 로그인에 실패했습니다.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun moveMain(user: FirebaseUser?) {
        if(user != null) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}