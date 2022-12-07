# Kotlin_PJ_EasyWine - 와인 노트 앱

####개발기간 : 2022.10.08 ~ 2022.12.06

# About Application

와인에 대해 간단하게 알아볼 수 있고 와인을 마시면서 테이스팅 노트를 작성 할 수 있는 애플리케이션입니다.

#### Easy Wine 앱을 만들게 된 이유

와인을 처음 접하게 되었을때 장터나 와인샵에서 어떤 와인을 선택해야 할지 정말 힘들었고,
어떠한 자리게 갔을때 우연치 않게 와인을 접했는데 그 와인이 인상깊어서 다음번에 구매하고 싶어도
와인을 기억하지 못해서 구매를 못했던 적이 있었습니다.
이러한 불편함을 개선하고 와인을 좀 더 쉽게 접근하기 위해 앱을 개발하게 되었습니다.

# Develop Environment 개발 환경

<div align="center">
    <img src="https://img.shields.io/badge/Android-3DDC84?style=flat&logo=Android&logoColor=white"/>
    <img src="https://img.shields.io/badge/Kotlin-7F52FF?style=flat&logo=Kotlin&logoColor=white"/>
    <img src="https://img.shields.io/badge/Firebase-FFCA28?style=flat&logo=Firebase&logoColor=white"/>
</div>

## 1. OS
- Android

## 2. Language
- Kotlin

## 3. DateBase
- Firebase(Cloud Firestore, Storage, Authentication)

## 4. Use Library
- lottie(Loding page Animation)
- Firebase(Authentication, Firestore Database)
- Google Login(play-services-auth)
- JetPack(Data Binding, LiveData, Navigation, ViewModel, Fragment, recyclerview)
- ViewPager2
- Glide

***

## 완성화면

#### Intro 화면

![easyWineIntro](https://user-images.githubusercontent.com/84020268/206105357-7052efd5-f117-43c7-b8a4-1995b74c3ed4.gif)

Lottie를 사용하여 움직이는 로딩화면을 구현하였고 Wine Bottle과 label을 연상하여 만들었습니다.
처음 어플을 실행한다면 구글계정을 선택하여 구글 로그인을 진행하고 어플에 사용할 닉네임을 작성할 수 있도록 했습니다.
(단, 기존에 로그인하여 어플을 사용했던 유저라면 닉네임 생성없이 바로 Main 화면으로 이동하도록 구현했습니다.)

#### Main Content 1 화면 (와인준비)

![easyWineContent1](https://user-images.githubusercontent.com/84020268/206106671-4cdc7834-67ad-4714-b5e6-b75144d7bab9.gif)

와인을 마시기 위해 알아야 할 간단한 정보들을 담았습니다.
이 컨텐츠들은 CoordinatorLayout을 사용하여 구성하였습니다.

#### Main Content 2 화면 (와인 테이스팅 노트)

![easyWineContent2](https://user-images.githubusercontent.com/84020268/206107320-4ae7e845-bf73-4712-82ea-4aa8ae2d8be5.gif)

와인 기록 쓰에기 사진을 첨부할 수 있고 첨부하기 위해서는 storage 접근 동의를 얻어야 접근할 수 있도록 했습니다.
와인이름부터 구입가격 까지는 필수로 입력 하도록 하였고 나머지는 개개인의 기호에 따라 입력할 수 있도록 구현했습니다.
작성 후 RecyclerView를 사용하여 목록을 나타낼 수 있도록 구현하였습니다.

#### Main Content 3 화면 (와인 분류)

![easyWineContent3](https://user-images.githubusercontent.com/84020268/206109198-dfb3ac01-f951-4842-aa71-aef8d4f34ca8.gif)

와인의 스타일, 생산지, 음식에 따라 어떤 와인들이 있는지 어떤 와인을 매칭해야 하는지에 대한 정보를 구성하였습니다.
와인의 스타일에는 레드, 로제, 화이트, 스파클링에 따라 어떤 와인품종이 있는지,
와인의 생산지에는 와인 생산지에 따라서 와인의 특성과 주요 와인 품종에 대한 정보,
와인의 음식에는 매칭하고자 하는 기준이 음식과 와인에 따라 기준이 음식이라면 어떤 와인이 어울리는지,
와인이 기준이라면 어떤 음식과 잘 어울리는지에 대한 정보를 담았습니다.

#### Main Content 4 화면 (와인 정보)

![easyWineContent4](https://user-images.githubusercontent.com/84020268/206109700-e5fc96d0-c1e0-4f35-a610-f4ab25ae76c5.gif)

와인에 대하서 잘 모른다면 아주 기본적으로 많이 사용하는 용어에 대해서 알 수 있도록 정보를 담아두었습니다.
정보가 부족하다면 더 알아보기를 통해 WebView로 이동하여 더 검색할 수 있도록 구현하였습니다.

#### MyPage 화면 (마이페이지)

![easyWineMyPage](https://user-images.githubusercontent.com/84020268/206110014-8399c2d8-2913-447a-897a-9791a32b4ba9.gif)

마이페이지에서는 닉네임 변경과 회원탈퇴를 할 수 있도록 구현하였습니다.