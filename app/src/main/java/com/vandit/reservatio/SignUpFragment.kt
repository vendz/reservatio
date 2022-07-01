package com.vandit.reservatio

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.google.firebase.database.FirebaseDatabase
import com.vandit.reservatio.databinding.FragmentSignUpBinding


class SignUpFragment : Fragment() {
    lateinit var binding:FragmentSignUpBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_up, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
        val flag = sharedPref.getString("category", null)
        if(flag != null){
            fragmentManager?.beginTransaction()?.replace(R.id.nav_host_fragment, ScanFragment())?.commit()
        }

        val items = resources.getStringArray(R.array.signUpArray)
        val spinnerAdapter= activity?.applicationContext?.let { ArrayAdapter(it, R.layout.spinner_item, items) }

        binding.apply {
            var category:String? = null

            categoryDD.adapter = spinnerAdapter
            categoryDD.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    category = items[p2]
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    Log.d("TAG", "onNothingSelected: ")
                }
            }

            submitBTN.setOnClickListener {
                with(sharedPref.edit()){
                    putString("name", nameET.text.toString())
                    putString("category", category)
                    apply()
                }

                fragmentManager?.beginTransaction()?.replace(R.id.nav_host_fragment, ScanFragment())?.commit()
            }
        }
    }
}