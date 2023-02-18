package com.swkim.weight_timer.DayDecorator

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.text.style.StyleSpan
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import com.prolificinteractive.materialcalendarview.spans.DotSpan
import java.util.*


class TodayDecorator : DayViewDecorator{
    private var date : CalendarDay = CalendarDay.today()
    override fun shouldDecorate(day: CalendarDay): Boolean {
        return date != null && day == date
    }

    override fun decorate(view: DayViewFacade) {
        view.addSpan(StyleSpan(Typeface.BOLD))
        view.addSpan(RelativeSizeSpan(1.5f))
    }

    fun setDate(date: Date?) {
        this.date = CalendarDay.from(date)
    }
}