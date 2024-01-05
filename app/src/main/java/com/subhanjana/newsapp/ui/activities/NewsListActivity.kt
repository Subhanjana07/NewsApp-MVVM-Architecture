package com.subhanjana.newsapp.ui.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.subhanjana.newsapp.NewsApplication
import com.subhanjana.newsapp.R
import com.subhanjana.newsapp.data.model.Article
import com.subhanjana.newsapp.databinding.ActivityNewsListBinding
import com.subhanjana.newsapp.databinding.ItemNewsListBinding
import com.subhanjana.newsapp.di.components.DaggerLanguageComponent
import com.subhanjana.newsapp.di.components.DaggerNewsListComponent
import com.subhanjana.newsapp.di.modules.LanguageModule
import com.subhanjana.newsapp.di.modules.NewsListModule
import com.subhanjana.newsapp.ui.adapters.NewsListAdapter
import com.subhanjana.newsapp.ui.base.UiState
import com.subhanjana.newsapp.ui.viewModels.NewsListViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsListActivity : AppCompatActivity() {

    @Inject
    lateinit var newsListViewModel: NewsListViewModel

    @Inject
    lateinit var newsListAdapter: NewsListAdapter

    lateinit var binding: ActivityNewsListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies()
        super.onCreate(savedInstanceState)
        binding = ActivityNewsListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupUI()
        setupObserver()
    }
    companion object{
        fun getIntent(context : Context,newsId : Int,newsSource : String? = "",
                      newsCountry : String? = "",newsLanguage : String? = "") : Intent{
            return Intent(context,NewsListActivity::class.java)
                .putExtra("newsId",newsId)
                .putExtra("newsSource",newsSource)
                .putExtra("newsLanguage",newsLanguage)
                .putExtra("newsCountry",newsCountry)
        }
    }
    private fun getData(){
        intent.extras?.apply {
            val newsId = getInt("newsId")
            newsId?.let { id ->
                when(id){
                    1 -> {
                        val language = getString("newsLanguage")
                        language?.let {
                            newsListViewModel.fetchNewsByLanguage(it)
                        }
                    }
                    2 -> {
                        val country = getString("newsCountry")
                        country?.let {
                            newsListViewModel.fetchNewsByCountry(it)
                        }
                    }
                    3 -> {
                        val source = getString("newsSource")
                        source?.let {
                            newsListViewModel.fetchNewsBySource(it)
                        }
                    }
                }
            }
        }
    }

    private fun injectDependencies(){
        DaggerNewsListComponent.builder()
            .applicationComponent((application as NewsApplication).applicationComponent)
            .newsListModule(NewsListModule(this)).build().inject(this)
    }

    private fun setupUI() {
        val recyclerView = binding.rvNewsList
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this.context)
            addItemDecoration(
                DividerItemDecoration(
                    recyclerView.context,
                    (recyclerView.layoutManager as LinearLayoutManager).orientation
                )
            )
            recyclerView.adapter = newsListAdapter
        }
        getData()
    }
    private fun setupObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                newsListViewModel.newsListUiState.collect {
                    when (it) {
                        is UiState.Success -> {
                            binding.progressBarNewsList.visibility = View.GONE
                            binding.rvNewsList.visibility = View.VISIBLE
                            renderList(it.data)

                        }
                        is UiState.Loading -> {
                            binding.progressBarNewsList.visibility = View.VISIBLE
                            binding.rvNewsList.visibility = View.GONE
                        }
                        is UiState.Error -> {
                            binding.progressBarNewsList.visibility = View.GONE
                            Toast.makeText(this@NewsListActivity, it.message, Toast.LENGTH_LONG)
                                .show()
                            Log.e("error",it.message)
                        }
                    }
                }
            }
        }
    }

    private fun renderList(newsList: List<Article>) {
        newsListAdapter.addData(newsList)
        newsListAdapter.notifyDataSetChanged()
//        println("newsLisAdapter count::" + newsLisAdapter.itemCount + " newsList size ::" + newsList.size)
    }
}