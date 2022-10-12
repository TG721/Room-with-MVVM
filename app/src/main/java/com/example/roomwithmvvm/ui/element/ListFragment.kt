package com.example.roomwithmvvm.ui.element

import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roomwithmvvm.data.local.source.User
import com.example.roomwithmvvm.databinding.FragmentListBinding
import com.example.roomwithmvvm.ui.element.adapter.UserAdapter
import com.example.roomwithmvvm.ui.element.common.BaseFragment
import com.example.roomwithmvvm.ui.element.common.SwipeGesture
import com.example.roomwithmvvm.ui.viewmodel.ListViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ListFragment :  BaseFragment<FragmentListBinding>(
    FragmentListBinding::inflate
) {
    val viewModel: ListViewModel by viewModels()
    private lateinit var adapter: UserAdapter

    override fun setup() {
        adapter = UserAdapter()
        val recyclerView = binding.recyclerViewMain
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel.getAllUsers()

    }

    override fun observers() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.users.collect{
                    adapter.submitList(it)
                }

            }
        }
    }

    override fun listeners() {
        binding.apply {
            buttonAdd.setOnClickListener {
            goToAddFragment()
            }

            lateinit var userID: String
            lateinit var userFirstName: String
            lateinit var userLastName: String
            lateinit var userAge: String
            lateinit var currentUser: User
            val swipeGesture = object : SwipeGesture(requireContext()){
                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                             userID = ((viewHolder.itemView as androidx.constraintlayout.widget.ConstraintLayout).getChildAt(3) as TextView).text.toString()
                             userFirstName = ((viewHolder.itemView as androidx.constraintlayout.widget.ConstraintLayout).getChildAt(0) as TextView).text.toString()
                             userLastName = ((viewHolder.itemView as androidx.constraintlayout.widget.ConstraintLayout).getChildAt(1) as TextView).text.toString()
                             userAge = ((viewHolder.itemView as androidx.constraintlayout.widget.ConstraintLayout).getChildAt(2) as TextView).text.toString()
                             currentUser = User(userID.toInt(), userFirstName, userLastName, userAge.toInt())
                             viewModel.deleteUser(currentUser)
                    Snackbar.make(binding.root,"1 user was removed", Snackbar.LENGTH_SHORT).apply {
                        setAction("Undo"){
                            viewModel.addUser(currentUser)
                        }
                    }.show()
                }
            }
            val touchHelper = ItemTouchHelper(swipeGesture)
            touchHelper.attachToRecyclerView(recyclerViewMain)

        }
    }

    private fun goToAddFragment() {
        findNavController().navigate(
            ListFragmentDirections.actionListFragmentToAddFragment()
        )
    }
}