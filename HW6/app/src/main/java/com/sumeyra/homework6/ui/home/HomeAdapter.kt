package com.sumeyra.homework6.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.sumeyra.homework6.databinding.ItemHomeBinding
import com.sumeyra.homework6.extension.loadImage
import com.sumeyra.homework6.model.Product



class HomeAdapter(val productList :List<Product>, val onClickProduct:(Product)->Unit):RecyclerView.Adapter<HomeAdapter.ViewHolder>(),Filterable {
 var product = productList

   inner class ViewHolder(val binding: ItemHomeBinding) :RecyclerView.ViewHolder(binding.root){
       fun bind(product:Product){
           binding.tvPrice.text = "${product.price} TL"
           binding.ivProduct.loadImage(product.images[0])
           binding.tvTitle.text = product.title
           binding.root.setOnClickListener {
               onClickProduct(product)
           }


       }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemHomeBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = product.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
         holder.bind(product[position])
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charString: CharSequence?): FilterResults {
                product = if(charString.isNullOrEmpty()){
                    productList
                } else{
                    productList.filter { it.title.contains(charString.toString()) }
                }
                return FilterResults().apply {
                    values = product
                }
            }

            override fun publishResults(charString: CharSequence?, results: FilterResults?) {
                val filteredDefinitions = results?.values as? List<*>
                filteredDefinitions?.let { def ->
                    product = def.filterIsInstance<Product>()
                    notifyDataSetChanged()
                }
            }
        }
    }
}