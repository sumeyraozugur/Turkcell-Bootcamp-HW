package com.sumeyra.homework6.ui.detail

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sumeyra.homework6.databinding.ActivityDetailBinding
import com.sumeyra.homework6.extension.loadImage
import com.sumeyra.homework6.model.Product
import com.sumeyra.homework6.model.ProductsModel

class DetailActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)


            val product =if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                intent.getParcelableExtra("product", Product::class.java)
            } else {
                intent.getParcelableExtra<Product>("product")

        }


        if (product != null) {
            setDetailData(product)
        }


    }
    private fun setDetailData(product: Product){
        with(binding){
            tvTitle.text = product.title
            tvDescription.text =product.description
            tvPrice.text = "${product.price} TL"
            ivDetail.loadImage(product.images[0])
        }
    }
}