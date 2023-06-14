package com.abdyunior.quisiin.ui.quiz

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.abdyunior.quisiin.data.response.DataItem
import com.abdyunior.quisiin.databinding.ItemQuizBinding

class QuizAdapter(private val fragment: QuizFragment) :
    RecyclerView.Adapter<QuizAdapter.ViewHolder>() {

    private val kuesionerList = ArrayList<DataItem>()

    private val diffCallback = object : DiffUtil.ItemCallback<DataItem>() {
        override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
            return oldItem.kuesionerId == newItem.kuesionerId
        }

        override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    fun submitList(kuesioners: List<DataItem>) {
        kuesionerList.clear()
        kuesionerList.addAll(kuesioners)
        differ.submitList(kuesioners)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizAdapter.ViewHolder {
        val binding = ItemQuizBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: QuizAdapter.ViewHolder, position: Int) {
        val kuesioner = differ.currentList[position]
        holder.bind(kuesioner)
    }

    override fun getItemCount(): Int = differ.currentList.size

    inner class ViewHolder(private val binding: ItemQuizBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val quiz = differ.currentList[position]
                    fragment.onKuesionerItemClick(quiz)
                }
            }
        }

        fun bind(kuesioner: DataItem) {
            binding.apply {
                ivQuiz.load(kuesioner.image)
                tvQuizTitle.text = kuesioner.judul
                tvDescription.text = kuesioner.deskripsi
            }
        }

    }
}