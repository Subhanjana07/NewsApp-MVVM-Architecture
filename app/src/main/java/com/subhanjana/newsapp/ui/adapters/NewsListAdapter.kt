package com.subhanjana.newsapp.ui.adapters

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.subhanjana.newsapp.data.model.Article
import com.subhanjana.newsapp.databinding.ItemNewsListBinding

class NewsListAdapter(private val newsList: ArrayList<Article>)
    : RecyclerView.Adapter<NewsListAdapter.DataViewHolder> () {

        class DataViewHolder(private val binding: ItemNewsListBinding)
            : RecyclerView.ViewHolder(binding.root) {
            fun bind(article: Article){
                binding.textViewNewsListTitle.text = article.title
                binding.textViewNewsListDescription.text = article.description
                binding.textViewNewsListSource.text = article.source.name
                Glide.with(binding.imageViewNewsListBanner.context).load(article.imageUrl).into(binding.imageViewNewsListBanner)
                itemView.setOnClickListener {
                    val builder = CustomTabsIntent.Builder()
                    val customTabsIntent = builder.build()
                    customTabsIntent.launchUrl(it.context, Uri.parse(article.url))
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = DataViewHolder (
            ItemNewsListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )

        override fun getItemCount(): Int = newsList.size

        override fun onBindViewHolder(holder: DataViewHolder, position: Int) = holder.bind(newsList[position])

        fun addData (list: List<Article>){
            newsList.addAll(list)
        }
    }