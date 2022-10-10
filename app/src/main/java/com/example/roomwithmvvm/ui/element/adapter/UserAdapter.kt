package com.example.roomwithmvvm.ui.element.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.roomwithmvvm.data.local.source.User
import com.example.roomwithmvvm.databinding.CustomRowBinding
import com.example.roomwithmvvm.ui.element.ListFragmentDirections

class UserAdapter : ListAdapter<User, UserAdapter.UserViewHolder>(ItemDiffCallback()) {

    inner class UserViewHolder(private val binding: CustomRowBinding) : RecyclerView.ViewHolder(binding.root) {


        fun bind() {

            val source = getItem(absoluteAdapterPosition)
            binding.textViewID.text  = source.id.toString()
            binding.RVFirstName.text = source.firstName
            binding.RVLastName.text = source.lastname
            binding.RVAge.text = source.age.toString()

            binding.rowLayout.setOnClickListener {
                val action = ListFragmentDirections.actionListFragmentToEditFragment(source) //current item
                binding.rowLayout.findNavController().navigate(action)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CustomRowBinding.inflate(layoutInflater, parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind()
    }


}

private class ItemDiffCallback : DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean =
        oldItem == newItem

}

