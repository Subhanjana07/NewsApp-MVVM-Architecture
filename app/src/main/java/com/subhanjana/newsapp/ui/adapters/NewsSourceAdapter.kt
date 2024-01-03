package com.subhanjana.newsapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.subhanjana.newsapp.data.model.Source
import com.subhanjana.newsapp.databinding.ItemNewsSourceBinding

class NewsSourceAdapter(private val sourceList : ArrayList<Source>): RecyclerView.Adapter<NewsSourceAdapter.DataViewHolder>() {
    class DataViewHolder(private val binding: ItemNewsSourceBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(source: Source){
            binding.btnItemSource.text = source.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            ItemNewsSourceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun getItemCount(): Int = sourceList.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(sourceList[position])
    }
    fun addData (list: List<Source>){
        sourceList.addAll(list)
    }
}