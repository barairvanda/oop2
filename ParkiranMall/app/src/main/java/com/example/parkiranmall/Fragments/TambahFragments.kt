package com.example.parkiranmall.Fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.parkiranmall.Data.Parkir
import com.example.parkiranmall.Data.ParkirViewModel
import com.example.parkiranmall.R
import kotlinx.android.synthetic.main.fragment_tambah_fragments.*
import kotlinx.android.synthetic.main.fragment_tambah_fragments.view.*

class TambahFragments : Fragment() {

    private lateinit var mParkirViewModel: ParkirViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_tambah_fragments, container, false)

        mParkirViewModel = ViewModelProvider(this).get(ParkirViewModel::class.java)

        view.btn_add.setOnClickListener {
            insertDataToDatabase()
        }

        return view
    }

    private fun insertDataToDatabase() {
        val nomor = txt_kendaraan.text.toString()
        val jenis = txt_jenis.text.toString()
        val masuk = txt_masuk.text.toString()
        val keluar = txt_keluar.text.toString()


        if(inputCheck(nomor, jenis, masuk, keluar)){
            // Create User Object
            val parkir = Parkir(0, nomor, jenis, masuk, keluar)
            // Add Data to Database
            mParkirViewModel.addParkir(parkir)
            Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_LONG).show()
            // Navigate Back
            findNavController().navigate(R.id.action_tambahFragments_to_listParkir)
        }else{
            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(nomor: String, jenis: String, masuk: String, keluar: String): Boolean{
        return !(TextUtils.isEmpty(nomor) && TextUtils.isEmpty(jenis) && TextUtils.isEmpty(masuk) && TextUtils.isEmpty(keluar))
    }

}