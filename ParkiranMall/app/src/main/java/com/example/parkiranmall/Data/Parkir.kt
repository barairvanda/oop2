package com.example.parkiranmall.Data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "parkir_table")
data class Parkir(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val nomor_kendaraan: String,
    val jenis_kendaraan: String,
    val masuk: String,
    val keluar: String
): Parcelable