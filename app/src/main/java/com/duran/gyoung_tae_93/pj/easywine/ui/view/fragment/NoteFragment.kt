package com.duran.gyoung_tae_93.pj.easywine.ui.view.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.duran.gyoung_tae_93.pj.easywine.R
import com.duran.gyoung_tae_93.pj.easywine.data.model.NoteInfoModel
import com.duran.gyoung_tae_93.pj.easywine.databinding.FragmentNoteBinding
import com.duran.gyoung_tae_93.pj.easywine.ui.adapter.NoteRVAdapter
import com.duran.gyoung_tae_93.pj.easywine.ui.view.activity.EditNoteActivity
import com.duran.gyoung_tae_93.pj.easywine.ui.viewmodel.NoteViewModel

class NoteFragment : Fragment() {

    private lateinit var binding : FragmentNoteBinding

    lateinit var rvAdapter : NoteRVAdapter
    private val viewModel by lazy { ViewModelProvider(this).get(NoteViewModel::class.java) }

    private val TAG = NoteFragment::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_note, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initMoveNoteEdit()
        getRVSetting()

    }

    private fun getRVSetting() {
        val rv : RecyclerView = binding.rvWineNote

        rvAdapter = NoteRVAdapter(requireContext())
        rv.adapter = rvAdapter
        rv.layoutManager = LinearLayoutManager(context)
        observerData()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun observerData() {
        viewModel.getNoteData().observe(viewLifecycleOwner, Observer {
            rvAdapter.setListData(it)
            rvAdapter.notifyDataSetChanged()
        })
    }

    private fun observerStorage() {

    }

    private fun initMoveNoteEdit() {
        binding.btnNoteEdit.setOnClickListener {
            val intent = Intent(context, EditNoteActivity::class.java)
            startActivity(intent)
        }
    }

}