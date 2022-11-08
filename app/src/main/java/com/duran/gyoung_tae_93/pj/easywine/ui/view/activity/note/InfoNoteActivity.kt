package com.duran.gyoung_tae_93.pj.easywine.ui.view.activity.note

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View.OnTouchListener
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.duran.gyoung_tae_93.pj.easywine.R
import com.duran.gyoung_tae_93.pj.easywine.data.model.NoteInfoModel
import com.duran.gyoung_tae_93.pj.easywine.databinding.ActivityInfoNoteBinding


class InfoNoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInfoNoteBinding

    private val imageArea by lazy { binding.ivAddPhoto }
    private val tvWineName by lazy { binding.tvNoteName }
    private val tvWineType by lazy { binding.tvNoteType }
    private val tvWineCountry by lazy { binding.tvNoteCountry }
    private val tvWineArea by lazy { binding.tvNoteArea }
    private val tvWineVariety by lazy { binding.tvNoteVariety }
    private val tvWineVintage by lazy { binding.tvNoteVintage }
    private val tvWineAlcohol by lazy { binding.tvNoteAlcohol }
    private val tvWineBuyDate by lazy { binding.tvNoteBuyDate }
    private val tvWineDrinkDate by lazy { binding.tvNoteDrinkDate }
    private val tvWinePrice by lazy { binding.tvNotePrice }
    private val tvWineNoteEtc by lazy { binding.tvNoteEtc }
    private val tvWineNoteAroma by lazy { binding.tvNoteAromaMemo }
    private val tvWineNoteTaste by lazy { binding.tvNoteTaste }

    /* SeekBer */
    private val sbSweetness by lazy { binding.sbNoteSweetness }
    private val sbAcidity by lazy { binding.sbNoteAcidity }
    private val sbTannin by lazy { binding.sbNoteTannin }
    private val sbBody by lazy { binding.sbNoteBody }
    private val sbAlcohol by lazy { binding.sbNoteAlcohol }
    private val sbAroma by lazy { binding.sbNoteAroma }
    private val sbBalance by lazy { binding.sbNoteBalance }
    private val sbLikable by lazy { binding.sbNoteLikable }

    private val TAG = InfoNoteActivity::class.java.simpleName

    lateinit var noteInfo: NoteInfoModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_info_note)

        noteInfo = intent.getSerializableExtra("noteData") as NoteInfoModel

        getToolbarSetting()
        setNoteInfo()
        setNoteImage()
        setSeekBarSetting()

    }

    /**
     * InfoNote 에 조회한 결과 text setting
     */
    private fun setNoteInfo() {
        tvWineName.text = noteInfo.wineName
        tvWineType.text = noteInfo.wineType
        tvWineCountry.text = noteInfo.wineCountry
        tvWineArea.text = noteInfo.wineArea
        tvWineVariety.text = noteInfo.wineVariety
        tvWineVintage.text = noteInfo.wineVintage
        tvWineAlcohol.text = noteInfo.wineAlcohol
        tvWineBuyDate.text = noteInfo.wineBuyDate
        tvWineDrinkDate.text = noteInfo.wineDrinkDate
        tvWinePrice.text = noteInfo.winePrice
        tvWineNoteEtc.text = noteInfo.wineNoteEtc
        tvWineNoteAroma.text = noteInfo.wineNoteAroma
        tvWineNoteTaste.text = noteInfo.wineNoteTaste
    }

    private fun setNoteImage() {

        Glide.with(this).load(noteInfo.imageUrl).into(imageArea)
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setSeekBarSetting() {

        sbSweetness.progress = noteInfo.wineSbSweetness
        sbSweetness.setOnTouchListener(OnTouchListener { view, event -> // 터치 이벤트 제거
            true
        })
        sbAcidity.progress = noteInfo.wineSbAcidity
        sbAcidity.setOnTouchListener(OnTouchListener { view, event ->
            true
        })
        sbTannin.progress = noteInfo.wineSbTannin
        sbTannin.setOnTouchListener(OnTouchListener { view, event ->
            true
        })
        sbBody.progress = noteInfo.wineSbBody
        sbBody.setOnTouchListener(OnTouchListener { view, event ->
            true
        })
        sbAlcohol.progress = noteInfo.wineSbAlcohol
        sbAlcohol.setOnTouchListener(OnTouchListener { view, event ->
            true
        })
        sbAroma.progress = noteInfo.wineSbAroma
        sbAroma.setOnTouchListener(OnTouchListener { view, event ->
            true
        })
        sbBalance.progress = noteInfo.wineBalance
        sbBalance.setOnTouchListener(OnTouchListener { view, event ->
            true
        })
        sbLikable.progress = noteInfo.wineLikable
        sbLikable.setOnTouchListener(OnTouchListener { view, event ->
            true
        })
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_info_note_toolbar, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
            R.id.menu_update -> {
                Toast.makeText(this, "수정하기", Toast.LENGTH_SHORT).show()
                return true
            }
            R.id.menu_delete -> {
                Toast.makeText(this, "삭제하기", Toast.LENGTH_SHORT).show()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}