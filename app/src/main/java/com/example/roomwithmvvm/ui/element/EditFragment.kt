package com.example.roomwithmvvm.ui.element

import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.roomwithmvvm.data.local.source.User
import com.example.roomwithmvvm.databinding.FragmentEditBinding
import com.example.roomwithmvvm.ui.element.common.BaseFragment
import com.example.roomwithmvvm.ui.viewmodel.AddViewModel
import com.example.roomwithmvvm.ui.viewmodel.EditViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditFragment :  BaseFragment<FragmentEditBinding>(
    FragmentEditBinding::inflate
) {
    private val viewModel: EditViewModel by viewModels()
    private val args by navArgs<EditFragmentArgs>()
    override fun setup() {
    binding.apply {
        editTextTextFirstName.setText(args.currentUser.firstName)
        editTextTextLastName.setText(args.currentUser.lastname)
        editTextAge.setText(args.currentUser.age.toString())
    }
    }

    override fun listeners() {
        binding.apply {
            buttonSave.setOnClickListener {
                val firstName = editTextTextFirstName.text.toString()
                val lastName = editTextTextLastName.text.toString()
                val age = editTextAge.text.toString()
                if(firstName.trim()!="" && lastName.trim()!="" && age.trim()!="") {
                    val user = User(args.currentUser.id, firstName, lastName, age.toInt())
                    viewModel.updateUser(user)
                    goToListFragment()
                }
                else {
                    Toast.makeText(requireContext(), "Fill all fields", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun goToListFragment() {
        findNavController().popBackStack()
    }
}