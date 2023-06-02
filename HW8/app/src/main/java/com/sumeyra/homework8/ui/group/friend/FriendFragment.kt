package com.sumeyra.homework8.ui.group.friend

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.sumeyra.homework8.databinding.FragmentFriendBinding
import com.sumeyra.homework8.ui.group.GroupAdapter


class FriendFragment : Fragment() {

    private var _binding: FragmentFriendBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FriendViewModel by viewModels()
    private val adapter by lazy { GroupAdapter() }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFriendBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerview.adapter = adapter
        initObserver()

    }

    private fun initObserver(){
        viewModel.getUsersByGroup("friend").observe(viewLifecycleOwner) {
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