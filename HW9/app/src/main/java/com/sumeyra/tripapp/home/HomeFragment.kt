package com.sumeyra.tripapp.home

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.sumeyra.tripapp.R
import com.sumeyra.tripapp.databinding.FragmentHomeBinding
import com.sumeyra.tripapp.delegete.viewBinding
import com.sumeyra.tripapp.model.CityModel
import com.sumeyra.tripapp.utils.extension.sent


class HomeFragment : Fragment(R.layout.fragment_home) {
    private val binding by viewBinding(FragmentHomeBinding::bind)
    private val homeAdapter by lazy { HomeAdapter(onClick = ::onClick) }
    private val viewModel: HomeViewModel by viewModels()

    private fun onClick(cityModel: CityModel) {
        context?.let {
            val alertDialogBuilder = AlertDialog.Builder(it)
                .setTitle("Are you sure you want to delete?")
                .setPositiveButton("Yes") { dialogInterface: DialogInterface, i: Int ->
                viewModel.deleteFromFirebase(cityModel)
            }
            alertDialogBuilder.setNegativeButton("No") { dialogInterface: DialogInterface, i: Int ->

            }

            val alertDialog: AlertDialog = alertDialogBuilder.create()
            alertDialog.show()
        }

    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        with(binding) {
            recyclerview.adapter = homeAdapter

            tvClick.setOnClickListener {
                Navigation.sent(it, R.id.action_homeFragment_to_formFragment)
            }
        }
    }

    private fun initObservers() {
        with(binding) {
            viewModel.cityList.observe(viewLifecycleOwner) { productList ->
                homeAdapter.setData(productList)
                productList.forEach {
                    Log.v("home", it.cityName)
                }
            }

        }
    }
}