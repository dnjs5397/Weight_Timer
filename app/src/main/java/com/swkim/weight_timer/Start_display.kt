package com.swkim.weight_timer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.swkim.weight_timer.databinding.ActivityStartDisplayBinding

class Start_display : AppCompatActivity() {
    var set = 0
    var rest = 0
    var backPressedTime : Long = 0
    private lateinit var binding : ActivityStartDisplayBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityStartDisplayBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val intent = Intent(this@Start_display, MainActivity::class.java)
        val intentCalendar = Intent(this@Start_display, Calendar::class.java)

        binding.calendarButton.setOnClickListener {
            startActivity(intentCalendar)
        }

        binding.weightStart.setOnClickListener {
            if (binding.setCount.text =="0" || binding.restCount.text == "0") {
                Toast.makeText(this, "올바른 세트 수 또는 휴식 시간을 입력하세요.", Toast.LENGTH_SHORT).show()
            }
            else {
                var goalSet: String = binding.setCount.text.toString()
                var restSet: String = binding.restCount.text.toString()
                intent.putExtra("goal_set", goalSet)
                intent.putExtra("rest_time", restSet)
                startActivity(intent)
            }
        }

        binding.setUpButton.setOnClickListener{
            set += 1
            binding.setCount.text = set.toString()
        }

        binding.setDownButton.setOnClickListener{
            if (set != 0) {
                set -= 1
                binding.setCount.text = set.toString()
            }
        }

        binding.restUpButton.setOnClickListener{
            rest += 10
            binding.restCount.text = rest.toString()
        }

        binding.restDownButton.setOnClickListener{
            if (rest != 0) {
                rest -= 10
                binding.restCount.text = rest.toString()
            }
        }

    }

    override fun onBackPressed() {
        if (System.currentTimeMillis() - backPressedTime < 2000) {
            finish()
            return
        }
        Toast.makeText(this, "'뒤로' 버튼을 한번 더 누르시면 앱이 종료됩니다.", Toast.LENGTH_SHORT).show()
        backPressedTime = System.currentTimeMillis()
    }
}