package com.reto2.pokes.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.reto2.pokes.R
import com.reto2.pokes.databinding.FragmentDialogBinding

class Dialog:DialogFragment() {

    private lateinit var binding:FragmentDialogBinding

    var title:String = ""
    var content:String = ""

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentDialogBinding.inflate(layoutInflater,container,false)
        // Inflate the layout for this fragment
        binding.titleMsg.text = title
        binding.contentMsg.text = content

        return binding.root
    }

    companion object {

        @JvmStatic
        fun newInstance() = Dialog()

    }
}