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
import com.subhanjana.newsapp.data.model.Article
import com.subhanjana.newsapp.databinding.ActivitySearchBinding
import com.subhanjana.newsapp.di.components.DaggerSearchComponent
import com.subhanjana.newsapp.di.modules.SearchModule
import com.subhanjana.newsapp.ui.adapters.SearchAdapter
import com.subhanjana.newsapp.ui.base.UiState
import com.subhanjana.newsapp.ui.viewModels.SearchViewModel
import com.subhanjana.newsapp.utils.getQueryTextChangeStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchActivity : AppCompatActivity() {

    @Inject
    lateinit var searchViewModel: SearchViewModel

    @Inject
    lateinit var searchAdapter: SearchAdapter

    private lateinit var binding: ActivitySearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies()
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        binding.searchView.clearFocus()
        searchViewModel.getSearchResult(binding.searchView.getQueryTextChangeStateFlow())
        setContentView(binding.root)
        setupUI()
        setUpObserver()
    }

    private fun injectDependencies() {
        DaggerSearchComponent.builder()
            .applicationComponent((application as NewsApplication).applicationComponent)
            .searchModule(SearchModule(this)).build().inject(this)
    }

    private fun setupUI() {
        val recyclerView = binding.rvSearch
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        recyclerView.adapter = searchAdapter
    }

    private fun setUpObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                searchViewModel.searchUiState
                    .collect {
                        when (it) {
                            is UiState.Success -> {
                                binding.rvSearch.visibility = View.VISIBLE
                                binding.progressBar.visibility = View.GONE
                                renderList(it.data)
                            }
                            is UiState.Loading -> {
                                binding.progressBar.visibility = View.VISIBLE
                                    binding.rvSearch.visibility = View.GONE

                            }
                            is UiState.Error -> {
                                binding.rvSearch.visibility = View.GONE
                                searchAdapter.clear()
                            }
                        }

                    }
            }
        }
    }
    private fun renderList(articleList: List<Article>) {
        searchAdapter.addData(articleList)
            searchAdapter.notifyDataSetChanged()

    }
}