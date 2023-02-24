package com.swkim.weight_timer.Preset

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import com.swkim.weight_timer.StartDisplay
import com.swkim.weight_timer.databinding.ActivityPresetPopupBinding

class PresetPopup : AppCompatActivity() {
    private lateinit var binding : ActivityPresetPopupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        val startIntent = Intent(this@PresetPopup, StartDisplay::class.java)
        binding = ActivityPresetPopupBinding.inflate(layoutInflater)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.noBtn.setOnClickListener {
            finish()
        }

        binding.yesBtn.setOnClickListener {
            if (binding.setInput.toString() != "" && binding.restInput.toString() != "" &&
                    binding.inputName.toString() != "") {
                startIntent.putExtra("setNum", binding.setInput.toString())
                startIntent.putExtra("restNum", binding.restInput.toString())
                startIntent.putExtra("restNum", binding.inputName.toString())
            }
        }


    }
}