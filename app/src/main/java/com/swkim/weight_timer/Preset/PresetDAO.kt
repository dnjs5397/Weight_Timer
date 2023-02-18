package com.swkim.weight_timer.Preset

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query


@Dao
interface PresetDAO {

    @Insert(onConflict = REPLACE) // Primary Key가 같으면 덮어쓰기 한다
    fun insert(preset : PresetEntity)

    @Query("SELECT * FROM preset")
    fun getALL() : List<PresetEntity>

    @Delete
    fun delete(preset : PresetEntity)
}