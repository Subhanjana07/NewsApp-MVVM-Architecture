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
import com.subhanjana.newsapp.data.model.Article
import com.subhanjana.newsapp.databinding.ActivityTopHeadlineBinding
import com.subhanjana.newsapp.di.components.DaggerTopHeadlineComponent
import com.subhanjana.newsapp.di.modules.TopHeadlineModule
import com.subhanjana.newsapp.ui.adapters.TopHeadlineAdapter
import com.subhanjana.newsapp.ui.base.UiState
import com.subhanjana.newsapp.ui.viewModels.TopHeadlineViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class TopHeadlineActivity : AppCompatActivity() {

    @Inject
    lateinit var topHeadlineViewModel: TopHeadlineViewModel

    @Inject
    lateinit var topHeadlineAdapter: TopHeadlineAdapter

    private lateinit var binding: ActivityTopHeadlineBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies()
        super.onCreate(savedInstanceState)
        binding = ActivityTopHeadlineBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupUI()
        setupObserver()
    }

    private fun injectDependencies(){
        DaggerTopHeadlineComponent.builder()
            .applicationComponent((application as NewsApplication).applicationComponent)
            .topHeadlineModule(TopHeadlineModule(this)).build().inject(this)
    }

    private fun setupUI()
    {
        val recyclerView = binding.rvTopHeadline
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(
            DividerItemDecoration(recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation))
        recyclerView.adapter = topHeadlineAdapter
    }
    private fun renderList(articleList: List<Article>) {
        topHeadlineAdapter.addData(articleList)
        topHeadlineAdapter.notifyDataSetChanged()
    }
    private fun setupObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                topHeadlineViewModel.uiState.collect {
                    when (it) {
                        is UiState.Success -> {
                            binding.progressBarTopHeadline.visibility = View.GONE
                            renderList(it.data)
                            binding.rvTopHeadline.visibility = View.VISIBLE
                        }
                        is UiState.Loading -> {
                            binding.progressBarTopHeadline.visibility = View.VISIBLE
                            binding.rvTopHeadline.visibility = View.GONE
                        }
                        is UiState.Error -> {
                            binding.progressBarTopHeadline.visibility = View.GONE
                            Toast.makeText(this@TopHeadlineActivity, it.message, Toast.LENGTH_LONG)
                                .show()
                            Log.e("error",it.message)
                        }
                    }
                }
            }
        }
    }
}