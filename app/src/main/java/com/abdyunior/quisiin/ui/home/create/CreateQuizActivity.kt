package com.abdyunior.quisiin.ui.home.create

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.abdyunior.quisiin.databinding.ActivityCreateQuizBinding

class CreateQuizActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreateQuizBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}