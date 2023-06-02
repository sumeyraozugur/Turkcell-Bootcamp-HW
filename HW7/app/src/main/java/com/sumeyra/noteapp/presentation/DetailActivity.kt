package com.sumeyra.noteapp.presentation

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sumeyra.noteapp.model.NoteModel
import com.sumeyra.noteapp.database.DB
import com.sumeyra.noteapp.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    lateinit var db: DB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)



        db = DB(this)

        val intent = intent
        val note =if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("note", NoteModel::class.java)
        } else {
            intent.getParcelableExtra<NoteModel>("note")
        }

        if (note != null) {
            setDetailData(note)
        }

        binding.ivBack.setOnClickListener {
            goToMain()
        }


        binding.btnDelete.setOnClickListener {
            if (note != null) {
                db.deleteNote(note.id)
                goToMain()
            }
        }
    }
    private fun setDetailData(note: NoteModel){
        with(binding){
            tvTitle.text = note.title
            tvDetail.text =note.detail
            tvDate.text= note.date
        }
    }

    private fun goToMain(){
        val intent  =Intent(this@DetailActivity, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

}