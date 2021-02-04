package com.example.parkiranmall.Fragments

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavArgs
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.parkiranmall.Data.Parkir
import com.example.parkiranmall.Data.ParkirViewModel
import com.example.parkiranmall.R
import kotlinx.android.synthetic.main.fragment_tambah_fragments.*
import kotlinx.android.synthetic.main.fragment_update_parkir.*
import kotlinx.android.synthetic.main.fragment_update_parkir.view.*

class UpdateParkir : Fragment() {

    private val args by navArgs<UpdateParkirArgs>()

    private lateinit var mParkirViewModel: ParkirViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update_parkir, container, false)

        mParkirViewModel = ViewModelProvider(this).get(ParkirViewModel::class.java)

        view.update_kendaraan.setText(args.currentParkir.nomor_kendaraan)
        view.update_jenis.setText(args.currentParkir.jenis_kendaraan)
        view.update_masuk.setText(args.currentParkir.masuk)
        view.update_keluar.setText(args.currentParkir.keluar)

        view.btn_ubah.setOnClickListener {
            updateItem()
        }

        setHasOptionsMenu(true)
        return view
    }

    private fun updateItem(){
        val nomor = update_kendaraan.text.toString()
        val jenis = update_jenis.text.toString()
        val masuk = update_masuk.text.toString()
        val keluar = update_keluar.text.toString()

        if(inputCheck(nomor, jenis, masuk, keluar)){

            val updatedParkir = Parkir(args.currentParkir.id, nomor, jenis, masuk, keluar)
            // Update Current User
            mParkirViewModel.updateParkir(updatedParkir)
            Toast.makeText(requireContext(), "Updated Successfully!", Toast.LENGTH_SHORT).show()
            // Navigate Back
            findNavController().navigate(R.id.action_updateParkir_to_listParkir)
        }else{
            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(nomor: String, jenis: String, masuk: String, keluar: String): Boolean{
        return !(TextUtils.isEmpty(nomor) && TextUtils.isEmpty(jenis) && TextUtils.isEmpty(masuk) && TextUtils.isEmpty(keluar))
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete) {
            deleteParkir()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteParkir() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            mParkirViewModel.deleteParkir(args.currentParkir)
            Toast.makeText(
                requireContext(),
                "Successfully removed: ${args.currentParkir.nomor_kendaraan}",
                Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateParkir_to_listParkir)
        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle("Delete ${args.currentParkir.nomor_kendaraan}?")
        builder.setMessage("Are you sure you want to delete ${args.currentParkir.nomor_kendaraan}?")
        builder.create().show()
    }
}

