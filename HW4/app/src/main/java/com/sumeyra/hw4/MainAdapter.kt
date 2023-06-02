package com.sumeyra.hw4

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sumeyra.hw4.databinding.ItemMainBinding

class MainAdapter(
    private var onNewsClick: (String) -> Unit
) : ListAdapter<NewsModel, MainAdapter.HomeHolder>(
    DiffUtilCallback<NewsModel>(
        itemsTheSame = { oldItem, newItem ->
            oldItem == newItem
        },
        contentsTheSame = { oldItem, newItem ->
            oldItem == newItem
        }
    )
) {

    inner class HomeHolder(private var itemMainBinding: ItemMainBinding):
        RecyclerView.ViewHolder(itemMainBinding.root) {

        fun bind(galleryModel: NewsModel) = with(itemMainBinding){
            tvTitle.text = galleryModel.title
            //ivNews.loadImage(galleryModel.img)
            Glide.with(ivNews).load(galleryModel.img).into(ivNews)
            root.setOnClickListener {
                onNewsClick(galleryModel.href)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= HomeHolder(
        ItemMainBinding.inflate(LayoutInflater.from(parent.context),parent,false)
    )

    override fun onBindViewHolder(holder: HomeHolder, position: Int) {
        holder.bind(currentList[position])
    }
}