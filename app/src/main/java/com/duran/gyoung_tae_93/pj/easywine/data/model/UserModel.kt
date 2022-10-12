package com.duran.gyoung_tae_93.pj.easywine.data.model

data class UserModel(
    var email: String? = null, // 로그인 유저 이메일
    var uid: String? = null, // 로그인 유저 uid
    var nickname: String? = null, // 로그인 유저 닉네임
    var timestamp: Long? = null // 로그인 유저 닉네임 생성 날짜
)
