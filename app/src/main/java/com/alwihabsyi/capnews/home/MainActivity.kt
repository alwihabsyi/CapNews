package com.alwihabsyi.capnews.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.LinearLayoutManager
import com.alwihabsyi.capnews.core.data.Resource
import com.alwihabsyi.capnews.core.ui.NewsAdapter
import com.alwihabsyi.capnews.databinding.ActivityMainBinding
import com.alwihabsyi.capnews.detail.DetailActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private val newsAdapter by lazy { NewsAdapter() }
    private val viewModel by viewModel<HomeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        setContentView(binding.root)

        setUpRv()
        observer()
        setActions()
    }

    private fun setActions() {
        binding.btnFavorite.setOnClickListener {
            val uri = Uri.parse("capnews://favorite")
            startActivity(Intent(Intent.ACTION_VIEW, uri))
        }
    }

    private fun setUpRv() {
        binding.rvNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
        newsAdapter.onClick = {
            startActivity(
                Intent(this, DetailActivity::class.java).apply {
                    putExtra(DetailActivity.NEWS, it)
                }
            )
        }
    }

    private fun observer() {
        viewModel.news.observe(this) {
            when (it) {
                is Resource.Loading -> {}
                is Resource.Success -> {
                    newsAdapter.differ.submitList(it.data)
                }
                is Resource.Error -> {
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}