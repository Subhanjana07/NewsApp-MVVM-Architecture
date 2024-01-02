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
import com.subhanjana.newsapp.data.model.Language
import com.subhanjana.newsapp.databinding.ActivityLanguageBinding
import com.subhanjana.newsapp.di.components.DaggerLanguageComponent
import com.subhanjana.newsapp.di.modules.LanguageModule
import com.subhanjana.newsapp.ui.adapters.LanguageAdapter
import com.subhanjana.newsapp.ui.base.UiState
import com.subhanjana.newsapp.ui.viewModels.LanguageViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class LanguageActivity : AppCompatActivity() {

    @Inject
    lateinit var languageViewModel: LanguageViewModel

    @Inject
    lateinit var languageAdapter: LanguageAdapter
    private lateinit var binding: ActivityLanguageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies()
        super.onCreate(savedInstanceState)
        binding = ActivityLanguageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupUI()
        setupObserver()
    }

    private fun injectDependencies(){
        DaggerLanguageComponent.builder()
            .applicationComponent((application as NewsApplication).applicationComponent)
            .languageModule(LanguageModule(this)).build().inject(this)
    }

    private fun setupUI()
    {
        val recyclerView = binding.rvLanguage
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(
            DividerItemDecoration(recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation)
        )
        recyclerView.adapter = languageAdapter
    }
    private fun renderList(languageList: List<Language>) {
        languageAdapter.addData(languageList)
        languageAdapter.notifyDataSetChanged()
    }
    fun setupObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                languageViewModel.languageUiState.collect {
                    when (it) {
                        is UiState.Success -> {
                            binding.progressBarLanguage.visibility = View.GONE
                            binding.rvLanguage.visibility = View.VISIBLE
                            renderList(it.data)

                        }
                        is UiState.Loading -> {
                            binding.progressBarLanguage.visibility = View.VISIBLE
                            binding.rvLanguage.visibility = View.GONE
                        }
                        is UiState.Error -> {
                            binding.progressBarLanguage.visibility = View.GONE
                            Toast.makeText(this@LanguageActivity, it.message, Toast.LENGTH_LONG)
                                .show()
                            Log.e("error",it.message)
                        }
                    }
                }
            }
        }
    }
}