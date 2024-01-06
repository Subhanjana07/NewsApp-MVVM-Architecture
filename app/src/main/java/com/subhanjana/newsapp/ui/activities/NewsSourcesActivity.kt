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
import com.subhanjana.newsapp.data.model.Source
import com.subhanjana.newsapp.databinding.ActivityNewsSourcesBinding
import com.subhanjana.newsapp.di.components.DaggerNewsSourceComponent
import com.subhanjana.newsapp.di.modules.NewsSourceModule
import com.subhanjana.newsapp.ui.adapters.NewsSourceAdapter
import com.subhanjana.newsapp.ui.base.UiState
import com.subhanjana.newsapp.ui.viewModels.NewsSourceViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsSourcesActivity : AppCompatActivity() {
    @Inject
    lateinit var newsSourceViewModel: NewsSourceViewModel

    @Inject
    lateinit var newsSourceAdapter: NewsSourceAdapter

    private lateinit var binding: ActivityNewsSourcesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies()
        super.onCreate(savedInstanceState)
        binding = ActivityNewsSourcesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupUI()
        setupObserver()
    }

    private fun injectDependencies(){
        DaggerNewsSourceComponent.builder()
            .applicationComponent((application as NewsApplication).applicationComponent)
            .newsSourceModule(NewsSourceModule(this)).build().inject(this)
    }

    private fun setupUI()
    {
        val recyclerView = binding.rvSource
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(
            DividerItemDecoration(recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation)
        )
        recyclerView.adapter = newsSourceAdapter
        binding.layoutError.btnError.setOnClickListener {
            binding.progressBarSource.visibility = View.VISIBLE
            binding.includeLayoutError.visibility = View.GONE
            newsSourceViewModel.fetchSource()
        }
    }
    private fun renderList(sourceList: List<Source>) {
        newsSourceAdapter.addData(sourceList)
        newsSourceAdapter.notifyDataSetChanged()
    }
    fun setupObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                newsSourceViewModel.sourceUiState.collect {
                    when (it) {
                        is UiState.Success -> {
                            binding.progressBarSource.visibility = View.GONE
                            binding.rvSource.visibility = View.VISIBLE
                            binding.includeLayoutError.visibility =  View.GONE
                            renderList(it.data)

                        }
                        is UiState.Loading -> {
                            binding.progressBarSource.visibility = View.VISIBLE
                            binding.rvSource.visibility = View.GONE
                            binding.includeLayoutError.visibility =  View.GONE
                        }
                        is UiState.Error -> {
                            binding.progressBarSource.visibility = View.GONE
                            binding.includeLayoutError.visibility =  View.VISIBLE
                            Toast.makeText(this@NewsSourcesActivity, it.message, Toast.LENGTH_LONG)
                                .show()
                            Log.e("error",it.message)
                        }
                    }
                }
            }
        }
    }
}