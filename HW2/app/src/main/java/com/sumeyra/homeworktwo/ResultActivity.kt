package com.sumeyra.homeworktwo

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.sumeyra.homeworktwo.MainActivity.Companion.nameArray
import com.sumeyra.homeworktwo.MainActivity.Companion.surnameArray

class ResultActivity : AppCompatActivity() {

    private lateinit var nameListView: ListView
    private lateinit var surnameListView: ListView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

         nameListView = findViewById(R.id.nameListView)
         surnameListView = findViewById(R.id.surnameListView)


        val adapterName = ArrayAdapter(this, android.R.layout.simple_list_item_1, nameArray)
        nameListView.adapter = adapterName

        val adapterSurname = ArrayAdapter(this, android.R.layout.simple_list_item_1, surnameArray)
        surnameListView.adapter = adapterSurname

        adapterSurname.notifyDataSetChanged()
        adapterName.notifyDataSetChanged()


        // click
        nameListView.setOnItemClickListener { adapterView, view, i, l ->
            Toast.makeText(this, nameArray[i],Toast.LENGTH_SHORT).show()
        }

        surnameListView.setOnItemClickListener { adapterView, view, i, l ->
            Toast.makeText(this, surnameArray[i],Toast.LENGTH_SHORT).show()
        }
    }
}