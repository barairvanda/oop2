package com.example.parkiranmall.Data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ParkirDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addParkir(parkir: Parkir)

    @Query("SELECT * FROM parkir_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<Parkir>>

    @Update
    suspend fun updateParkir(parkir: Parkir)

    @Delete
    suspend fun deleteParkir(parkir: Parkir)

    @Query("DELETE FROM parkir_table")
    suspend fun deleteAllParkir()

}