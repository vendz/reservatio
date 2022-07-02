package com.vandit.reservatio

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import com.vandit.reservatio.databinding.FragmentScanBinding
import java.util.*
import kotlin.collections.HashMap

class ScanFragment : Fragment() {
    lateinit var binding: FragmentScanBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_scan, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
        val inQueue = sharedPref.getBoolean("inQueue", false)

        if(inQueue){
            fragmentManager?.beginTransaction()?.replace(R.id.nav_host_fragment, QueueFragment())?.commit()
        }

        binding.apply {
            scanBTN.setOnClickListener{
                fragmentManager?.beginTransaction()?.replace(R.id.nav_host_fragment, CameraFragment())?.commit()
            }
        }
    }
}