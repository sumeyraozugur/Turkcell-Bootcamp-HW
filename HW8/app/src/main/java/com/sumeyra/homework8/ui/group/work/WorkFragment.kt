package com.sumeyra.homework8.ui.group.work

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.sumeyra.homework8.databinding.FragmentWorkBinding
import com.sumeyra.homework8.ui.group.GroupAdapter
import com.sumeyra.homework8.ui.group.school.SchoolViewModel


class WorkFragment : Fragment() {
    private var _binding: FragmentWorkBinding ?= null
    private val binding get() = _binding!!
    private lateinit var workViewModel: WorkViewModel
    private val adapter by lazy { GroupAdapter() }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWorkBinding.inflate(inflater, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        workViewModel =
            ViewModelProvider(this)[WorkViewModel::class.java]

        binding.recyclerview.adapter = adapter
        initObserver()

    }

    private fun initObserver() {
        workViewModel.getUsersByGroup("work").observe(viewLifecycleOwner) {
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