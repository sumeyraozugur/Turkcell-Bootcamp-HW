package com.sumeyra.homework8.ui.group

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sumeyra.homework8.databinding.ItemHomeBinding
import com.sumeyra.homework8.model.UserModel

class GroupAdapter() : RecyclerView.Adapter<GroupAdapter.GroupHolder>() {
    private var userList = emptyList<UserModel>()


    inner class GroupHolder(private var binding: ItemHomeBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(userModel: UserModel)= with(binding){
            tvName.text = userModel.user_name
            tvSurname.text = userModel.user_surname
            tvGroup.text=userModel.user_group
            tvPhone.text=userModel.user_phone
            tvAddress.text=userModel.user_address
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= GroupHolder(
        ItemHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false))



    override fun onBindViewHolder(holder: GroupHolder, position: Int) =
        holder.bind(userList[position])


    fun setData(user: List<UserModel>) {
        this.userList = user
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int {
        return userList.size
    }
}