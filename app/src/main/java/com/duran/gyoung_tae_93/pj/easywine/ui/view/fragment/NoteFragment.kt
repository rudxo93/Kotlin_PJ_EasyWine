package com.duran.gyoung_tae_93.pj.easywine.ui.view.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.duran.gyoung_tae_93.pj.easywine.R
import com.duran.gyoung_tae_93.pj.easywine.databinding.FragmentNoteBinding
import com.duran.gyoung_tae_93.pj.easywine.ui.view.activity.EditNoteActivity

class NoteFragment : Fragment() {

    private lateinit var binding : FragmentNoteBinding

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

        binding.btnNoteEdit.setOnClickListener {
            val intent = Intent(context, EditNoteActivity::class.java)
            startActivity(intent)
        }
    }

}