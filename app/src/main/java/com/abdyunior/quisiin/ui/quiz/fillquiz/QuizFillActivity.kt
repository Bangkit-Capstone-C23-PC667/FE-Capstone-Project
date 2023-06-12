package com.abdyunior.quisiin.ui.quiz.fillquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.abdyunior.quisiin.databinding.ActivityQuizFillBinding

class QuizFillActivity : AppCompatActivity() {

    private lateinit var binding: ActivityQuizFillBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizFillBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}