package com.alwihabsyi.capnews.favorite

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.alwihabsyi.capnews.core.ui.NewsAdapter
import com.alwihabsyi.capnews.detail.DetailActivity
import com.alwihabsyi.capnews.favorite.databinding.ActivityFavoriteBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteActivity : AppCompatActivity() {

    private var _binding: ActivityFavoriteBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModel<FavoriteViewModel>()
    private val favoriteAdapter by lazy { NewsAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadKoinModules(favoriteModule)

        setUpRv()
        observer()
    }

    private fun observer() {
        viewModel.favoriteList.observe(this) {
            favoriteAdapter.differ.submitList(it)
        }
    }

    private fun setUpRv() {
        binding.rvFavorite.apply {
            adapter = favoriteAdapter
            layoutManager = LinearLayoutManager(context)
        }

        favoriteAdapter.onClick = {
            startActivity(
                Intent(this, DetailActivity::class.java).apply {
                    putExtra(DetailActivity.NEWS, it)
                }
            )
        }
    }
}