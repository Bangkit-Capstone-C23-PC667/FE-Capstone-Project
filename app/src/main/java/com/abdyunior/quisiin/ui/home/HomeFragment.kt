package com.abdyunior.quisiin.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.abdyunior.quisiin.data.response.DataItemKuesioner
import com.abdyunior.quisiin.data.store.DataStorePreferences
import com.abdyunior.quisiin.databinding.FragmentHomeBinding
import com.abdyunior.quisiin.ui.home.create.CreateQuizActivity
import com.abdyunior.quisiin.ui.home.myquiz.MyQuizDetailActivity
import com.abdyunior.quisiin.ui.profile.edit.EditProfileActivity
import com.abdyunior.quisiin.ui.quiz.fillquiz.QuizFillActivity
import com.abdyunior.quisiin.ui.welcome.WelcomeActivity
import com.abdyunior.quisiin.utils.ViewModelFactory

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "User")

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var Adapter: MyQuizAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeViewModel =
            ViewModelProvider(
                this,
                ViewModelFactory(
                    DataStorePreferences.getInstance(requireContext().dataStore),
                    requireContext()
                )
            )[HomeViewModel::class.java]

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        /*val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }*/
        Adapter = MyQuizAdapter(this)
        binding.apply {
            MyQuiz.adapter = Adapter
            MyQuiz.layoutManager = LinearLayoutManager(requireContext())
            MyQuiz.setHasFixedSize(true)
        }

        homeViewModel.getUser().observe(viewLifecycleOwner) {
            homeViewModel.getAllKuesionerByUser("Bearer ${it.token}")
            homeViewModel.kuesionerList.observe(viewLifecycleOwner) { kuesioner ->
                Adapter.submitList(kuesioner)
            }
        }

        binding.btnCompleteProfile.setOnClickListener {
            startActivity(Intent(requireContext(), EditProfileActivity::class.java))
        }

        binding.btnCreatedQuiz.setOnClickListener {
            startActivity(Intent(requireContext(), CreateQuizActivity::class.java))
        }

        return root
    }

    fun onKuesionerItemClick(kuesioner: DataItemKuesioner) {
        /*startActivity(Intent(requireContext(), MyQuizDetailActivity::class.java).apply {
            putExtra("kuesionerId", kuesionerId)
        })
    }*/
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}