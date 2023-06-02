package com.sumeyra.homework8.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sumeyra.homework8.databinding.ItemHomeBinding
import com.sumeyra.homework8.model.UserModel

class HomeAdapter(private val onClick: (UserModel)->Unit ={}) : RecyclerView.Adapter<HomeAdapter.HomeHolder>() {
    private var userList = emptyList<UserModel>()


    inner class HomeHolder(private var binding: ItemHomeBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(userModel:UserModel)= with(binding){
            tvName.text = userModel.user_name
            tvSurname.text = userModel.user_surname
            tvGroup.text=userModel.user_group
            tvPhone.text=userModel.user_phone
            tvAddress.text=userModel.user_address
            root.setOnClickListener {
                onClick(userModel)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= HomeHolder(
        ItemHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false))



    override fun onBindViewHolder(holder: HomeHolder, position: Int) =
        holder.bind(userList[position])


    fun setData(user: List<UserModel>) {
        this.userList = if (user.size > 10) { // last added 10 people
            user.subList(user.size - 10, user.size)
        } else {
            user
        }
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return userList.size
    }
}
