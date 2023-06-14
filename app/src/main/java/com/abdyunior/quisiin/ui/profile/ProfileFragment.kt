package com.abdyunior.quisiin.ui.profile

import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.abdyunior.quisiin.data.response.HistoryRequest
import com.abdyunior.quisiin.data.store.DataStorePreferences
import com.abdyunior.quisiin.databinding.FragmentProfileBinding
import com.abdyunior.quisiin.ui.profile.edit.EditProfileActivity
import com.abdyunior.quisiin.ui.welcome.WelcomeActivity
import com.abdyunior.quisiin.utils.ViewModelFactory
import com.abdyunior.quisiin.utils.uriToFile
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "User")

class ProfileFragment : Fragment() {

    private lateinit var profileViewModel: ProfileViewModel
    private var _binding: FragmentProfileBinding? = null
    private var getFile: File? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var quizHistoryAdapter: QuizHistoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        profileViewModel =
            ViewModelProvider(
                this,
                ViewModelFactory(
                    DataStorePreferences.getInstance(requireContext().dataStore),
                    requireContext()
                )
            )[ProfileViewModel::class.java]

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.btnLogout.setOnClickListener {
            AlertDialog.Builder(requireContext())
                .setTitle("Logout")
                .setMessage("Are you sure want to logout?")
                .setPositiveButton("Yes") { _, _ ->
                    profileViewModel.logout()
                }
                .setNegativeButton("No") { dialog, _ ->
                    dialog.cancel()
                }
                .show()
        }

        profileViewModel.getUser().observe(viewLifecycleOwner) { data ->
            if (data.token.isEmpty()) {
                startActivity(Intent(requireContext(), WelcomeActivity::class.java))
                requireActivity().finish()
            }
        }

        binding.btnEditProfileIcon.setOnClickListener { startGallery() }

        binding.btnEditProfile.setOnClickListener {
            startActivity(Intent(requireContext(), EditProfileActivity::class.java))
        }
        quizHistoryAdapter = QuizHistoryAdapter(ArrayList())
        binding.rvQuizHistory.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = quizHistoryAdapter
        }

        quizHistoryAdapter.setOnItemClickCallback(object : QuizHistoryAdapter.OnItemClickCallback {
            override fun onItemClicked(data: HistoryRequest) {
                /*val intent = Intent(requireContext(), QuizHistoryDetailActivity::class.java)
                intent.putExtra(QuizHistoryDetailActivity.EXTRA_QUIZ_HISTORY, data)
                startActivity(intent)*/
            }
        })
        return root
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
            if (result.resultCode == AppCompatActivity.RESULT_OK) {
                val selectedImage = result.data?.data as Uri
                selectedImage.let { uri ->
                    val myFile = uriToFile(uri, requireContext())
                    getFile = myFile
                    binding.ivProfile.setImageBitmap(BitmapFactory.decodeFile(myFile.path))
                    uploadProfilePicture(myFile)
                }
            }
        }

    private fun uploadProfilePicture(file: File) {
        val token = profileViewModel.getUser().value?.token
        val requestBody = file.asRequestBody("image/*".toMediaTypeOrNull())
        val multipartBody = MultipartBody.Part.createFormData("file", file.name, requestBody)

        if (token != null) {
            profileViewModel.uploadProfilePic(token, multipartBody)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}