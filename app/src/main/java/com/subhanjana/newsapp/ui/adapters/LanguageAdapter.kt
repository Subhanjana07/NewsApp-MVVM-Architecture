package com.subhanjana.newsapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.subhanjana.newsapp.data.model.Language
import com.subhanjana.newsapp.databinding.ItemLanguageBinding

class LanguageAdapter(private val languageList: ArrayList<Language>) : RecyclerView.Adapter<LanguageAdapter.DataViewHolder>() {
    class DataViewHolder(private val binding: ItemLanguageBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(language : Language){
            binding.btnItemLanguage.text = language.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            ItemLanguageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun getItemCount(): Int = languageList.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(languageList[position])
    }
    fun addData (list: List<Language>){
        languageList.addAll(list)
    }
}