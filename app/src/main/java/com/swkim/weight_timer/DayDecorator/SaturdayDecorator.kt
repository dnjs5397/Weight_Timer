package com.swkim.weight_timer.DayDecorator

import android.graphics.Color
import android.text.style.ForegroundColorSpan
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade
import java.util.Calendar

class SaturdayDecorator : DayViewDecorator{
    private val calendar = Calendar.getInstance()

    override fun shouldDecorate(day: CalendarDay?): Boolean {
        day?.copyTo(calendar)
        val weekday =  calendar.get(Calendar.DAY_OF_WEEK)
        return weekday == Calendar.SATURDAY
    }

    override fun decorate(view: DayViewFacade?) {
        view?.addSpan(ForegroundColorSpan(Color.BLUE))
    }
}