package com.sumeyra.homework8.ui.home

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.sumeyra.homework8.R
import com.sumeyra.homework8.databinding.FragmentHomeBinding
import com.sumeyra.homework8.model.UserModel
import com.sumeyra.homework8.ui.utils.extension.sent

class HomeFragment : Fragment() , SearchView.OnQueryTextListener{
    private var _binding:FragmentHomeBinding ?= null
    private val binding get()  = _binding!!
    private lateinit var homeViewModel: HomeViewModel
    private val adapter by lazy { HomeAdapter(onClick = ::onClick) }


    private fun onClick(userModel: UserModel) {
        val action = HomeFragmentDirections.actionNavHomeToUpdateFragment(userModel)
        findNavController().navigate(action)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(layoutInflater,container,false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //UserViewModel
        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        binding.recyclerview.adapter = adapter
        initObserver()

        binding.fab.setOnClickListener {
            Navigation.sent(it, R.id.action_nav_home_to_addFragment)
        }




    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initObserver(){
        homeViewModel.readAllData.observe(viewLifecycleOwner){
            if(it.isEmpty()){
                binding.tvEmpty.visibility =View.VISIBLE
            }else{
                adapter.setData(it)
            }

        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
        val searchItem = menu.findItem(R.id.ic_search)
        val searchView = searchItem.actionView as SearchView
        searchView.setOnQueryTextListener(this)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        query?.let { homeViewModel.searchDatabase(it) }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        newText?.let {
            homeViewModel.searchDatabase(it)
            homeViewModel.tempList.observe(viewLifecycleOwner){
                adapter.setData(it)
            }
        }

        return true
    }


}