package com.alwihabsyi.capnews.detail

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.alwihabsyi.capnews.R
import com.alwihabsyi.capnews.core.domain.model.News
import com.alwihabsyi.capnews.core.utils.glide
import com.alwihabsyi.capnews.databinding.ActivityDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    private var _binding: ActivityDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModel<DetailViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpPage()
    }

    @Suppress("DEPRECATION")
    private fun setUpPage() {
        val data = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(NEWS, News::class.java)
        } else {
            intent.getParcelableExtra(NEWS)
        }

        data?.let {
            with(binding) {
                ivNews.glide(it.urlToImage)
                tvContent.text = it.content
                tvTitle.text = it.title

                val news = viewModel.getNews(it.url)
                news.observe(this@DetailActivity) {
                    it?.let { setFavoriteIcon(it.isFavorite) }
                }

                var isFavorite = it.isFavorite
                setFavoriteIcon(isFavorite)
                fabFavorite.setOnClickListener { _ ->
                    isFavorite = !isFavorite
                    viewModel.setFavoriteNews(it, isFavorite)
                    setFavoriteIcon(isFavorite)
                }
            }
        }
    }

    private fun setFavoriteIcon(isFavorite: Boolean) {
        if (isFavorite) {
            binding.fabFavorite.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite))
        } else {
            binding.fabFavorite.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite_border))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        const val NEWS = "news"
    }
}