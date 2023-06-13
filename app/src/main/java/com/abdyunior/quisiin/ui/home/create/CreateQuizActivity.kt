package com.abdyunior.quisiin.ui.home.create

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import com.abdyunior.quisiin.databinding.ActivityCreateQuizBinding
import com.abdyunior.quisiin.utils.uriToFile
import java.io.File

class CreateQuizActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreateQuizBinding
    private var getFile: File? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            btnQuizImage.setOnClickListener { startGallery() }
        }
    }
    private fun startGallery() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        val chooser = Intent.createChooser(intent, "Select Image")
        launcherIntentGallery.launch(chooser)
    }

    private val launcherIntentGallery =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val selectedImage = result.data?.data as Uri
                selectedImage.let { uri ->
                    val myFile = uriToFile(uri, this@CreateQuizActivity)
                    getFile = myFile
                    binding.tvImageName.text = myFile.name
                }
            }
        }
}