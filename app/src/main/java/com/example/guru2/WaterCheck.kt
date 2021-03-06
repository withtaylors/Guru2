package com.example.guru2

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import com.google.android.material.floatingactionbutton.FloatingActionButton

class WaterCheck : AppCompatActivity() {
    lateinit var WaterDBManger: WaterDBManger
    lateinit var sqlitedb: SQLiteDatabase
    lateinit var layout: LinearLayout
    lateinit var scrollWater: ScrollView
    lateinit var bgChoice:ImageView
    lateinit var watertext:TextView
    lateinit var textView8:TextView
    lateinit var addButton:FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_water_check2)

        WaterDBManger = WaterDBManger(this, "waterlist", null, 1 )
        sqlitedb = WaterDBManger.readableDatabase

        layout = findViewById(R.id.waterlist)
        scrollWater = findViewById(R.id.scrollWater)
        bgChoice = findViewById(R.id.bgChoice)
        watertext = findViewById(R.id.watertext)
        textView8 = findViewById(R.id.textView8)
        addButton = findViewById(R.id.addButton)

        var cursor: Cursor
        cursor = sqlitedb.rawQuery("SELECT * FROM waterlist", null)

        var num: Int = 0
        while (cursor.moveToNext()){
            var str_year = cursor.getString(cursor.getColumnIndex("year")).toString()
            var str_month = cursor.getString(cursor.getColumnIndex("month")).toString()
            var str_date = cursor.getString(cursor.getColumnIndex("date")).toString()
            var str_waterml = cursor.getString(cursor.getColumnIndex("water")).toString()

            var layout_item: LinearLayout = LinearLayout(this)
            layout_item.orientation = LinearLayout.VERTICAL
            layout_item.id = num

            var tvCalView: TextView = TextView(this)
            tvCalView.text = str_year + "???" + str_month + "???" + str_date + "??? ?????????"
            tvCalView.textSize = 20F
//                tvCalView.setBackgroundColor(Color.parseColor("#8DE4FF"))
            tvCalView.setBackgroundColor(Color.parseColor("#FAF2EE"))
            tvCalView.setTextColor(Color.BLACK)
            layout_item.addView(tvCalView)

            var tvWater: TextView = TextView(this)
            tvWater.text = str_waterml+"ml"
            tvWater.textSize = 20F
            tvWater.setBackgroundColor(Color.WHITE)
            tvWater.setTextColor(Color.GRAY)
            layout_item.addView(tvWater)

            addButton.setOnClickListener {
                val intent = Intent(this, WaterEdit::class.java)
                startActivity(intent)
            }

            layout_item.setOnClickListener{
                val intent = Intent(this, WaterDelete::class.java)
                intent.putExtra("year", str_year)
                intent.putExtra("month", str_month)
                intent.putExtra("date", str_date)
                intent.putExtra("water", str_waterml)
                startActivity(intent)
            }
            layout.addView(layout_item)
            num++
        }
        cursor.close()
        sqlitedb.close()
        WaterDBManger.close()
    }
    //????????? ??????
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return true
    }

    //????????? ?????? ??????
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.main->{
                val mainIntent= Intent(this,MainActivity::class.java)
                startActivity(mainIntent)
            }
            R.id.sport->{
                val sportIntent=Intent(this,HealthListActivity::class.java)
                startActivity(sportIntent)
            }
            R.id.meal->{
                val mealIntent=Intent(this,MealWriteActivity::class.java)
                startActivity(mealIntent)
            }
            R.id.water->{
                val waterIntent=Intent(this,WaterCheck::class.java)
                startActivity(waterIntent)
            }
            R.id.mental->{
                val mentalIntent=Intent(this,MentalDailyActivity::class.java)
                startActivity(mentalIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
