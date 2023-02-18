package com.swkim.weight_timer.Preset

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "preset")
data class PresetEntity(
             @PrimaryKey(autoGenerate = true)
             var id : Long?,
             var name : String,
             var setNum : Int,
             var restNum : Int)