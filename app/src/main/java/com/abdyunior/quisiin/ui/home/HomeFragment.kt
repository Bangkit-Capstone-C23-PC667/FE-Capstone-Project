package com.abdyunior.quisiin.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.abdyunior.quisiin.databinding.FragmentHomeBinding
import com.abdyunior.quisiin.ui.home.create.CreateQuizActivity
import com.abdyunior.quisiin.ui.home.myquiz.MyQuizDetailActivity
import com.abdyunior.quisiin.ui.quiz.fillquiz.QuizFillActivity
import com.abdyunior.quisiin.ui.welcome.WelcomeActivity

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this)[HomeViewModel::class.java]

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        /*val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }*/

        binding.btnWelcome.setOnClickListener {
            startActivity(Intent(requireContext(), QuizFillActivity::class.java))
        }

        binding.btnCreatedQuiz.setOnClickListener {
            startActivity(Intent(requireContext(), CreateQuizActivity::class.java))
        }

        return binding.root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}