package com.example.guru2

import android.os.Build.VERSION_CODES
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CalendarView
import android.widget.TextView
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_health_rec.*
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class HealthRecActivity : AppCompatActivity() {

    lateinit var calendarView : CalendarView
    lateinit var exec1 : TextView
    lateinit var exec2 : TextView
    lateinit var exec3 : TextView
    lateinit var exec4 : TextView
    lateinit var exec5 : TextView

    @RequiresApi(VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_health_rec)

        calendarView = findViewById<CalendarView>(R.id.calendarView)
        exec1 = findViewById<TextView>(R.id.exe1)
        exec2 = findViewById<TextView>(R.id.exe2)
        exec3 = findViewById<TextView>(R.id.exe3)
        exec4 = findViewById<TextView>(R.id.exe4)
        exec5 = findViewById<TextView>(R.id.exe5)

        var currentTime = LocalDateTime.now();
        val formatter = DateTimeFormatter.ISO_DATE
        val formatted = currentTime.format(formatter)
        val todayText = formatted.toString()

        calendarView.dateTextAppearance = R.style.TextAppearance_AppCompat_Large
        calendarView.weekDayTextAppearance = R.style.TextAppearance_AppCompat_Medium
        calendarView.minDate = SimpleDateFormat("yyyyMMdd").parse("20210101").time
        calendarView.maxDate - SimpleDateFormat("yyyyMMdd").parse("20221231").time

        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            if (month < 9) {
                if (dayOfMonth < 10) {
                    today.text = "" + year + "-" + 0 + (month + 1) + "-" + 0 + dayOfMonth

                } else {
                    today.text = "" + year + "-" + 0 + (month + 1) + "-" + dayOfMonth
                }
            } else {
                if (dayOfMonth < 10) {
                    today.text = "" + year + "-" + (month + 1) + "-" + 0 + dayOfMonth
                } else {
                    today.text = "" + year + "-" + 0 + (month + 1) + "-" + dayOfMonth
                }
            }

            if (today.text == todayText) {

                var exercise_1 = intent.getStringExtra("?????? ??????").toFloat()
                var exercise_2 = intent.getStringExtra("??? ??????").toFloat()
                var exercise_3 = intent.getStringExtra("???????????? ??????").toFloat()
                var exercise_4 = intent.getStringExtra("?????? ??????").toFloat()
                var exercise_5 = intent.getStringExtra("?????? ??????").toFloat()

                exec1.text = "?????? ?????? : " + exercise_1.toInt().toString() +"???"
                exec2.text = "??? ?????? : " + exercise_2.toInt().toString()+"???"
                exec3.text = "???????????? ?????? : " + exercise_3.toInt().toString()+"???"
                exec4.text = "?????? ?????? : " + exercise_4.toInt().toString()+"???"
                exec5.text = "?????? ?????? : " + exercise_5.toInt().toString()+"???"
            }
            else{
                exec1.text = ""
                exec2.text = ""
                exec3.text = ""
                exec4.text = ""
                exec5.text = ""
            }
        }
    }
}