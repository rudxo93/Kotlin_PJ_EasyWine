package com.duran.gyoung_tae_93.pj.easywine.ui.view.activity.note

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import android.widget.SeekBar
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.duran.gyoung_tae_93.pj.easywine.R
import com.duran.gyoung_tae_93.pj.easywine.data.model.note.NoteInfoModel
import com.duran.gyoung_tae_93.pj.easywine.databinding.ActivityEditNoteBinding
import com.duran.gyoung_tae_93.pj.easywine.ui.viewmodel.NoteViewModel
import com.duran.gyoung_tae_93.pj.easywine.util.FBAuth
import com.duran.gyoung_tae_93.pj.easywine.util.FBSrg
import com.google.android.gms.tasks.Task
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class EditNoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditNoteBinding

    private val TAG = EditNoteActivity::class.java.simpleName

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

    private var isImageUpload = false
    private lateinit var photoUri: Uri

    private val viewModel by lazy { ViewModelProvider(this)[NoteViewModel::class.java] }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_note)

        getToolbarSetting()
        getDatePickerDialog()
        getEditTextMaxLines()
        getSeekBarSetting()
        getAddPhotoBtn()

        getSaveBtnClick()
    }

    /**
     *  노트작성 저장하기 버튼 클릭
     */
    private fun getSaveBtnClick() {
        binding.btnSaveNote.setOnClickListener {
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
                if (isImageUpload == true) {
                    // 업로드 도중 화면 터치 막기
                    val dialog = Dialog(this)

                    dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT)) // 배경 투명하기
                    dialog.setContentView(ProgressBar(this)) // ProgressBar 위젯 생성
                    dialog.setCanceledOnTouchOutside(false) // 외부 터치 막기
                    dialog.setOnCancelListener { this.finish() } // 뒤로가기시 현재 엑티비티 종료

                    dialog.show()

                    imageUpload(dialog)
                }
            }
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun imageUpload(dialog: Dialog) {
        // 이미지 저장 날싸와 시간(파일명이 중복되지 않도록 날짜와 시간으로)
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        // 파일 저장 이름 만들기 -> IMAGE_저장날짜.png
        val imageFileName = "IMAGE_" + timeStamp + ".png"

        val storagePath = FBSrg.storageRef.child("images").child(imageFileName)

        storagePath.putFile(photoUri).continueWithTask {
            return@continueWithTask storagePath.downloadUrl
        }.addOnCompleteListener { downloadUrl ->
            getSaveNote(downloadUrl, dialog)
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun getSaveNote(downloadUrl: Task<Uri>, dialog: Dialog) {

        // NoteInfo
        val wineImageUrl = downloadUrl.result.toString()
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
        val saveDate = System.currentTimeMillis()
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
        CoroutineScope(Dispatchers.IO).launch {
            val noteInfo = NoteInfoModel(
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
            )
            viewModel.insertNoteInfo(noteInfo)
            dialog.dismiss()
            finish()
        }
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
                ) == PackageManager.PERMISSION_GRANTED -> {
                    // 권한이 부여되어 있다면 갤러리에서 사진 선택 기능
                    getPhotoStorage()
                }
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

    var photoResult =
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
        var previousString = ""
        /* 참고사항 */
        etNoteEtc.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                previousString = s.toString()
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                if (etNoteEtc.lineCount >= 7) {
                    etNoteEtc.setText(previousString)
                    etNoteEtc.setSelection(etNoteEtc.length())
                }
            }
        })
        /* 향의 특징 */
        etNoteAroma.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                previousString = s.toString()
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                if (etNoteAroma.lineCount >= 3) {
                    etNoteAroma.setText(previousString)
                    etNoteAroma.setSelection(etNoteAroma.length())
                }
            }
        })
        /* 맛 표현 */
        etNoteTaste.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                previousString = s.toString()
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                if (etNoteTaste.lineCount >= 7) {
                    etNoteTaste.setText(previousString)
                    etNoteTaste.setSelection(etNoteTaste.length())
                }
            }
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