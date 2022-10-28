package com.duran.gyoung_tae_93.pj.easywine.ui.view.activity

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.duran.gyoung_tae_93.pj.easywine.R
import com.duran.gyoung_tae_93.pj.easywine.databinding.ActivityEditNoteBinding
import java.text.SimpleDateFormat
import java.util.*

class EditNoteActivity : AppCompatActivity() {

    private lateinit var binding : ActivityEditNoteBinding

    private val TAG = EditNoteActivity::class.java.simpleName

    /*DatePickerDialog text set*/
    private val buyDate by lazy { binding.tvNoteBuyDate }
    private val drinkDate by lazy { binding.tvNoteDrinkDate }
    /*Edittext Area*/
    private val etNoteEtc by lazy { binding.etNoteEtc }
    private val etNoteAroma by lazy { binding.etNoteAromaMemo }
    private val etNoteTaste by lazy { binding.etNoteTaste }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_note)

        getToolbarSetting()
        getDatePickerDialog()
        getEditTextMaxLines()
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
            val buyDatePicker = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                val dateString = "${year}/${month+1}/${dayOfMonth}"
                buyDate.setText(dateString)
            }
            DatePickerDialog(this, buyDatePicker, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show()
        }
        /* 시음일자 */
        btnDrinkDate.setOnClickListener {
            val drinkDatePicker = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                val dateString = "${year}/${month+1}/${dayOfMonth}"
                drinkDate.setText(dateString)
            }
            DatePickerDialog(this, drinkDatePicker, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show()
        }

    }

    /**
     * 참고사항, 향의 특징, 맛 표현 EditText 최대입력라인 setting
     */
    private fun getEditTextMaxLines() {
        /* 참고사항 */
        etNoteEtc.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(etNoteEtc.lineCount == 6) {
                    etNoteEtc.setOnKeyListener(object : View.OnKeyListener {
                        override fun onKey(v: View?, keyCode: Int, event: KeyEvent?): Boolean {
                            if((event?.action == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                                imm.hideSoftInputFromWindow(etNoteEtc.windowToken, 0)
                                etNoteEtc.clearFocus()
                                return true
                            }
                            return false
                        }
                    })
                }
            }
            override fun afterTextChanged(s: Editable?) {

            }
        })
        /* 향의 특징 */
        etNoteAroma.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(etNoteAroma.lineCount == 2) {
                    etNoteAroma.setOnKeyListener(object : View.OnKeyListener {
                        override fun onKey(v: View?, keyCode: Int, event: KeyEvent?): Boolean {
                            if((event?.action == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                                imm.hideSoftInputFromWindow(etNoteAroma.windowToken, 0)
                                etNoteAroma.clearFocus()
                                return true
                            }
                            return false
                        }
                    })
                }
            }
            override fun afterTextChanged(s: Editable?) {

            }
        })
        /* 맛 표현 */
        etNoteTaste.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(etNoteTaste.lineCount == 6) {
                    etNoteTaste.setOnKeyListener(object : View.OnKeyListener {
                        override fun onKey(v: View?, keyCode: Int, event: KeyEvent?): Boolean {
                            if((event?.action == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                                imm.hideSoftInputFromWindow(etNoteTaste.windowToken, 0)
                                etNoteTaste.clearFocus()
                                return true
                            }
                            return false
                        }
                    })
                }
            }
            override fun afterTextChanged(s: Editable?) {

            }
        })
    }
}