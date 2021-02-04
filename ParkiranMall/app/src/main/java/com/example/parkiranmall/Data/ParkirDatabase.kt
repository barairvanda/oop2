package com.example.parkiranmall.Data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Parkir::class], version = 1, exportSchema = false)
abstract class ParkirDatabase : RoomDatabase() {

    abstract fun parkirDao(): ParkirDao

    companion object {
        @Volatile
        private var INSTANCE: ParkirDatabase? = null

        fun getDatabase(context: Context): ParkirDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ParkirDatabase::class.java,
                    "parkir_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
    }


