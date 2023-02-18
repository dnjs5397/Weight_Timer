package com.swkim.weight_timer.Preset

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(PresetEntity::class), version = 1)
abstract class PresetDatabase : RoomDatabase() {
    abstract fun presetDAO() : PresetDAO

    companion object {
        var INSTANCE : PresetDatabase? = null

        fun getInstance(context : Context) : PresetDatabase? {
            if (INSTANCE == null) {
                synchronized(PresetDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                                PresetDatabase::class.java, "preset.db")
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE
        }
    }
}