package com.subhanjana.newsapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.subhanjana.newsapp.data.model.Article
import com.subhanjana.newsapp.databinding.ItemSearchBinding

class SearchAdapter(private val searchList: ArrayList<Article>) : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {
    class SearchViewHolder(private val binding : ItemSearchBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item : Article){
            binding.headline.text = item.title

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder(ItemSearchBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false))
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(searchList[position])
    }

    override fun getItemCount(): Int = searchList.size

    fun addData(list: List<Article>) {
        searchList.clear()
        searchList.addAll(list)
    }

    fun clear(){
        if(searchList.isNotEmpty()) {
            searchList.clear()
            notifyDataSetChanged()
        }
    }

}