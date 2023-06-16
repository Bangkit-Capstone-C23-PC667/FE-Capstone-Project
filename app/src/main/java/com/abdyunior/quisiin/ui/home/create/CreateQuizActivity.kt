package com.abdyunior.quisiin.ui.home.create

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.abdyunior.quisiin.data.response.CreateKuesionerResponse
import com.abdyunior.quisiin.data.store.DataStorePreferences
import com.abdyunior.quisiin.databinding.ActivityCreateQuizBinding
import com.abdyunior.quisiin.utils.ViewModelFactory
import com.abdyunior.quisiin.utils.reduceFileImage
import com.abdyunior.quisiin.utils.uriToFile
import java.io.File

private val Context.datastore: DataStore<Preferences> by preferencesDataStore(name = "User")

class CreateQuizActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreateQuizBinding
    private var getFile: File? = null
    private lateinit var viewModel: CreateViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(DataStorePreferences.getInstance(datastore), this)
        )[CreateViewModel::class.java]

        binding.apply {
            btnQuizImage.setOnClickListener { startGallery() }
            btnCreate.setOnClickListener { createQuiz() }
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

    private fun createQuiz() {
        if (getFile != null) {
            val link = binding.etInputQuiz.text.toString()
            val file = reduceFileImage(getFile as File)
            val judul = binding.etQuizTitle.text.toString()
            val deskripsi = binding.etDescription.text.toString()
            val rentangUsia = binding.etAge.text.toString().toInt()
            val kategori = binding.etKategori.text.toString().toInt()
            viewModel.getUser().observe(this) {
                viewModel.createKuesioner(
                    "Bearer ${it.token}",
                    link,
                    file,
                    judul,
                    deskripsi,
                    rentangUsia,
                    kategori
                ).observe(this) { CreateKuesionerResponse ->
                    if (CreateKuesionerResponse != null) {
                        Toast.makeText(this, "Create Kuesioner Berhasil", Toast.LENGTH_SHORT).show()
                        finish()
                    } else {
                        Toast.makeText(this, "Create Kuesioner Gagal", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            viewModel.isLoading.observe(this) {
                //
            }
        } else {
            Toast.makeText(this, "Gambar Belum Dipilih", Toast.LENGTH_SHORT).show()
        }
    }
}