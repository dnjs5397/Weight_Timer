package com.swkim.weight_timer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.swkim.weight_timer.Calendar.Calendar
import com.swkim.weight_timer.Preset.Preset
import com.swkim.weight_timer.Preset.PresetPopup
import com.swkim.weight_timer.Timer.MainActivity
import com.swkim.weight_timer.databinding.ActivityStartDisplayBinding

class StartDisplay : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter : RecyclerView.Adapter<*>
    private lateinit var viewManager : RecyclerView.LayoutManager

    private val presetData = arrayListOf<Preset>()

    var set = 0
    var rest = 0
    var backPressedTime : Long = 0
    private lateinit var binding : ActivityStartDisplayBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityStartDisplayBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val presetIntent = Intent(this@StartDisplay, PresetPopup::class.java)
        val mainIntent = Intent(this@StartDisplay, MainActivity::class.java)
        val intentCalendar = Intent(this@StartDisplay, Calendar::class.java)
        // 테스트 데이터
//        presetData.add(Preset("등운동", "5", "60"))
//        presetData.add(Preset("가슴", "5", "70"))
        class PresetAdapter(private val dataSet: List<Preset>) :
            RecyclerView.Adapter<PresetAdapter.PresetViewHolder>() {
            inner class PresetViewHolder(view: View) : RecyclerView.ViewHolder(view)
            override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): PresetViewHolder {
                val view = LayoutInflater.from(viewGroup.context)
                    .inflate(R.layout.preset_recyclerview, viewGroup, false)

                return PresetViewHolder(view)
            }
            override fun onBindViewHolder(presetViewHolder: PresetViewHolder, position: Int) {

                val weightName = presetViewHolder.itemView.findViewById<TextView>(R.id.weight_name)
                val setNum = presetViewHolder.itemView.findViewById<TextView>(R.id.weight_setNum)
                val setRest = presetViewHolder.itemView.findViewById<TextView>(R.id.weight_restNum)
                weightName.text = dataSet[position].name
                setNum.text = dataSet[position].setNum
                setRest.text = dataSet[position].setRest

            }
            override fun getItemCount() = dataSet.size

        }

        viewManager = LinearLayoutManager(this)
        viewAdapter = PresetAdapter(presetData)
        recyclerView = findViewById<RecyclerView>(R.id.presetRecycler).apply {
            layoutManager = viewManager
            adapter = viewAdapter
        }

        binding.addPreset.setOnClickListener {
            startActivity(Intent(this@StartDisplay, PresetPopup::class.java))
            val setNum : String? = presetIntent.getStringExtra("setNum")
            val setRest : String? = presetIntent.getStringExtra("setRest")
            val setName : String? = presetIntent.getStringExtra("inputName")

            presetData.add(Preset(setName.toString(), setNum.toString(), setRest.toString()))
        }



        binding.calendarButton.setOnClickListener {
            startActivity(intentCalendar)
        }

        binding.weightStart.setOnClickListener {
            if (binding.setCount.text == "0" || binding.restCount.text == "0") {
                Toast.makeText(this, "올바른 세트 수 또는 휴식 시간을 입력 하세요.", Toast.LENGTH_SHORT).show()
            }
            else {
                val goalSet: String = binding.setCount.text.toString()
                val restSet: String = binding.restCount.text.toString()
                mainIntent.putExtra("goal_set", goalSet)
                mainIntent.putExtra("rest_time", restSet)
                startActivity(mainIntent)
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

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        if (System.currentTimeMillis() - backPressedTime < 2000) {
            finish()
            return
        }
        Toast.makeText(this, "'뒤로' 버튼을 한번 더 누르시면 앱이 종료됩니다.", Toast.LENGTH_SHORT).show()
        backPressedTime = System.currentTimeMillis()
    }
}