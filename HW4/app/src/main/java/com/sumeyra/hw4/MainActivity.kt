package com.sumeyra.hw4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.sumeyra.hw4.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val adapter by lazy { MainAdapter(onNewsClick = ::onNewsClick) } //onNewsClick = ::onNewsClick
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        with(binding){
            val result = Result()
            recyclerview.adapter = adapter

            val handler = Handler(Looper.getMainLooper())
            Thread {
                val result = Result()
                val list = result.news()

                handler.post {
                    // Update the UI on the main thread
                    adapter.submitList(list)
                }
            }.start()
        }

    }

    private fun onNewsClick(href: String) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("message", href)
        startActivity(intent)
    }
}