package com.sumeyra.mybio.ui.gallery

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.sumeyra.mybio.databinding.ItemGalleryBinding
import com.sumeyra.mybio.model.GalleryModel
import com.sumeyra.mybio.utils.extension.loadImage

class GalleryAdapter(
) : RecyclerView.Adapter<GalleryAdapter.GalleryHolder>() {

    private var galleryList = listOf<GalleryModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = GalleryHolder(
        ItemGalleryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: GalleryHolder, position: Int) =
        holder.bind(galleryList[position])

    inner class GalleryHolder(private var itemGalleryBinding: ItemGalleryBinding) :
        RecyclerView.ViewHolder(itemGalleryBinding.root) {

        fun bind(galleryModel: GalleryModel) = with(itemGalleryBinding) {
            tvTitle.text = galleryModel.title
            ivPicture.loadImage(galleryModel.img)


        }
    }

    override fun getItemCount() = galleryList.size

    fun updateList(updatedList: List<GalleryModel>) {
        this.galleryList = updatedList
        //notifyDataSetChanged()
    }
}