package com.duran.gyoung_tae_93.pj.easywine.ui.view.activity

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
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
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.duran.gyoung_tae_93.pj.easywine.R
import com.duran.gyoung_tae_93.pj.easywine.databinding.ActivityEditNoteBinding
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

    private val btnSave by lazy { binding.btnSaveNote }

    private var isImageUpload = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_note)

        getToolbarSetting()
        getDatePickerDialog()
        getEditTextMaxLines()
        getSeekBarSetting()
        getAddPhotoBtn()

        btnSave.setOnClickListener {
            Log.e(
                "result",
                "=========================================================================="
            )
            Log.e("resultEtWineName", etWineName.text.toString())
            Log.e("resultEtWineType", etWineType.text.toString())
            Log.e("resultEtCountry", etCountry.text.toString())
            Log.e("resultEtArea", etArea.text.toString())
            Log.e("resultEtVariety", etVariety.text.toString())
            Log.e("resultEtVintage", etVintage.text.toString())
            Log.e("resultEtAlcohol", etAlcohol.text.toString())

            Log.e("resultBuyDate", buyDate.text.toString())
            Log.e("resultDrinkDate", drinkDate.text.toString())

            Log.e("resultEtPrice", etPrice.text.toString())
            Log.e("resultEtNoteEtc", etNoteEtc.text.toString())

            Log.e("resultSweetness", resultSweetness.toString())
            Log.e("resultAcidity", resultAcidity.toString())
            Log.e("resultTannin", resultTannin.toString())
            Log.e("resultBody", resultBody.toString())
            Log.e("resultAlcohol", resultAlcohol.toString())
            Log.e("resultAroma", resultAroma.toString())

            Log.e("resultEtNoteAroma", etNoteAroma.text.toString())

            Log.e("resultBalance", resultBalance.toString())
            Log.e("resultLikable", resultLikable.toString())

            Log.e("resultEtNoteTaste", etNoteTaste.text.toString())
            Log.e(
                "result",
                "=========================================================================="
            )
        }
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
                    showPermissionPopup()
                }

                else -> {
                    // 권한 요청 작업 -> 요청할 권한 배열로 담아서 요청
                    requestPermissions(
                        arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
                        1000
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
                requestPermissions(arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE), 1000)
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
            1000 -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // 권한이 부여 됬을 때
                    getPhotoStorage()
                } else {
                    Toast.makeText(this, "권한을 거부하셨습니다.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun getPhotoStorage() {
        val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
        startActivityForResult(gallery, 2000)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == 2000) {
            btnImageAdd.setImageURI(data?.data)
            photoImage.visibility = View.GONE
            photoImageTitle.visibility = View.GONE
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
     * 구입시기와 시음일자 DatePickerDialog 기능 구현
     */
    private fun getDatePickerDialog() {
        val btnBuyDate = binding.ivNoteBuyDateIcon
        val btnDrinkDate = binding.ivNoteDrinkDateIcon

        val myCalendar = Calendar.getInstance()
        /* 구입시기 */
        btnBuyDate.setOnClickListener {
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

    private fun getSeekBarSetting() {
        /* 당도 result 1 -> Dry, 2 -> O-dry, 3 -> M-dry, 4 -> M-sweet, 5 -> Sweet */
        sbSweetness.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                when (progress) {
                    in 0..20 -> {
                        resultSweetness = 1
                    }
                    in 21..40 -> {
                        resultSweetness = 2
                    }
                    in 41..60 -> {
                        resultSweetness = 3
                    }
                    in 61..80 -> {
                        resultSweetness = 4
                    }
                    in 81..100 -> {
                        resultSweetness = 5
                    }
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
        /* 산도 result 1 -> 낮은, 2 -> 부드러운, 3 -> 적당한, 4 -> 높은, 5 -> 강한 */
        sbAcidity.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                when (progress) {
                    in 0..20 -> {
                        resultAcidity = 1
                    }
                    in 21..40 -> {
                        resultAcidity = 2
                    }
                    in 41..60 -> {
                        resultAcidity = 3
                    }
                    in 61..80 -> {
                        resultAcidity = 4
                    }
                    in 81..100 -> {
                        resultAcidity = 5
                    }
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
        /* 타닌 result 1 -> 약한, 2 -> 부드러운, 3 -> 적당한, 4 -> 견고한, 5 -> 강한 */
        sbTannin.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                when (progress) {
                    in 0..20 -> {
                        resultTannin = 1
                    }
                    in 21..40 -> {
                        resultTannin = 2
                    }
                    in 41..60 -> {
                        resultTannin = 3
                    }
                    in 61..80 -> {
                        resultTannin = 4
                    }
                    in 81..100 -> {
                        resultTannin = 5
                    }
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
        /* 바디 result 1 -> Light, 2 -> M-Light, 3 -> Medium, 4 -> M-full, 5 -> Full */
        sbBody.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                when (progress) {
                    in 0..20 -> {
                        resultBody = 1
                    }
                    in 21..40 -> {
                        resultBody = 2
                    }
                    in 41..60 -> {
                        resultBody = 3
                    }
                    in 61..80 -> {
                        resultBody = 4
                    }
                    in 81..100 -> {
                        resultBody = 5
                    }
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
        /* 알코올 result 1 -> 낮은, 2 -> 부드러운, 3 -> 적당한, 4 -> 높은, 5 -> 강한 */
        sbAlcohol.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                when (progress) {
                    in 0..20 -> {
                        resultAlcohol = 1
                    }
                    in 21..40 -> {
                        resultAlcohol = 2
                    }
                    in 41..60 -> {
                        resultAlcohol = 3
                    }
                    in 61..80 -> {
                        resultAlcohol = 4
                    }
                    in 81..100 -> {
                        resultAlcohol = 5
                    }
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
        /* 향의 강도 result 1 -> 아주 약함, 2 -> 약함, 3 -> 보통, 4 -> 강함, 5 -> 아주 강함 */
        sbAroma.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                when (progress) {
                    in 0..20 -> {
                        resultAroma = 1
                    }
                    in 21..40 -> {
                        resultAroma = 2
                    }
                    in 41..60 -> {
                        resultAroma = 3
                    }
                    in 61..80 -> {
                        resultAroma = 4
                    }
                    in 81..100 -> {
                        resultAroma = 5
                    }
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
        /* 균형 result 1 -> 불균형, 2 -> 그런대로, 3 -> 괜찮은, 4 -> 좋은, 5 -> 훌륭한 */
        sbBalance.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                when (progress) {
                    in 0..20 -> {
                        resultBalance = 1
                    }
                    in 21..40 -> {
                        resultBalance = 2
                    }
                    in 41..60 -> {
                        resultBalance = 3
                    }
                    in 61..80 -> {
                        resultBalance = 4
                    }
                    in 81..100 -> {
                        resultBalance = 5
                    }
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
        /* 호감도 result 1 -> 비호감, 2 -> 별로, 3 -> 보통, 4 -> 호감, 5 -> 매우호감 */
        sbLikable.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                when (progress) {
                    in 0..20 -> {
                        resultLikable = 1
                    }
                    in 21..40 -> {
                        resultLikable = 2
                    }
                    in 41..60 -> {
                        resultLikable = 3
                    }
                    in 61..80 -> {
                        resultLikable = 4
                    }
                    in 81..100 -> {
                        resultLikable = 5
                    }
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
    }

}