package com.sumeyra.noteapp.presentation

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.sumeyra.noteapp.model.NoteModel
import com.sumeyra.noteapp.database.DB
import com.sumeyra.noteapp.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var date = ""
    private lateinit var db: DB
    private var ls = listOf<NoteModel>()
    private val adapter by lazy { NoteAdapter(onNoteClick = ::onNoteClick) }

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = DB(this)
        binding.recyclerview.adapter = adapter
        updateList()


        with(binding) {

            etDate.setOnTouchListener { _, event ->
                if (event.action == MotionEvent.ACTION_UP) {
                    val now = Calendar.getInstance()
                    val datePicker = DatePickerDialog(
                        this@MainActivity,
                        DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                            val selectedDate = Calendar.getInstance()
                            selectedDate.set(Calendar.YEAR, year)
                            selectedDate.set(Calendar.MONTH, monthOfYear)
                            selectedDate.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                             date = SimpleDateFormat(
                                "dd.MM.yyyy",
                                Locale.getDefault()
                            ).format(selectedDate.time)
                            etDate.setText(date)
                        },
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH)
                    )
                    datePicker.show()
                }
                true
            }

            btnSubmit.setOnClickListener {
                val title = binding.etTitle.text.toString()
                val detail = binding.etDetail.text.toString()

                if ( date != "" && title != "" && detail != "" ) {
                    db.addNote(title, detail, date)
                    updateList()
                    doItEmpty()
                }else {
                    Toast.makeText(this@MainActivity, "Plase fill in the blanks!", Toast.LENGTH_LONG).show()
                }

            }
        }
    }


    private fun doItEmpty() {
         binding.etDate.setText(" ")
         binding.etTitle.setText(" ")
         binding.etDetail.setText(" ")
     }

    private fun onNoteClick(note: NoteModel){
        val intent = Intent(this@MainActivity, DetailActivity::class.java)
        intent.putExtra("note",note)
        startActivity(intent)
    }

    private fun updateList(){
        ls = db.allNote()
        adapter.updateList(ls)
    }
}