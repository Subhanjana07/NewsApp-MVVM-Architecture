package com.subhanjana.newsapp.ui.activities

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.subhanjana.newsapp.NewsApplication
import com.subhanjana.newsapp.R
import com.subhanjana.newsapp.data.model.Country
import com.subhanjana.newsapp.databinding.ActivityCountriesBinding
import com.subhanjana.newsapp.di.components.DaggerCountryComponent
import com.subhanjana.newsapp.di.modules.CountryModule
import com.subhanjana.newsapp.ui.adapters.CountryAdapter
import com.subhanjana.newsapp.ui.base.UiState
import com.subhanjana.newsapp.ui.viewModels.CountryViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class CountriesActivity : AppCompatActivity() {

    @Inject
    lateinit var countryViewModel: CountryViewModel
    @Inject
    lateinit var countryAdapter: CountryAdapter
    private lateinit var binding: ActivityCountriesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies()
        super.onCreate(savedInstanceState)
        binding = ActivityCountriesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupUI()
        setupObserver()
    }

    private fun injectDependencies(){
        DaggerCountryComponent.builder()
            .applicationComponent((application as NewsApplication).applicationComponent)
            .countryModule(CountryModule(this)).build().inject(this)
    }

    private fun setupUI()
    {
        val recyclerView = binding.rvCountries
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(
            DividerItemDecoration(recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation)
        )
        recyclerView.adapter = countryAdapter
        binding.layoutError.btnError.setOnClickListener {
            binding.progressBarCountries.visibility = View.VISIBLE
            binding.includeLayoutError.visibility = View.GONE
            countryViewModel.fetchCountries()
        }
        countryAdapter.itemClickListener = {country ->
             startActivity(NewsListActivity.getIntent(this, newsId = 2, newsCountry = country.id))
        }
    }
    private fun renderList(countryList: List<Country>) {
        countryAdapter.addData(countryList)
        countryAdapter.notifyDataSetChanged()
    }
    fun setupObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                countryViewModel.countryUiState.collect {
                        when (it) {
                            is UiState.Success -> {
                                binding.progressBarCountries.visibility = View.GONE
                                binding.rvCountries.visibility = View.VISIBLE
                                binding.includeLayoutError.visibility =  View.GONE
                                renderList(it.data)

                            }
                            is UiState.Loading -> {
                                binding.progressBarCountries.visibility = View.VISIBLE
                                binding.rvCountries.visibility = View.GONE
                                binding.includeLayoutError.visibility =  View.GONE
                            }
                            is UiState.Error -> {
                                binding.progressBarCountries.visibility = View.GONE
                                binding.includeLayoutError.visibility =  View.VISIBLE
                                Toast.makeText(this@CountriesActivity, it.message, Toast.LENGTH_LONG)
                                    .show()
                                Log.e("error",it.message)
                            }
                        }
                    }
            }
        }
    }
}