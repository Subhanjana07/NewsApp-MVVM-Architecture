package com.subhanjana.newsapp.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.subhanjana.newsapp.data.model.Source
import com.subhanjana.newsapp.databinding.ItemNewsSourceBinding
import com.subhanjana.newsapp.ui.activities.NewsListActivity

class NewsSourceAdapter(private val sourceList : ArrayList<Source>): RecyclerView.Adapter<NewsSourceAdapter.DataViewHolder>() {
    class DataViewHolder(private val binding: ItemNewsSourceBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(source: Source){
            binding.btnItemSource.text = source.name
            itemView.setOnClickListener {
                val activity = it.context as AppCompatActivity
                activity.startActivity(NewsListActivity.getIntent(activity, newsId = 3, newsSource = source.id))
                Log.e("NewsListActivity","NewsListActivity has started")
            }
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
        sourceList.clear()
        sourceList.addAll(list)
    }
}