package com.sumeyra.tripapp.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sumeyra.tripapp.databinding.ItemHomeBinding
import com.sumeyra.tripapp.model.CityModel

class HomeAdapter(private val onClick: (CityModel) -> Unit) :
    RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {
     val formList = mutableListOf<CityModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val binding =
            ItemHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(formList[position])

    }



    inner class HomeViewHolder(private var binding: ItemHomeBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(city: CityModel) {
            with(binding) {
                tvTitle.text = city.title
                tvNotes.text = city.notes
                root.setOnLongClickListener {
                    onClick(city)
                    true
                }
            }
        }
    }

    override fun getItemCount() = formList.size

    fun setData(city: List<CityModel>) {
        formList.clear()
        formList.addAll(city)
        notifyDataSetChanged()
    }
}