package com.sumeyra.homework8.ui.add

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.sumeyra.homework8.R
import com.sumeyra.homework8.databinding.FragmentAddBinding
import com.sumeyra.homework8.model.UserModel
import com.sumeyra.homework8.ui.utils.extension.sent
import com.sumeyra.homework8.ui.utils.extension.showErrorSnackBar
import com.sumeyra.homework8.ui.utils.extension.showToast
import com.sumeyra.homework8.ui.utils.extension.trimmedText


class AddFragment : Fragment() {
    private var _binding: FragmentAddBinding ?= null
    private val binding get() = _binding!!
    private lateinit var arrayAdapter: ArrayAdapter<String>
    private lateinit var addViewModel: AddViewModel
    private var selectedItem:String ="family"


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAddBinding.inflate(layoutInflater,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addViewModel = ViewModelProvider(this)[AddViewModel::class.java]

        val userList = arrayListOf("family","school","friend","work")
        arrayAdapter = ArrayAdapter(requireContext(),R.layout.dropdown_item, userList)
        binding.autoCompleteTextView.setAdapter(arrayAdapter)


        binding.autoCompleteTextView.setOnItemClickListener{ parent, view, position,id ->
            selectedItem = userList[position]
        }


        binding.btnSave.setOnClickListener {
            if(checkFields()){
                val userName = binding.etName.text.toString()
                val userSurname = binding.etSurname.text.toString()
                val userPhone = binding.etPhone.text.toString()
                val userAddress = binding.etAddress.text.toString()


                //Create User Object
                val user = UserModel(user_group = selectedItem, user_name = userName, user_surname = userSurname,
                    user_phone = userPhone, user_address = userAddress)

                addViewModel.addUser(user)

                requireContext().showToast("Successfully Added!!")

                Navigation.sent(it, R.id.action_addFragment_to_nav_home)

            }
        }

    }



    private fun checkFields(): Boolean {
        with(binding) {
            val userName = etName.trimmedText()
            val userSurname = etSurname.trimmedText()
            val userPhone = etPhone.trimmedText()
            val userAddress = etAddress.trimmedText()

            return when {
                userName.isEmpty() -> showError(getString(R.string.user_name_empty) )
                userSurname.isEmpty() -> showError(getString(R.string.user_surname_empty))
                userPhone.isEmpty() -> showError(getString(R.string.user_phone_empty))
                userAddress.isEmpty() -> showError(getString(R.string.user_address_empty))
                else -> true
            }
        }
    }

    private fun showError(errorMsg: String): Boolean {
        requireView().showErrorSnackBar(errorMsg, true)
        return false
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}