package com.subhanjana.newsapp.ui.adapters

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.subhanjana.newsapp.data.model.Article
import com.subhanjana.newsapp.databinding.ItemTopHeadlineBinding
import com.subhanjana.newsapp.utils.ItemClickListener

class TopHeadlineAdapter(private val articleList : ArrayList<Article>)
    : RecyclerView.Adapter<TopHeadlineAdapter.DataViewHolder> () {

        lateinit var itemClickListener: ItemClickListener<Article>
        class DataViewHolder(private val binding: ItemTopHeadlineBinding)
            : RecyclerView.ViewHolder(binding.root) {
                fun bind(article: Article,itemClickListener: ItemClickListener<Article>){
                    binding.textViewTitle.text = article.title
                    binding.textViewDescription.text = article.description
                    binding.textViewSource.text = article.source.name
                    Glide.with(binding.imageViewBanner.context).load(article.imageUrl).into(binding.imageViewBanner)
                    itemView.setOnClickListener {
                        itemClickListener(article)
                    }
                }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = DataViewHolder (
        ItemTopHeadlineBinding.inflate(LayoutInflater.from(parent.context),parent,false)
    )

    override fun getItemCount(): Int = articleList.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) = holder.bind(articleList[position],itemClickListener)
    fun addData (list: List<Article>){
        articleList.addAll(list)
    }
}