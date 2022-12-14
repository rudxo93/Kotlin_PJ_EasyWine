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
     *  ????????? ????????? ????????????
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
     *  ????????? ????????????
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
     *  ????????????
     */
    private fun setDeleteAccount() {
        binding.tvDeleteAccount.setOnClickListener {
            showDeleteAccount()
        }
    }

    private fun showDeleteAccount() {
        AlertDialog.Builder(this)
            .setTitle("????????????")
            .setMessage("?????????????????????????")
            .setPositiveButton("??????") { _, _ ->
                // ?????? ??? ???????????? ??????
                getContent()
            }
            .setNegativeButton("??????") { _, _ -> }
            .create()
            .show()
    }

    /**
     *  ?????? uid ??? ???????????? ?????? ?????? ????????????
     */
    private fun getContent() {
        val currentUid = FBAuth.getUid()

        FBDocRef.fbDB.collection("note_info").whereEqualTo("uid", currentUid)
            .get().addOnCompleteListener { task ->
                var noteId = ""

                for (dataModel in task.result.documentChanges) {
                    val item = dataModel.document.toObject(NoteInfoModel::class.java)

                    noteId = dataModel.document.id
                    // storage??? ???????????? ?????? note??? image?????? ????????????
                    getDeleteImage(item, noteId)
                }

                // image??? note??? ??? ???????????? ????????? ????????????
                getDeleteNickname()
            }
    }

    /**
     *  Image ??? Note ??????
     */
    private fun getDeleteImage(noteModel: NoteInfoModel, noteId: String) {
        val imageUrlSubString = noteModel.imageUrl!!.substring(82, 107)

        val storagePath = FBSrg.storageRef.child("images").child(imageUrlSubString)
        storagePath.delete().addOnSuccessListener {
            Log.d("????????????", "??????????????? Storage??? ???????????? ??????????????????.")
            // ?????? note??? image??? ??????????????? ?????? ?????? ?????????.
            getDeleteData(noteId)
        }

    }

    private fun getDeleteData(noteId: String) {
        FBDocRef.fbDB.collection("note_info").document(noteId)
            .delete()
            .addOnSuccessListener {
                Log.d("????????????", "??????????????? ?????? ???????????? ??????????????????.")
            }
    }

    /**
     *  User ??? Nickname ??????
     */
    private fun getDeleteNickname() {
        Log.e("dddd", "?????? ????????????")
        val currentUid = FBAuth.getUid()
        FBDocRef.fbDB.collection("user").document(currentUid)
            .delete().addOnSuccessListener {
                Log.d("????????????", "??????????????? ???????????? ??????????????????.")
                getDeleteAccount()
            }
    }

    private fun getDeleteAccount() {
        val user = Firebase.auth.currentUser!!

        user.delete().addOnCompleteListener { task ->

            if (task.isSuccessful) {
                Log.d("????????????", "??????????????? ???????????? ??????????????????.")
                val intent = Intent(this, IntroActivity::class.java)
                intent.flags =
                    Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP //???????????? ????????????
                startActivity(intent)
            }
        }
    }
}