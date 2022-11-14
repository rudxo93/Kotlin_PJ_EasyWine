package com.duran.gyoung_tae_93.pj.easywine.ui.view.activity.note

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View.OnTouchListener
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.duran.gyoung_tae_93.pj.easywine.R
import com.duran.gyoung_tae_93.pj.easywine.data.model.note.NoteInfoModel
import com.duran.gyoung_tae_93.pj.easywine.databinding.ActivityInfoNoteBinding
import com.duran.gyoung_tae_93.pj.easywine.ui.view.activity.MainActivity
import com.duran.gyoung_tae_93.pj.easywine.util.FBAuth
import com.duran.gyoung_tae_93.pj.easywine.util.FBDocRef
import com.duran.gyoung_tae_93.pj.easywine.util.FBSrg


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
    private lateinit var currentImageUrlSubString: String
    private lateinit var currentImageUrl: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_info_note)

        noteInfo = intent.getSerializableExtra("noteData") as NoteInfoModel

        currentImageUrlSubString = noteInfo.imageUrl!!.substring(82, 107)
        currentImageUrl = noteInfo.imageUrl.toString()

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
        // 터치 이벤트 제거
        sbSweetness.setOnTouchListener(OnTouchListener { _, _ -> true })
        sbAcidity.progress = noteInfo.wineSbAcidity
        sbAcidity.setOnTouchListener(OnTouchListener { _, _ -> true })
        sbTannin.progress = noteInfo.wineSbTannin
        sbTannin.setOnTouchListener(OnTouchListener { _, _ -> true })
        sbBody.progress = noteInfo.wineSbBody
        sbBody.setOnTouchListener(OnTouchListener { _, _ -> true })
        sbAlcohol.progress = noteInfo.wineSbAlcohol
        sbAlcohol.setOnTouchListener(OnTouchListener { _, _ -> true })
        sbAroma.progress = noteInfo.wineSbAroma
        sbAroma.setOnTouchListener(OnTouchListener { _, _ -> true })
        sbBalance.progress = noteInfo.wineBalance
        sbBalance.setOnTouchListener(OnTouchListener { _, _ -> true })
        sbLikable.progress = noteInfo.wineLikable
        sbLikable.setOnTouchListener(OnTouchListener { _, _ -> true })
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
                val intent = Intent(baseContext, UpdateNoteActivity::class.java)
                intent.putExtra("noteData", noteInfo)
                startActivity(intent)
            }
            R.id.menu_delete -> {
                getCurrentDocId()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    /**
     *  해당 글의 document Id 가져오기 -> Storage 삭제 -> Firestore Data 삭제
     */
    private fun getCurrentDocId() {
        val currentUid = FBAuth.getUid()

        FBDocRef.fbDB.collection("note_info").whereEqualTo("uid", currentUid)
            .whereEqualTo("imageUrl", currentImageUrl)
            .get().addOnSuccessListener { result ->
                var noteId = ""

                for (item in result.documentChanges) noteId = item.document.id

                getDeleteImage(noteId)
            }
    }

    private fun getDeleteImage(noteId: String) {

        val storagePath = FBSrg.storageRef.child("images").child(currentImageUrlSubString)
        storagePath.delete().addOnSuccessListener { getDeleteData(noteId) }

    }

    private fun getDeleteData(noteId: String) {

        FBDocRef.fbDB.collection("note_info").document(noteId)
            .delete()
            .addOnSuccessListener {

                val intent = Intent(this, MainActivity::class.java)
                intent.flags =
                    Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP //액티비티 스택제거
                startActivity(intent)
        }
    }

}