package com.sumeyra.homework8.ui.group.school

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.sumeyra.homework8.databinding.FragmentSchoolBinding
import com.sumeyra.homework8.ui.group.GroupAdapter
import com.sumeyra.homework8.ui.group.family.FamilyViewModel


class SchoolFragment : Fragment() {

    private var _binding: FragmentSchoolBinding? = null
    private val binding get() = _binding!!
    private lateinit var schoolViewModel: SchoolViewModel
    private val adapter by lazy { GroupAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSchoolBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        schoolViewModel =
            ViewModelProvider(this)[SchoolViewModel::class.java]

        binding.recyclerview.adapter = adapter
        initObserver()
    }

    private fun initObserver(){
        schoolViewModel.getUsersByGroup("school").observe(viewLifecycleOwner) {
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