package com.subhanjana.newsapp.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.subhanjana.newsapp.data.model.Language
import com.subhanjana.newsapp.databinding.ItemLanguageBinding
import com.subhanjana.newsapp.ui.activities.NewsListActivity

class LanguageAdapter(private val languageList: ArrayList<Language>) : RecyclerView.Adapter<LanguageAdapter.DataViewHolder>() {
    class DataViewHolder(private val binding: ItemLanguageBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(language : Language){
            binding.btnItemLanguage.text = language.name
            itemView.setOnClickListener {
                val activity = it.context as AppCompatActivity
                activity.startActivity(NewsListActivity.getIntent(activity, newsId = 1, newsLanguage = language.id))
                Log.e("NewsListActivity","NewsListActivity has started")
            }
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
        languageList.clear()
        languageList.addAll(list)
    }
}