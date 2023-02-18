package com.swkim.weight_timer.Calendar

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import com.swkim.weight_timer.DayDecorator.SaturdayDecorator
import com.swkim.weight_timer.DayDecorator.SundayDecorator
import com.swkim.weight_timer.DayDecorator.TodayDecorator
import com.swkim.weight_timer.R
import com.swkim.weight_timer.databinding.ActivityCalendarBinding

class Calendar : AppCompatActivity() {
    lateinit var binding : ActivityCalendarBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        val intent = intent

        binding = ActivityCalendarBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.calendarView.selectedDate = CalendarDay.today()
        val calendar = findViewById<MaterialCalendarView>(R.id.calendar_view)
        calendar.state().edit()
            .setMinimumDate(CalendarDay.from(2023,0,1))
            .setMaximumDate(CalendarDay.from(2030,1,31))
            .commit()

        calendar.addDecorators(
            SundayDecorator(),
            SaturdayDecorator(),
            TodayDecorator(),
        )
    }
}