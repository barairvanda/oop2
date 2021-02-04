package com.example.parkiranmall.Fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.parkiranmall.Data.Parkir
import com.example.parkiranmall.R
import kotlinx.android.synthetic.main.custom_row.view.*

class ListParkirAdapter: RecyclerView.Adapter<ListParkirAdapter.MyViewHolder>() {

    private var parkirList = emptyList<Parkir>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_row, parent, false))
    }

    override fun getItemCount(): Int {
        return parkirList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = parkirList[position]
        holder.itemView.hasil_nomor.text = currentItem.nomor_kendaraan
        holder.itemView.hasil_jenis.text = currentItem.jenis_kendaraan
        holder.itemView.hasil_masuk.text = currentItem.masuk
        holder.itemView.hasil_keluar.text = currentItem.keluar

        holder.itemView.rowParkir.setOnClickListener {
            val action = ListParkirDirections.actionListParkirToUpdateParkir(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    fun setData(parkir: List<Parkir>){
        this.parkirList = parkir
        notifyDataSetChanged()
    }
}