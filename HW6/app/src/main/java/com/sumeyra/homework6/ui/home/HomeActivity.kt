package com.sumeyra.homework6.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.sumeyra.homework6.configs.ApiClient
import com.sumeyra.homework6.databinding.ActivityHomeBinding
import com.sumeyra.homework6.model.ProductsModel
import com.sumeyra.homework6.services.DummyService
import com.sumeyra.homework6.ui.detail.DetailActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    lateinit var dummyService: DummyService
    lateinit var adapter: HomeAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.searchView.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                adapter.filter.filter(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        }
        )

        dummyService = ApiClient.getClient().create(DummyService::class.java)

        dummyService.getProduct().enqueue(object : Callback<ProductsModel> {
            override fun onResponse(call: Call<ProductsModel>, response: Response<ProductsModel>) {
                val product = response.body()?.products
                Log.d("status", response.code().toString())
                product?.let {
                    adapter = HomeAdapter(it){
                        val intent = Intent(this@HomeActivity, DetailActivity::class.java)
                        intent.putExtra("product",it)
                        startActivity(intent)
                       // finish()
                    }
                    binding.recyclerview .adapter= adapter
                }
            }

            override fun onFailure(call: Call<ProductsModel>, t: Throwable) {
                Log.e("login", t.toString())
                Toast.makeText(this@HomeActivity, "Internet or Server Fail", Toast.LENGTH_LONG).show()
            }

        })
        applicationContext
    }
}