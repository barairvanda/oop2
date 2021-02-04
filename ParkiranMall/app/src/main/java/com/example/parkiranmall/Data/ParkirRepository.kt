package com.example.parkiranmall.Data

import androidx.lifecycle.LiveData

class ParkirRepository(private val parkirDao: ParkirDao) {

    val readAllData: LiveData<List<Parkir>> = parkirDao.readAllData()

    suspend fun addParkir(parkir: Parkir){
        parkirDao.addParkir(parkir)
    }
    suspend fun updateParkir(parkir: Parkir){
        parkirDao.updateParkir(parkir)
    }
    suspend fun deleteParkir(parkir: Parkir){
        parkirDao.deleteParkir(parkir)
    }

    suspend fun deleteAllParkir(){
        parkirDao.deleteAllParkir()
    }

}