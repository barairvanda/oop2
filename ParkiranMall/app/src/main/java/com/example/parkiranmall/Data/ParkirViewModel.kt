package com.example.parkiranmall.Data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ParkirViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<Parkir>>
    private val repository: ParkirRepository

    init {
        val parkirDao = ParkirDatabase.getDatabase(application).parkirDao()
        repository = ParkirRepository(parkirDao)
        readAllData = repository.readAllData
    }

    fun addParkir(parkir: Parkir) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addParkir(parkir)
        }
    }

    fun updateParkir(parkir: Parkir) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateParkir(parkir)
        }
    }

    fun deleteParkir(parkir: Parkir){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteParkir(parkir)
        }
    }

    fun deleteAllParkir(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllParkir()
        }
    }
}