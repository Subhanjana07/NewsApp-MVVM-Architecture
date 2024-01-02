package com.subhanjana.newsapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.subhanjana.newsapp.data.model.Article
import com.subhanjana.newsapp.data.model.Country
import com.subhanjana.newsapp.data.repository.TopHeadlineRepository
import com.subhanjana.newsapp.databinding.ItemCountryBinding
import com.subhanjana.newsapp.databinding.ItemTopHeadlineBinding

class CountryAdapter(private val countryList: ArrayList<Country>) : RecyclerView.Adapter<CountryAdapter.DataViewHolder>() {
    class DataViewHolder(private val binding: ItemCountryBinding) : ViewHolder(binding.root){
        fun bind(country : Country){
            binding.btnItemCountry.text = country.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            ItemCountryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun getItemCount(): Int = countryList.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(countryList[position])
    }
    fun addData (list: List<Country>){
        countryList.addAll(list)
    }
}