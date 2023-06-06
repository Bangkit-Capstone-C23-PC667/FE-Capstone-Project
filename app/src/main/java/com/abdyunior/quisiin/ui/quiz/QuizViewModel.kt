package com.abdyunior.quisiin.ui.quiz

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class QuizViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is quiz list Fragment"
    }
    val text: LiveData<String> = _text
}