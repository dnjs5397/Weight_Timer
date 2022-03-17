package com.swkim.weight_timer

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.view.*
import android.widget.Button
import android.widget.Chronometer
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.swkim.weight_timer.databinding.ActivityMainBinding
import java.sql.Time
import java.util.*
import kotlin.concurrent.timer

class MainActivity : AppCompatActivity() {
    private var complete = false
    private var time = 0
    private var isRunning = false
    private var timeTask: Timer? = null
    private var timerTask : Timer? = null
    private var isResting = false
    var set : Int = 0
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        val doneIntent = Intent(this@MainActivity, Calendar::class.java)
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val intent = intent
        binding.goalSet.text = intent.getStringExtra("goal_set")
        var restTime = intent.getStringExtra("rest_time")

        start()
        isRunning = !isRunning

        binding.startButton.setOnClickListener {
            if (!complete) {
                isRunning = !isRunning
                if (isRunning)
                    start()
                else
                    pause()
            }
        }

        // 홈 버튼 누르면 경고창 출력
        binding.resetButton.setOnClickListener {
            if (!complete) {
                val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_design, null)
                val builder = AlertDialog.Builder(this)
                    .setView(dialogView)

                val mAlertDialog = builder.show()

                val okBtn = dialogView.findViewById<Button>(R.id.yes_btn)
                okBtn.setOnClickListener {
                    mAlertDialog.dismiss()
                    finish()
                }
                val noBtn = dialogView.findViewById<Button>(R.id.no_btn)
                noBtn.setOnClickListener {
                    mAlertDialog.dismiss()
                }
            }
            else {
                finish()
            }
        }

        binding.restButton.setOnClickListener {
            // 휴식중이 아니고 + 타이머가 돌아가고있고 + 목표 세트에 도달하지 않았고 + 운동이 끝나지 않았을 때 실행
            if (!isResting && isRunning && set != Integer.parseInt(binding.goalSet.text.toString()) && !complete) {
                isResting = !isResting
                var goalset = Integer.parseInt(binding.goalSet.text.toString())
                var restcount = Integer.parseInt(restTime)
                set += 1
                restcount += 1
                if (set == goalset) {
                    binding.leftRest.text = ""
                    timeTask?.cancel()
                    timerTask?.cancel()
                    binding.weighInfo.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 22F)
                    binding.goingSet.text = set.toString()
                    binding.weighInfo.text = "수고하셨습니다!\n목표한 세트 수에 도달했습니다."
                    isRunning = false
                    isResting = true
                    complete = true
                    doneIntent.putExtra("isDone", complete)
                }

                else {
                    binding.leftRest.text = "남은 휴식 시간"
                    binding.weighInfo.text = restcount.toString()
                    binding.goingSet.text = set.toString()
                    timerTask = timer(period = 1000) {
                        if (restcount == 0) {
                            runOnUiThread {
                                binding.leftRest.text = ""
                                binding.weighInfo.text = "운동 중.."
                                isResting = !isResting
                            }
                            this.cancel()
                        }
                        else {
                            runOnUiThread {
                                restcount -= 1
                                binding.weighInfo.text = "$restcount"
                            }
                        }
                    }
                }
            }
        }


    }

    override fun onBackPressed() {
        if (!complete) {
            val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_design, null)
            val builder = AlertDialog.Builder(this)
                .setView(dialogView)

            val mAlertDialog = builder.show()

            val okBtn = dialogView.findViewById<Button>(R.id.yes_btn)
            okBtn.setOnClickListener {
                mAlertDialog.dismiss()
                finish()
            }
            val noBtn = dialogView.findViewById<Button>(R.id.no_btn)
            noBtn.setOnClickListener {
                mAlertDialog.dismiss()
            }
        }
        else {
            finish()
        }
    }

    private fun start() {
        binding.startButton.setImageResource(R.drawable.ic_baseline_pause_24)
        if (binding.weighInfo.text == "일시정지") {
            binding.weighInfo.text = "운동 중.."
        }
        timeTask = timer(period = 10) {
            time++
            val min = (time / 6000) % 60
            val sec = (time / 100) % 60
            val ms = time % 100
            runOnUiThread {
                if (min<10) { // 분
                    binding.minutes.text = "0$min"
                }
                else {
                    binding.minutes.text = "$min"
                }
                if (sec < 10) { // 초
                    binding.seconds.text = "0$sec"
                } else {
                    binding.seconds.text = "$sec"
                }

                if (ms < 10){ // m초
                    binding.mSeconds.text = "0$ms"
                }else {
                    binding.mSeconds.text = "$ms"
                }


            }

        }

    }

    private fun pause() {
        if (isResting) {
            binding.leftRest.text = ""
            binding.startButton.setImageResource(R.drawable.ic_baseline_play_arrow_24)
            timeTask?.cancel() // 실행중인 타이머가 있다면 취소
            timerTask?.cancel()
            isResting = false
            binding.weighInfo.text = "일시정지"
        }
        else {
            binding.startButton.setImageResource(R.drawable.ic_baseline_play_arrow_24)
            timeTask?.cancel() // 실행중인 타이머가 있다면 취소
            timerTask?.cancel()
            isResting = false
            binding.weighInfo.text = "일시정지"
        }
    }
}


