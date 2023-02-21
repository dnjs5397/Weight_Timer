package com.swkim.weight_timer.Preset

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.swkim.weight_timer.databinding.ActivityPresetPopupBinding

class PresetPopup : AppCompatActivity() {
    private lateinit var binding : ActivityPresetPopupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityPresetPopupBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


    }
}