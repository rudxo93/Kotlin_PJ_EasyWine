package com.duran.gyoung_tae_93.pj.easywine.ui.view.activity.note

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.SeekBar
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.duran.gyoung_tae_93.pj.easywine.R
import com.duran.gyoung_tae_93.pj.easywine.data.model.note.NoteInfoModel
import com.duran.gyoung_tae_93.pj.easywine.databinding.ActivityUpdateNoteBinding
import com.duran.gyoung_tae_93.pj.easywine.ui.view.activity.MainActivity
import com.duran.gyoung_tae_93.pj.easywine.util.FBAuth
import com.duran.gyoung_tae_93.pj.easywine.util.FBDocRef
import com.duran.gyoung_tae_93.pj.easywine.util.FBSrg
import java.text.SimpleDateFormat
import java.util.*


class UpdateNoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateNoteBinding

    private val btnImageAdd by lazy { binding.ivAddPhoto }
    private val photoImage by lazy { binding.ivAddPhotoCamera }
    private val photoImageTitle by lazy { binding.tvAddPhotoCameraTitle }

    /* Edittext content */
    private val etWineName by lazy { binding.etNoteName }
    private val etWineType by lazy { binding.etNoteType }
    private val etCountry by lazy { binding.etNoteCountry }
    private val etArea by lazy { binding.etNoteArea }
    private val etVariety by lazy { binding.etNoteVariety }
    private val etVintage by lazy { binding.etNoteVintage }
    private val etAlcohol by lazy { binding.etNoteAlcohol }
    private val etPrice by lazy { binding.etNotePrice }

    /*DatePickerDialog text set*/
    private val buyDate by lazy { binding.tvNoteBuyDate }
    private val drinkDate by lazy { binding.tvNoteDrinkDate }

    /*Edittext Area*/
    private val etNoteEtc by lazy { binding.etNoteEtc }
    private val etNoteAroma by lazy { binding.etNoteAromaMemo }
    private val etNoteTaste by lazy { binding.etNoteTaste }

    /* SeekBar & Result */
    private val sbSweetness by lazy { binding.sbNoteSweetness }
    private var resultSweetness = 0
    private val sbAcidity by lazy { binding.sbNoteAcidity }
    private var resultAcidity = 0
    private val sbTannin by lazy { binding.sbNoteTannin }
    private var resultTannin = 0
    private val sbBody by lazy { binding.sbNoteBody }
    private var resultBody = 0
    private val sbAlcohol by lazy { binding.sbNoteAlcohol }
    private var resultAlcohol = 0
    private val sbAroma by lazy { binding.sbNoteAroma }
    private var resultAroma = 0
    private val sbBalance by lazy { binding.sbNoteBalance }
    private var resultBalance = 0
    private val sbLikable by lazy { binding.sbNoteLikable }
    private var resultLikable = 0

    private val btnUpdateBtn by lazy { binding.btnUpdateNote }

    private val TAG = UpdateNoteActivity::class.java.simpleName
    private var isImageUpload = false
    private lateinit var photoUri: Uri
    private lateinit var noteInfo: NoteInfoModel
    private var currentUid = FBAuth.getUid()
    private lateinit var currentImageUrlSubString: String
    private lateinit var currentImageUrl: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_update_note)

        noteInfo = intent.getSerializableExtra("noteData") as NoteInfoModel

        currentImageUrlSubString = noteInfo.imageUrl!!.substring(82, 107)
        currentImageUrl = noteInfo.imageUrl.toString()

        getToolbarSetting() // Toolbar
        // Data Setting
        setNoteImage()
        setNoteData()
        setSeekBarSetting()

        getAddPhotoBtn()
        getDatePickerDialog()
        getEditTextMaxLines()
        getSeekBarSetting()
        // 변경하기 Button Click
        getUpdateBtnClick()
    }

    /**
     *  노트작성 업데이트 버튼 클릭
     */
    private fun getUpdateBtnClick() {
        btnUpdateBtn.setOnClickListener {
            initEmptyCheck()
        }
    }

    /**
     *  필요한 최소한의 컨텐츠가 비었는지 확인 후 채워졌다면 저장하기 이동
     */
    private fun initEmptyCheck() {
        when {
            etWineName.text.isEmpty() -> {
                Toast.makeText(this, "와인명이 비어있습니다.", Toast.LENGTH_SHORT).show()
                etWineName.requestFocus()
            }
            etWineType.text.isEmpty() -> {
                Toast.makeText(this, "종류가 비어있습니다.", Toast.LENGTH_SHORT).show()
                etWineType.requestFocus()
            }
            etCountry.text.isEmpty() -> {
                Toast.makeText(this, "국가가 비어있습니다.", Toast.LENGTH_SHORT).show()
                etCountry.requestFocus()
            }
            etArea.text.isEmpty() -> {
                Toast.makeText(this, "지역이 비어있습니다.", Toast.LENGTH_SHORT).show()
                etArea.requestFocus()
            }
            etVariety.text.isEmpty() -> {
                Toast.makeText(this, "품종이 비어있습니다.", Toast.LENGTH_SHORT).show()
                etVariety.requestFocus()
            }
            etVintage.text.isEmpty() -> {
                Toast.makeText(this, "빈티지가 비어있습니다.", Toast.LENGTH_SHORT).show()
                etVintage.requestFocus()
            }
            etAlcohol.text.isEmpty() -> {
                Toast.makeText(this, "알코올이 비어있습니다.", Toast.LENGTH_SHORT).show()
                etAlcohol.requestFocus()
            }
            buyDate.text.isEmpty() -> {
                Toast.makeText(this, "구입시기가 비어있습니다.", Toast.LENGTH_SHORT).show()
            }
            drinkDate.text.isEmpty() -> {
                Toast.makeText(this, "시음일자가 비어있습니다.", Toast.LENGTH_SHORT).show()
            }
            etPrice.text.isEmpty() -> {
                Toast.makeText(this, "구입가격이 비어있습니다.", Toast.LENGTH_SHORT).show()
                etPrice.requestFocus()
            }
            else -> {
                // 이미지를 변경했는지?
                if (!isImageUpload) {
                    val imageUrl = noteInfo.imageUrl.toString()
                    getSaveNote(imageUrl, currentImageUrl)
                } else {
                    imageDelete()
                }
            }
        }
    }

    /**
     *  기존에 저장되어있는 Image 는 삭제
     */
    private fun imageDelete() {

        val storagePath = FBSrg.storageRef.child("images").child(currentImageUrlSubString)

        storagePath.delete().addOnSuccessListener { imageUpload(currentImageUrl) }
    }

    /**
     *   다시 Image 를 저장한다.
     */
    @SuppressLint("SimpleDateFormat")
    private fun imageUpload(currentImageUrl: String) {

        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val imageFileName = "IMAGE_$timeStamp.png"
        val storagePath = FBSrg.storageRef.child("images").child(imageFileName)

        storagePath.putFile(photoUri).continueWithTask {
            return@continueWithTask storagePath.downloadUrl
        }.addOnCompleteListener { downloadUrl ->
            val imageUrl = downloadUrl.result.toString()
            getSaveNote(imageUrl, currentImageUrl)
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun getSaveNote(imageUrl: String, currentImageUrl: String) {

        // NoteInfo
        val wineImageUrl = imageUrl
        val wineName = etWineName.text.toString()
        val wineType = etWineType.text.toString()
        val wineCountry = etCountry.text.toString()
        val wineArea = etArea.text.toString()
        val wineVariety = etVariety.text.toString()
        val wineVintage = etVintage.text.toString()
        val wineAlcohol = etAlcohol.text.toString()
        val wineBuyDate = buyDate.text.toString()
        val wineDrinkDate = drinkDate.text.toString()
        val winePrice = etPrice.text.toString()
        val saveDate = noteInfo.saveTime
        // NoteContent
        val wineNoteEtc = etNoteEtc.text.toString()
        val wineSbSweetness = resultSweetness
        val wineSbAcidity = resultAcidity
        val wineSbTannin = resultTannin
        val wineSbBody = resultBody
        val wineSbAlcohol = resultAlcohol
        val wineSbAroma = resultAroma
        val wineNoteAroma = etNoteAroma.text.toString()
        val wineBalance = resultBalance
        val wineLikable = resultLikable
        val wineNoteTaste = etNoteTaste.text.toString()
        val isChecked = noteInfo.isChecked

        val noteUpdate = NoteInfoModel(
            FBAuth.getUid(),
            wineImageUrl,
            wineName,
            wineType,
            wineCountry,
            wineArea,
            wineVariety,
            wineVintage,
            wineAlcohol,
            wineBuyDate,
            wineDrinkDate,
            winePrice,
            wineNoteEtc,
            wineSbSweetness,
            wineSbAcidity,
            wineSbTannin,
            wineSbBody,
            wineSbAlcohol,
            wineSbAroma,
            wineNoteAroma,
            wineBalance,
            wineLikable,
            wineNoteTaste,
            saveDate,
            isChecked
        )

        getCurrentDocId(noteUpdate, currentImageUrl)

    }

    private fun getCurrentDocId(noteUpdate: NoteInfoModel, currentImageUrl: String) {
        FBDocRef.fbDB.collection("note_info").whereEqualTo("uid", currentUid)
            .whereEqualTo("imageUrl", currentImageUrl)
            .get().addOnSuccessListener { result ->
                var noteId = ""

                for (item in result.documentChanges) noteId = item.document.id

                getUpdateNote(noteId, noteUpdate)
            }
    }

    private fun getUpdateNote(noteId: String, noteUpdate: NoteInfoModel) {
        val tsDoc = FBDocRef.fbDB.collection("note_info").document(noteId)

        FBDocRef.fbDB.runTransaction { transition ->
            val dataModel = transition.get(tsDoc).toObject(NoteInfoModel::class.java)

            dataModel!!.imageUrl = noteUpdate.imageUrl
            dataModel.wineName = noteUpdate.wineName
            dataModel.wineType = noteUpdate.wineType
            dataModel.wineCountry = noteUpdate.wineCountry
            dataModel.wineArea = noteUpdate.wineArea
            dataModel.wineVariety = noteUpdate.wineVariety
            dataModel.wineVintage = noteUpdate.wineVintage
            dataModel.wineAlcohol = noteUpdate.wineAlcohol
            dataModel.wineBuyDate = noteUpdate.wineBuyDate
            dataModel.wineDrinkDate = noteUpdate.wineDrinkDate
            dataModel.winePrice = noteUpdate.winePrice
            dataModel.saveTime = noteUpdate.saveTime
            dataModel.wineNoteEtc = noteUpdate.wineNoteEtc
            dataModel.wineSbSweetness = noteUpdate.wineSbSweetness
            dataModel.wineSbAcidity = noteUpdate.wineSbAcidity
            dataModel.wineSbTannin = noteUpdate.wineSbTannin
            dataModel.wineSbBody = noteUpdate.wineSbBody
            dataModel.wineSbAlcohol = noteUpdate.wineSbAlcohol
            dataModel.wineSbAroma = noteUpdate.wineSbAroma
            dataModel.wineNoteAroma = noteUpdate.wineNoteAroma
            dataModel.wineBalance = noteUpdate.wineBalance
            dataModel.wineLikable = noteUpdate.wineLikable
            dataModel.wineNoteTaste = noteUpdate.wineNoteTaste
            dataModel.isChecked = noteUpdate.isChecked

            transition.set(tsDoc, dataModel)

        }
        Toast.makeText(this, "변경되었습니다.", Toast.LENGTH_SHORT).show()

        val intent = Intent(this, MainActivity::class.java)
        intent.flags =
            Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP //액티비티 스택제거
        startActivity(intent)
    }

    /**
     *  넘어온 Data Setting
     */
    private fun setNoteImage() {

        Glide.with(this).load(noteInfo.imageUrl).into(btnImageAdd)
        photoImage.visibility = View.GONE
        photoImageTitle.visibility = View.GONE
    }

    private fun setNoteData() {
        etWineName.setText(noteInfo.wineName)
        etWineType.setText(noteInfo.wineType)
        etCountry.setText(noteInfo.wineCountry)
        etArea.setText(noteInfo.wineArea)
        etVariety.setText(noteInfo.wineVariety)
        etVintage.setText(noteInfo.wineVintage)
        etAlcohol.setText(noteInfo.wineAlcohol)
        etPrice.setText(noteInfo.winePrice)
        buyDate.text = noteInfo.wineBuyDate
        drinkDate.text = noteInfo.wineDrinkDate
        etNoteEtc.setText(noteInfo.wineNoteEtc)
        etNoteAroma.setText(noteInfo.wineNoteAroma)
        etNoteTaste.setText(noteInfo.wineNoteTaste)
    }

    private fun setSeekBarSetting() {
        sbSweetness.progress = noteInfo.wineSbSweetness
        sbAcidity.progress = noteInfo.wineSbAcidity
        sbTannin.progress = noteInfo.wineSbTannin
        sbBody.progress = noteInfo.wineSbBody
        sbAlcohol.progress = noteInfo.wineSbAlcohol
        sbAroma.progress = noteInfo.wineSbAroma
        sbBalance.progress = noteInfo.wineBalance
        sbLikable.progress = noteInfo.wineLikable
    }

    /**
     * 상단 Toolbar 세팅
     */
    private fun getToolbarSetting() {
        val toolbar = binding.noteTopToolbar
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
     *  STORAGE 권한 얻기 -> STORAGE 이동
     */
    private fun getAddPhotoBtn() {
        btnImageAdd.setOnClickListener {
            when {
                ContextCompat.checkSelfPermission( // 1. 사용할 권한이 주어졌는지 check
                    this,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_GRANTED -> getPhotoStorage()
                // 권한 수락이 거절 -> 팝업 띄워주기
                shouldShowRequestPermissionRationale(android.Manifest.permission.READ_EXTERNAL_STORAGE) -> {
                    Log.d(TAG, "Permossion Refuse. Open PopUp Dialog")
                    showPermissionPopup()
                }

                else -> {
                    // 권한 요청 작업 -> 요청할 권한 배열로 담아서 요청
                    requestPermissions(
                        arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
                        100
                    )
                }
            }
            // 권한이 부여되어 있다면 갤러리에서 사진 선택 기능
        }

    }

    // 권한 수락 거절 시 팝업 띄워주기
    private fun showPermissionPopup() {
        AlertDialog.Builder(this)
            .setTitle("권한이 필요합니다.")
            .setMessage("와인 사진을 불러오기 위해 권한이 필요합니다.")
            .setPositiveButton("동의") { _, _ ->
                // 권한 요청 팝업
                requestPermissions(arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE), 100)
            }
            .setNegativeButton("취소") { _, _ -> }
            .create()
            .show()
    }

    // 권한 수락, 거절 시 호출 -> 수락 시 STORAGE 접근 허용 / 거절 시 간단한 Toast
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            100 -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // 권한이 부여 됬을 때
                    getPhotoStorage()
                } else {
                    Toast.makeText(this, "권한을 거부하셨습니다.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    /**
     *  Storage 접근 -> 이미지 가져오기
     */
    @SuppressLint("IntentReset")
    private fun getPhotoStorage() {
        Log.d(TAG, "Access the Storage.")
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
        intent.type = "image/*"
        photoResult.launch(intent)
        isImageUpload = true
    }

    private var photoResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            when (result.resultCode) {
                RESULT_OK -> {
                    // 사진 받는 부분
                    photoUri = result.data?.data!! // 이미지 경로 넘어옴
                    btnImageAdd.setImageURI(photoUri) // 선택한 이미지 넣어주기
                    photoImage.visibility = View.GONE
                    photoImageTitle.visibility = View.GONE
                }
                else -> {
                    Toast.makeText(this, "취소되었습니다.", Toast.LENGTH_SHORT).show()
                }
            }
        }

    /**
     * 구입시기와 시음일자 DatePickerDialog 기능 구현
     */
    private fun getDatePickerDialog() {
        val btnBuyDate = binding.ivNoteBuyDateIcon
        val btnDrinkDate = binding.ivNoteDrinkDateIcon

        val myCalendar = Calendar.getInstance()
        /* 구입시기 */
        btnBuyDate.setOnClickListener {
            Log.d(TAG, "Open Buy DatePicker")
            val buyDatePicker = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                val dateString = "${year}/${month + 1}/${dayOfMonth}"
                buyDate.text = dateString
            }
            DatePickerDialog(
                this, buyDatePicker, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }
        /* 시음일자 */
        btnDrinkDate.setOnClickListener {
            Log.d(TAG, "Open Drink DatePicker")
            val drinkDatePicker = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                val dateString = "${year}/${month + 1}/${dayOfMonth}"
                drinkDate.text = dateString
            }
            DatePickerDialog(
                this,
                drinkDatePicker,
                myCalendar.get(Calendar.YEAR),
                myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }
    }

    /**
     * 참고사항, 향의 특징, 맛 표현 EditText 최대입력라인 setting
     */
    private fun getEditTextMaxLines() {
        /* 참고사항 */
        etNoteEtc.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (etNoteEtc.lineCount == 6) {
                    etNoteEtc.setOnKeyListener(object : View.OnKeyListener {
                        override fun onKey(v: View?, keyCode: Int, event: KeyEvent?): Boolean {
                            if ((event?.action == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                                val imm =
                                    getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                                imm.hideSoftInputFromWindow(etNoteEtc.windowToken, 0)
                                etNoteEtc.clearFocus()
                                return true
                            }
                            return false
                        }
                    })
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })
        /* 향의 특징 */
        etNoteAroma.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (etNoteAroma.lineCount == 2) {
                    etNoteAroma.setOnKeyListener(object : View.OnKeyListener {
                        override fun onKey(v: View?, keyCode: Int, event: KeyEvent?): Boolean {
                            if ((event?.action == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                                val imm =
                                    getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                                imm.hideSoftInputFromWindow(etNoteAroma.windowToken, 0)
                                etNoteAroma.clearFocus()
                                return true
                            }
                            return false
                        }
                    })
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })
        /* 맛 표현 */
        etNoteTaste.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (etNoteTaste.lineCount == 6) {
                    etNoteTaste.setOnKeyListener(object : View.OnKeyListener {
                        override fun onKey(v: View?, keyCode: Int, event: KeyEvent?): Boolean {
                            if ((event?.action == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                                val imm =
                                    getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                                imm.hideSoftInputFromWindow(etNoteTaste.windowToken, 0)
                                etNoteTaste.clearFocus()
                                return true
                            }
                            return false
                        }
                    })
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    /**
     *  SeekBar 세팅하기
     */
    private fun getSeekBarSetting() {
        /* 당도 progress 0-20 -> Dry, 21-40 -> O-dry, 41-60 -> M-dry, 61-80 -> M-sweet, 81-100 -> Sweet */
        sbSweetness.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                resultSweetness = progress
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
        /* 산도 progress 0-20 -> 낮은, 21-40 -> 부드러운, 41-60 -> 적당한, 61-80 -> 높은, 81-100 -> 강한 */
        sbAcidity.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                resultAcidity = progress
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
        /* 타닌 progress 0-20 -> 약한, 21-40 -> 부드러운, 41-60 -> 적당한, 61-80 -> 견고한, 81-100 -> 강한 */
        sbTannin.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                resultTannin = progress
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
        /* 바디 progress 0-20 -> Light, 21-40 -> M-Light, 41-60 -> Medium, 61-80 -> M-full, 81-100 -> Full */
        sbBody.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                resultBody = progress
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
        /* 알코올 progress 0-20 -> 낮은, 21-40 -> 부드러운, 41-60 -> 적당한, 61-80 -> 높은, 81-100 -> 강한 */
        sbAlcohol.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                resultAlcohol = progress
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
        /* 향의 강도 progress 0-20 -> 아주 약함, 21-40 -> 약함, 41-60 -> 보통, 61-80 -> 강함, 81-100 -> 아주 강함 */
        sbAroma.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                resultAroma = progress
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
        /* 균형 progress 0-20 -> 불균형, 21-40 -> 그런대로, 41-60 -> 괜찮은, 61-80 -> 좋은, 81-100 -> 훌륭한 */
        sbBalance.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                resultBalance = progress
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
        /* 호감도 progress 0-20 -> 비호감, 21-40 -> 별로, 41-60 -> 보통, 61-80 -> 호감, 81-100 -> 매우호감 */
        sbLikable.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                resultLikable = progress
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
    }
}