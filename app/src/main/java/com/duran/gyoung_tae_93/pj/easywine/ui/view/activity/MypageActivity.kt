package com.duran.gyoung_tae_93.pj.easywine.ui.view.activity

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.duran.gyoung_tae_93.pj.easywine.R
import com.duran.gyoung_tae_93.pj.easywine.data.model.UserModel
import com.duran.gyoung_tae_93.pj.easywine.data.model.note.NoteInfoModel
import com.duran.gyoung_tae_93.pj.easywine.databinding.ActivityMypageBinding
import com.duran.gyoung_tae_93.pj.easywine.ui.view.fragment.mypage.UpdateNicknameFragment
import com.duran.gyoung_tae_93.pj.easywine.util.FBAuth
import com.duran.gyoung_tae_93.pj.easywine.util.FBDocRef
import com.duran.gyoung_tae_93.pj.easywine.util.FBSrg
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase

class MypageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMypageBinding

    var nickname: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_mypage)

        Log.e("ddddd", "onCreate")

        getToolbarSetting()
        setDeleteAccount()
        getSearchNickname()
        setUpdateNickname()

    }

    /**
     *  Toolbar Setting
     */
    private fun getToolbarSetting() {
        val toolbar = binding.topToolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    /**
     *  닉네임 실시간 업데이트
     */
    private fun getSearchNickname() {
        val nickNameArea = binding.tvUserNickname

        FBDocRef.fbDB.collection("user").addSnapshotListener { value, error ->

            nickNameArea.text = ""

            if(value == null ) return@addSnapshotListener

            for(dataModel in value.documents) {
                val item = dataModel.toObject(UserModel::class.java)
                if(item?.uid == FBAuth.getUid()) {
                    nickname = item.nickname.toString()
                    nickNameArea.text = item.nickname
                }
            }

        }
    }

    /**
     *  닉네임 업데이트
     */
    private fun setUpdateNickname() {
        binding.ivNickNameUpdate.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("nickName", nickname)

            val dialog = UpdateNicknameFragment()
            dialog.arguments = bundle
            supportFragmentManager.beginTransaction()
            dialog.show(supportFragmentManager, "UpdateNicknameDialog")
        }
    }

    /**
     *  회원탈퇴
     */
    private fun setDeleteAccount() {
        binding.tvDeleteAccount.setOnClickListener {
            showDeleteAccount()
        }
    }

    private fun showDeleteAccount() {
        AlertDialog.Builder(this)
            .setTitle("탈퇴하기")
            .setMessage("탈퇴하시겠습니까?")
            .setPositiveButton("확인") { _, _ ->
                // 확인 시 계정탈퇴 실행
                getContent()
            }
            .setNegativeButton("취소") { _, _ -> }
            .create()
            .show()
    }

    /**
     *  현재 uid 로 저장되어 있는 노트 가져오기
     */
    private fun getContent() {
        val currentUid = FBAuth.getUid()

        FBDocRef.fbDB.collection("note_info").whereEqualTo("uid", currentUid)
            .get().addOnCompleteListener { task ->
                var noteId = ""

                for (dataModel in task.result.documentChanges) {
                    val item = dataModel.document.toObject(NoteInfoModel::class.java)

                    noteId = dataModel.document.id
                    // storage에 저장되어 있는 note의 image부터 날려주기
                    getDeleteImage(item, noteId)
                }

                // image와 note를 다 나렸다면 닉네임 날려주기
                getDeleteNickname()
            }
    }

    /**
     *  Image 와 Note 삭제
     */
    private fun getDeleteImage(noteModel: NoteInfoModel, noteId: String) {
        val imageUrlSubString = noteModel.imageUrl!!.substring(82, 107)

        val storagePath = FBSrg.storageRef.child("images").child(imageUrlSubString)
        storagePath.delete().addOnSuccessListener {
            Log.d("회원탈퇴", "성공적으로 Storage의 이미지를 삭제했습니다.")
            // 해당 note의 image를 삭제했다면 해당 글도 날린다.
            getDeleteData(noteId)
        }

    }

    private fun getDeleteData(noteId: String) {
        FBDocRef.fbDB.collection("note_info").document(noteId)
            .delete()
            .addOnSuccessListener {
                Log.d("회원탈퇴", "성공적으로 해당 게시글을 삭제했습니다.")
            }
    }

    /**
     *  User 의 Nickname 삭제
     */
    private fun getDeleteNickname() {
        Log.e("dddd", "이제 별명삭제")
        val currentUid = FBAuth.getUid()
        FBDocRef.fbDB.collection("user").document(currentUid)
            .delete().addOnSuccessListener {
                Log.d("회원탈퇴", "성공적으로 닉네임을 삭제했습니다.")
                getDeleteAccount()
            }
    }

    private fun getDeleteAccount() {
        val user = Firebase.auth.currentUser!!

        user.delete().addOnCompleteListener { task ->

            if (task.isSuccessful) {
                Log.d("회원탈퇴", "성공적으로 사용자를 삭제했습니다.")
                val intent = Intent(this, IntroActivity::class.java)
                intent.flags =
                    Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP //액티비티 스택제거
                startActivity(intent)
            }
        }
    }
}