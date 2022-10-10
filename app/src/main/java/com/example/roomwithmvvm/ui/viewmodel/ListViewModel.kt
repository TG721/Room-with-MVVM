package com.example.roomwithmvvm.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomwithmvvm.data.local.source.User
import com.example.roomwithmvvm.domain.usecase.DeleteUserUseCase
import com.example.roomwithmvvm.domain.usecase.GetAllUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(private val getAllUsersUseCase: GetAllUsersUseCase) : ViewModel() {

    lateinit var users: Flow<List<User>>

    fun getAllUsers(){
        viewModelScope.launch {
           users = getAllUsersUseCase.getAllUsers()

        }
    }


}