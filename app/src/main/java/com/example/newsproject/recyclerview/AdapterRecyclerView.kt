package com.example.newsproject.recyclerview

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsproject.entity.Articles
import com.example.newsproject.databinding.NewsItemBinding
import com.example.newsproject.entity.ArticlesList

class AdapterRecyclerView(val onClick: (Articles) -> Unit) : RecyclerView.Adapter<ViewHolder>() {

    private var data: List<Articles> = emptyList()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: List<Articles>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            NewsItemBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        with(holder.binding) {
            name.text = item.source.name
            nameAuthor.text = item.author ?: "unknown author"
            title.text = item.title
            description.text = item.description
            publishedAt.text = item.publishedAt
            content.text = item.content.substring(0, item.content.length - 13)
            Glide.with(imageNews.context)
                .load(item.urlToImage)
                .into(imageNews)
        }
        holder.binding.root.setOnClickListener {
            item.let {
                onClick(item)
            }
        }
    }

    override fun getItemCount(): Int = data.size
}

class ViewHolder(val binding: NewsItemBinding) : RecyclerView.ViewHolder(binding.root)