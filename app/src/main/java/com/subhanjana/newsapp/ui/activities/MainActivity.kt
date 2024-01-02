package com.subhanjana.newsapp.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.subhanjana.newsapp.R
import com.subhanjana.newsapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        onClick()
    }
    private fun onClick(){
        binding.btnTopHeadlines.setOnClickListener {
            val intent = Intent(this,TopHeadlineActivity::class.java)
            startActivity(intent)
        }
        binding.btnNews.setOnClickListener {
            val intent = Intent(this,NewsSourcesActivity::class.java)
            startActivity(intent)
        }
        binding.btnCountries.setOnClickListener {
            val intent = Intent(this,CountriesActivity::class.java)
            startActivity(intent)
        }
        binding.btnLanguages.setOnClickListener {
            val intent = Intent(this,LanguageActivity::class.java)
            startActivity(intent)
        }
        binding.btnSearch.setOnClickListener {
            val intent = Intent(this,SearchActivity::class.java)
            startActivity(intent)
        }
    }
}