package com.example.newsproject

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.newsproject.recyclerview.AdapterRecyclerView
import com.example.newsproject.databinding.ActivityMainBinding
import com.example.newsproject.entity.Articles
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: ViewModelArticle by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapterArticle = AdapterRecyclerView { onClickItemRecyclerView(it, it.url) }

        viewModel.articles.onEach {
            adapterArticle.setData(it)
        }.launchIn(lifecycleScope)

        binding.recyclerView.adapter = adapterArticle

    }

    private fun onClickItemRecyclerView(item: Articles, url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }
}