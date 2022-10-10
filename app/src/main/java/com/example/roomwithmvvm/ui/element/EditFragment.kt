package com.example.roomwithmvvm.ui.element

import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.roomwithmvvm.data.local.source.User
import com.example.roomwithmvvm.databinding.FragmentEditBinding
import com.example.roomwithmvvm.ui.element.common.BaseFragment
import com.example.roomwithmvvm.ui.viewmodel.EditViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.properties.Delegates

@AndroidEntryPoint
class EditFragment :  BaseFragment<FragmentEditBinding>(
    FragmentEditBinding::inflate
) {
    private val viewModel: EditViewModel by viewModels()
    private val args by navArgs<EditFragmentArgs>()
    private var currUserID by Delegates.notNull<Int>()
    private lateinit var currUserFirstName : String
    private lateinit var currUserLastName : String
    private var currUserAge by Delegates.notNull<Int>()

    override fun setup() {
         currUserID = args.currentUser.id!!
         currUserFirstName= args.currentUser.firstName
         currUserLastName = args.currentUser.lastname
         currUserAge = args.currentUser.age

        binding.apply {
        editTextTextFirstName.setText(currUserFirstName)
        editTextTextLastName.setText(currUserLastName)
        editTextAge.setText(currUserAge.toString())
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
            buttonDelete.setOnClickListener {
                viewModel.deleteUser(User(args.currentUser.id,currUserFirstName,currUserLastName,currUserAge))
                goToListFragment()
            }
        }
    }

    private fun goToListFragment() {
        findNavController().popBackStack()
    }
}