package com.example.roomwithmvvm.ui.element

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roomwithmvvm.databinding.FragmentListBinding
import com.example.roomwithmvvm.ui.element.adapter.UserAdapter
import com.example.roomwithmvvm.ui.element.common.BaseFragment
import com.example.roomwithmvvm.ui.viewmodel.ListViewModel
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
        }
    }

    private fun goToAddFragment() {
        findNavController().navigate(
            ListFragmentDirections.actionListFragmentToAddFragment()
        )
    }
}