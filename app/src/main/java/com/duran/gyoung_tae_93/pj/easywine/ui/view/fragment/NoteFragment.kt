package com.duran.gyoung_tae_93.pj.easywine.ui.view.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.duran.gyoung_tae_93.pj.easywine.R
import com.duran.gyoung_tae_93.pj.easywine.data.model.note.NoteInfoModel
import com.duran.gyoung_tae_93.pj.easywine.databinding.FragmentNoteBinding
import com.duran.gyoung_tae_93.pj.easywine.ui.adapter.note.NoteRVAdapter
import com.duran.gyoung_tae_93.pj.easywine.ui.view.activity.note.EditNoteActivity
import com.duran.gyoung_tae_93.pj.easywine.ui.view.activity.note.InfoNoteActivity
import com.duran.gyoung_tae_93.pj.easywine.ui.viewmodel.NoteViewModel
import com.duran.gyoung_tae_93.pj.easywine.util.FBAuth
import com.duran.gyoung_tae_93.pj.easywine.util.FBDocRef

class NoteFragment : Fragment() {

    private lateinit var binding: FragmentNoteBinding
    private lateinit var viewModel: NoteViewModel
    lateinit var rvAdapter: NoteRVAdapter

    var currentUid = FBAuth.getUid()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_note, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[NoteViewModel::class.java]

        initMoveNoteEdit()
        getRVSetting()
    }

    /**
     *  Recycler View Setting
     */
    private fun getRVSetting() {
        val rv: RecyclerView = binding.rvWineNote

        rvAdapter = NoteRVAdapter(requireContext())
        rv.adapter = rvAdapter
        rv.layoutManager = LinearLayoutManager(context)

        // 컨텐츠 클릭 시 -> Inside 이동
        rvAdapter.setItemClickListener(object : NoteRVAdapter.ItemClickListener {
            override fun onClick(view: View, position: Int, imageUrl: String?) {
                var noteData = NoteInfoModel()

                FBDocRef.fbDB.collection("note_info").whereEqualTo("uid", currentUid)
                    .whereEqualTo("imageUrl", imageUrl)
                    .get().addOnSuccessListener { result ->

                        if (result == null) return@addOnSuccessListener

                        for (dataModel in result.documents) {
                            val data = dataModel.toObject(NoteInfoModel::class.java)
                            if (data != null) {
                                noteData = data
                            }
                        }

                        val intent = Intent(activity, InfoNoteActivity::class.java)
                        intent.putExtra("noteData", noteData)
                        startActivity(intent)
                    }
            }
        })

    }

    /**
     *  노트 작성 Activity 이동
     */
    private fun initMoveNoteEdit() {
        binding.btnNoteEdit.setOnClickListener {
            val intent = Intent(context, EditNoteActivity::class.java)
            startActivity(intent)
        }
    }

    /**
     *  작성이 끝나고 다시 Fragment 가 start 되면 추가된 전체 데이터 RecyclerView 업데이트
     */
    override fun onStart() {
        super.onStart()
        observerData()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun observerData() {
        currentUid = FBAuth.getUid()
        viewModel.getNoteData(currentUid).observe(viewLifecycleOwner, Observer {
            rvAdapter.setListData(it)
            rvAdapter.notifyDataSetChanged()
        })
    }
}