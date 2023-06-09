package com.abdyunior.quisiin.ui.quiz.fillquiz

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import com.abdyunior.quisiin.data.response.DataItem
import com.abdyunior.quisiin.databinding.ActivityQuizFillBinding

class QuizFillActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_KUESIONER = "extra_kuesioner"
    }

    private lateinit var webView: WebView
    private lateinit var binding: ActivityQuizFillBinding

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizFillBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        webView = binding.webView
        webView.settings.javaScriptEnabled = true
        webView.webViewClient = WebViewClient()

        val dataKuesioner = intent.getParcelableExtra<DataItem>(EXTRA_KUESIONER)
        /*val formUrl =
            "https://docs.google.com/forms/d/1jH_t60tsaBJ0ghiQuKdbnB5JxKkOK3zF8EF-1xmP3RQ/edit?usp=drive_web"*/
        val formUrl = dataKuesioner?.link.toString()
        Log.d("link", formUrl)

        webView.loadUrl(formUrl)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}