package com.example.roomwithmvvm.ui.element

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.roomwithmvvm.data.local.source.User
import com.example.roomwithmvvm.databinding.FragmentAddBinding
import com.example.roomwithmvvm.ui.element.common.BaseFragment
import com.example.roomwithmvvm.ui.viewmodel.AddViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AddFragment :  BaseFragment<FragmentAddBinding>(FragmentAddBinding::inflate) {
    private val viewModel: AddViewModel by viewModels()
    override fun setup() {

    }

    override fun listeners() {
        binding.apply {
            buttonAdd.setOnClickListener {
                val firstName = editTextTextFirstName.text.toString()
                val lastName = editTextTextLastName.text.toString()
                val age = editTextAge.text.toString().toInt()
                val user = User(null,firstName, lastName, age)
                viewModel.addUser(user)
                goToListFragment()
            }
        }
    }
    private fun goToListFragment() {
        findNavController().popBackStack()
    }
}