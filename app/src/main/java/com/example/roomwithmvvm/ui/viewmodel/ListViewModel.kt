package com.example.roomwithmvvm.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomwithmvvm.data.local.source.User
import com.example.roomwithmvvm.domain.usecase.AddUserUseCase
import com.example.roomwithmvvm.domain.usecase.DeleteUserUseCase
import com.example.roomwithmvvm.domain.usecase.GetAllUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(private val getAllUsersUseCase: GetAllUsersUseCase,
                                        private val deleteUserUseCase: DeleteUserUseCase,
                                        private val addUseCase: AddUserUseCase
) : ViewModel() {

    lateinit var users: Flow<List<User>>

    fun getAllUsers(){
        viewModelScope.launch {
           users = getAllUsersUseCase.getAllUsers()

        }
    }
    fun deleteUser(user: User){
        viewModelScope.launch {
            deleteUserUseCase.deleteUser(user)
        }
    }
    fun addUser(user: User){
        viewModelScope.launch {
            addUseCase.addUser(user)
        }
    }


}