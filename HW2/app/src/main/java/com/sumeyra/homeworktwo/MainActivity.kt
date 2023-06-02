package com.sumeyra.homeworktwo

/*
ListView Homework
Created by Sümeyra Özuğur
 */

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var nameData:EditText
    private lateinit var surnameData:EditText
    private lateinit var btnNameAdd:Button
    private lateinit var btnSurnameAdd:Button
    private lateinit var btnShowResult:Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        nameData = findViewById(R.id.editTextName)
        surnameData = findViewById(R.id.editTextSurname)
        btnNameAdd = findViewById(R.id.btnNameAdd)
        btnSurnameAdd = findViewById(R.id.btnSurnameAdd)
        btnShowResult = findViewById(R.id.btnShowResult)



        btnNameAdd.setOnClickListener {
            val name= nameData.text.toString().trim()
            if(name.isNotEmpty()){
                nameArray.add(nameData.text.toString().trim())
                nameData.setText("")
                nameData.requestFocus() // imleci bu alana getir
            }
            else{
                Toast.makeText(this,"You should enter your name",Toast.LENGTH_SHORT).show()
            }
        }


        btnSurnameAdd.setOnClickListener {
            val surname = surnameData.text.toString().trim()
            if(surname.isNotEmpty()){
                surnameArray.add(surnameData.text.toString())
                surnameData.setText("")
                surnameData.requestFocus() // imleci bu alana getir
            }else{
                Toast.makeText(this,"You should enter your surname",Toast.LENGTH_SHORT).show()
            }

        }

        btnShowResult.setOnClickListener {
            val intent = Intent(this, ResultActivity::class.java)
            startActivity(intent)
        }
    }

    companion object{
         val nameArray = mutableListOf<String>()
         val surnameArray = mutableListOf<String>()
    }
}

