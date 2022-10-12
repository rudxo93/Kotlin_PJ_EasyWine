package com.duran.gyoung_tae_93.pj.easywine.ui.view.activity

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.duran.gyoung_tae_93.pj.easywine.R
import com.duran.gyoung_tae_93.pj.easywine.data.model.UserModel
import com.duran.gyoung_tae_93.pj.easywine.databinding.ActivityCreateNicknameBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class CreateNicknameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreateNicknameBinding
    private lateinit var firestore: FirebaseFirestore
    private lateinit var auth: FirebaseAuth

    private val TAG = CreateNicknameActivity::class.java.simpleName

    private val etCreateNk by lazy { binding.edCreateNickname }
    private val btnMoveMain by lazy { binding.btnMoveMain }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_create_nickname)

        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

        initCreateNk() // EditText변경 이벤트
        initBtnMoveMain() // MoveMain버튼 클릭시(입력한 닉네임 Database 저장)

    }

    /**
     *  Edittext 의 텍스트 변경 이벤트
     */
    private fun initCreateNk() {
        etCreateNk.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                // 입력이 끝날때
                etEmptyCk(s)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // 입력 하기 전
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // 타이핑 되는 텍스트에 변화
            }
        })
    }

    /**
     * Edittext 의 Text 여부에 따른 Move Button on/off
     */
    private fun etEmptyCk(s: Editable?) {
        if (s.isNullOrEmpty()) {
            Log.d(TAG, "EditText is Null Or Empty! Move Button Gone")
            btnMoveMain.visibility = View.GONE
        } else {
            Log.d(TAG, "Move Button Visible")
            btnMoveMain.visibility = View.VISIBLE
        }
    }

    /**
     * MoveMain 버튼 클릭 시 닉네임 Database 생성 -> Main이동
     */
    private fun initBtnMoveMain() {
        btnMoveMain.setOnClickListener {
            val nickname = etCreateNk.text.toString()
            val currentUid = auth.currentUser!!.uid

            val userModel = UserModel()
            userModel.email = auth.currentUser?.email
            userModel.uid = auth.currentUser?.uid
            userModel.nickname = nickname
            userModel.timestamp = System.currentTimeMillis()

            firestore.collection("user").document(currentUid).set(userModel)
            Log.d(TAG, "현재 작성된 닉네임으로 User 컬렉션/${currentUid} 문서 이름으로 Database를 생성합니다.")
            moveMain()
        }
    }

    /**
     * 닉네임이 생성되었다면 MainActivity 로 이동 -> 이전 엑티비티 스택 제거
     */
    private fun moveMain() {
        Log.d(TAG, "Nickname save -> Move MainActivity")
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP //액티비티 스택제거
        startActivity(intent)
        finish()
    }
}