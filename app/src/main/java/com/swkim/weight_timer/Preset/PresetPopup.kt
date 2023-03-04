package com.swkim.weight_timer.Preset

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.swkim.weight_timer.StartDisplay
import com.swkim.weight_timer.Timer.MainActivity
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
            if (binding.setInput.text.toString() != "" && binding.restInput.text.toString() != "" &&
                    binding.nameInput.text.toString() != "") {

                startIntent.putExtra("setNum", binding.setInput.text.toString())
                startIntent.putExtra("setRest", binding.restInput.text.toString())
                startIntent.putExtra("setName", binding.nameInput.text.toString())

            }
            else {
                Toast.makeText(this, "모든 필드에 값을 입력해주세요.", Toast.LENGTH_SHORT).show()
            }

            finish()
        }


    }
}