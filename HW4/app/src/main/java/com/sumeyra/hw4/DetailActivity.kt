package com.sumeyra.hw4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import com.sumeyra.hw4.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var webView: WebView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = getIntent()
        val message = intent.getStringExtra("message")

        webView = binding.webView

        val webSettings = webView.settings
        webSettings.javaScriptEnabled = true

        message?.let { webView.loadUrl(it) }



    }
}