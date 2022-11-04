package com.duran.gyoung_tae_93.pj.easywine.ui.view.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
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
import com.duran.gyoung_tae_93.pj.easywine.databinding.FragmentNoteBinding
import com.duran.gyoung_tae_93.pj.easywine.ui.adapter.NoteRVAdapter
import com.duran.gyoung_tae_93.pj.easywine.ui.view.activity.EditNoteActivity
import com.duran.gyoung_tae_93.pj.easywine.ui.viewmodel.NoteViewModel

class NoteFragment : Fragment() {

    private lateinit var binding: FragmentNoteBinding
    private lateinit var viewModel: NoteViewModel
    lateinit var rvAdapter: NoteRVAdapter

    private val TAG = NoteFragment::class.java.simpleName
    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.e("dddddd", "onAttach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("dddddd", "onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_note, container, false)
        Log.e("dddddd", "onCreateView")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e("dddddd", "onViewCreated")

        viewModel =  ViewModelProvider(this)[NoteViewModel::class.java]

        initMoveNoteEdit()
        getRVSetting()
        /*observerData()*/

    }

    /**
     *  Recycler View Setting
     */
    private fun getRVSetting() {
        val rv: RecyclerView = binding.rvWineNote

        rvAdapter = NoteRVAdapter(requireContext())
        rv.adapter = rvAdapter
        rv.layoutManager = LinearLayoutManager(context)
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
        viewModel.getNoteData().observe(viewLifecycleOwner, Observer {
            rvAdapter.setListData(it)
            rvAdapter.notifyDataSetChanged()
        })
    }
}