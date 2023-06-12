package com.abdyunior.quisiin.ui.home.myquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.abdyunior.quisiin.databinding.ActivityMyQuizDetailBinding

class MyQuizDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMyQuizDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyQuizDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}