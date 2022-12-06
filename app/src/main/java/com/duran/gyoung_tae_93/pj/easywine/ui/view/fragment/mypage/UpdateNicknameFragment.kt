package com.duran.gyoung_tae_93.pj.easywine.ui.view.fragment.mypage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.duran.gyoung_tae_93.pj.easywine.R
import com.duran.gyoung_tae_93.pj.easywine.data.model.UserModel
import com.duran.gyoung_tae_93.pj.easywine.databinding.FragmentUpdateNicknameBinding
import com.duran.gyoung_tae_93.pj.easywine.util.FBAuth
import com.duran.gyoung_tae_93.pj.easywine.util.FBDocRef

class UpdateNicknameFragment : DialogFragment() {

    private lateinit var binding: FragmentUpdateNicknameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_update_nickname, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val nickname = arguments?.getString("nickName")

        binding.edNickNameUpdate.hint = nickname!!.toString()

        setBtnCancelCLick()
        setBtnUpdateClick()

    }

    private fun setBtnUpdateClick() {
        binding.btnNickNameUpdate.setOnClickListener {
            if (binding.edNickNameUpdate.text.isEmpty()) {
                // 닉네임 입력이 비어있다면
                Toast.makeText(context, "변경할 닉네임이 없습니다. 닉네임 입력 후 변경하기를 클릭해주세요.", Toast.LENGTH_SHORT)
                    .show()
            } else {
                // 닉네임 입력되었다면
                getUpdateNickname()
            }
        }
    }

    /**
     *  NickName 변경
     */
    private fun getUpdateNickname() {
        val tsDoc = FBDocRef.fbDB.collection("user").document(FBAuth.getUid())

        FBDocRef.fbDB.runTransaction { transition ->

            val dataModel = transition.get(tsDoc).toObject(UserModel::class.java)

            dataModel!!.nickname = binding.edNickNameUpdate.text.toString()

            transition.set(tsDoc, dataModel)
        }

        Toast.makeText(context, "닉네임이 변경되었습니다.", Toast.LENGTH_SHORT).show()
        dismiss()
    }

    /**
     *  취소 버튼
     */
    private fun setBtnCancelCLick() {
        binding.btnNickNameCancel.setOnClickListener {
            dismiss()
        }
    }

}