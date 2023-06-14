package com.abdyunior.quisiin.ui.quiz

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
import com.abdyunior.quisiin.data.response.DataItem
import com.abdyunior.quisiin.data.store.DataStorePreferences
import com.abdyunior.quisiin.databinding.FragmentQuizBinding
import com.abdyunior.quisiin.ui.quiz.fillquiz.QuizFillActivity
import com.abdyunior.quisiin.utils.ViewModelFactory

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "User")

class QuizFragment : Fragment() {

    private var _binding: FragmentQuizBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var quizViewModel: QuizViewModel
    private lateinit var quizAdapter: QuizAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        /*val quizViewModel =
            ViewModelProvider(
                this,
                ViewModelFactory(
                    DataStorePreferences.getInstance(requireContext().dataStore),
                    requireContext()
                )
            )[QuizViewModel::class.java]*/

        _binding = FragmentQuizBinding.inflate(inflater, container, false)
        val root: View = binding.root
        /*val textView: TextView = binding.textQuiz
        quizViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }*/
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        quizViewModel = ViewModelProvider(
            this, ViewModelFactory(
                DataStorePreferences.getInstance(requireContext().dataStore), requireContext()
            )
        )[QuizViewModel::class.java]

        quizAdapter = QuizAdapter(this)

        binding.apply {
            rvQuiz.layoutManager = LinearLayoutManager(requireContext())
            rvQuiz.setHasFixedSize(true)
            rvQuiz.adapter = quizAdapter
        }

        quizViewModel.kuesionerList.observe(viewLifecycleOwner) {
            quizAdapter.submitList(it)
        }

        quizViewModel.getAllKuesioner()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun onKuesionerItemClick(kuesioner: DataItem) {
        val intent = Intent(requireContext(), QuizFillActivity::class.java)
        /*intent.putExtra(QuizFillActivity.EXTRA_KUESIONER, kuesioner)*/
    }
}