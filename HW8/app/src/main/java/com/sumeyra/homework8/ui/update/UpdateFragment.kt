package com.sumeyra.homework8.ui.update

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.sumeyra.homework8.R
import com.sumeyra.homework8.databinding.FragmentUpdateBinding
import com.sumeyra.homework8.model.UserModel
import com.sumeyra.homework8.ui.add.AddViewModel
import com.sumeyra.homework8.ui.utils.extension.showToast


class UpdateFragment : Fragment() {

    private var _binding: FragmentUpdateBinding? = null
    private val binding get() = _binding!!
    private lateinit var updateViewModel: UpdateViewModel
    private val args :UpdateFragmentArgs by navArgs()
    private lateinit var arrayAdapter: ArrayAdapter<String>



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentUpdateBinding.inflate(layoutInflater, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val user = args.itemObject

        updateViewModel = ViewModelProvider(this)[UpdateViewModel::class.java]

        val userList = arrayListOf(args.itemObject.user_group)
        val tempList =  arrayListOf("family","school","friend","work")
        for(i in tempList ){
            if(i !in userList){
                userList.add(i)
            }
        }

        arrayAdapter = ArrayAdapter(requireContext(),R.layout.dropdown_item, userList)
        binding.autoCompleteTextView.setAdapter(arrayAdapter)



        with(binding){
            etName.setText(user.user_name)
            etSurname.setText( user.user_surname)
            etAddress.setText(user.user_address)
            etPhone.setText(user.user_phone)
           autoCompleteTextView.setText(user.user_group,false)



            btnDelete.setOnClickListener {
                updateViewModel.deleteUser(user)
                requireContext().showToast("Delete!!")
                findNavController().navigate(R.id.action_updateFragment_to_nav_home)
            }


            btnSave.setOnClickListener {
                updateItem()
            }
        }
    }

    private fun updateItem() {

        val name = binding.etName.text.toString()
        val surname = binding.etSurname.text.toString()
        val phone = binding.etPhone.text.toString()
        val address= binding.etAddress.text.toString()
        val group= binding.autoCompleteTextView.text.toString()
        if (inputCheck(name,surname,phone,address,group)) {
            //Create User Object
            val updateData =UserModel(args.itemObject.user_id,group,name,surname,phone,address)

            //Update Current User
            updateViewModel.updateUser(updateData)
            requireContext().showToast("Updated Successfully!")
            //Navigate Back
            findNavController().navigate(R.id.action_updateFragment_to_nav_home)

        } else {
            requireContext().showToast("Please fill out all fields!")
        }
    }


    private fun inputCheck(name: String,surname:String,phone:String,address:String,group:String ): Boolean {
        return !(TextUtils.isEmpty(name) && TextUtils.isEmpty(surname)  && TextUtils.isEmpty(phone)  && TextUtils.isEmpty(address)  && TextUtils.isEmpty(group))
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}