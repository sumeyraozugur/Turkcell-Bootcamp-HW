package com.sumeyra.homework8.ui.group.family

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.sumeyra.homework8.databinding.FragmentFamilyBinding
import com.sumeyra.homework8.ui.group.GroupAdapter

class FamilyFragment : Fragment() {

    private var _binding: FragmentFamilyBinding? = null
    private val binding get() = _binding!!
    private lateinit var familyViewModel: FamilyViewModel
    private val adapter by lazy { GroupAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFamilyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        familyViewModel =
            ViewModelProvider(this)[FamilyViewModel::class.java]
        binding.recyclerview.adapter = adapter
        initObserver()
    }


    private fun initObserver(){
        familyViewModel.getUsersByGroup("family").observe(viewLifecycleOwner) {
            if(it.isEmpty()){
                binding.tvEmpty.visibility =View.VISIBLE
            }else{
                adapter.setData(it)
            }

        }
    }









    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}