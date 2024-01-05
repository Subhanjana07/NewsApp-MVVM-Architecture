package com.subhanjana.newsapp.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.subhanjana.newsapp.data.model.Country
import com.subhanjana.newsapp.databinding.ItemCountryBinding
import com.subhanjana.newsapp.ui.activities.NewsListActivity

class CountryAdapter(private val countryList: ArrayList<Country>) : RecyclerView.Adapter<CountryAdapter.DataViewHolder>() {
    class DataViewHolder(private val binding: ItemCountryBinding) : ViewHolder(binding.root){
        fun bind(country : Country){
            binding.btnItemCountry.text = country.name
            itemView.setOnClickListener {
                val activity = it.context as AppCompatActivity
                activity.startActivity(NewsListActivity.getIntent(activity, newsId = 2, newsCountry = country.id))
            }
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
        countryList.clear()
        countryList.addAll(list)
    }
}