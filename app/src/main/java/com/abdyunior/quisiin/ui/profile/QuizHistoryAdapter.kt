package com.abdyunior.quisiin.ui.profile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abdyunior.quisiin.data.response.HistoryRequest
import com.abdyunior.quisiin.databinding.ItemQuizHistoryBinding

class QuizHistoryAdapter(private val list: ArrayList<HistoryRequest>) :
    RecyclerView.Adapter<QuizHistoryAdapter.ViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): QuizHistoryAdapter.ViewHolder {
        val binding =
            ItemQuizHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: QuizHistoryAdapter.ViewHolder, position: Int) {
        val (title, description) = list[position]
        holder.bind(title, description)

        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(list[holder.adapterPosition]) }
    }

    override fun getItemCount(): Int = list.size

    inner class ViewHolder(private val binding: ItemQuizHistoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(title: String, description: String) {
            binding.tvQuizTitle.text = title
            binding.tvDescription.text = description
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: HistoryRequest)
    }
}