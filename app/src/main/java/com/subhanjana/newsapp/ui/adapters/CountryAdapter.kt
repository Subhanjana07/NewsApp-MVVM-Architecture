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
import com.subhanjana.newsapp.utils.ItemClickListener

class CountryAdapter(private val countryList: ArrayList<Country>) : RecyclerView.Adapter<CountryAdapter.DataViewHolder>() {

    lateinit var itemClickListener: ItemClickListener<Country>
    class DataViewHolder(private val binding: ItemCountryBinding) : ViewHolder(binding.root){
        fun bind(country : Country,itemClickListener: ItemClickListener<Country>){
            binding.btnItemCountry.text = country.name
            itemView.setOnClickListener {
                itemClickListener(country)

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            ItemCountryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun getItemCount(): Int = countryList.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(countryList[position],itemClickListener)
    }
    fun addData (list: List<Country>){
        countryList.clear()
        countryList.addAll(list)
    }
}