package com.example.roomwithmvvm.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomwithmvvm.data.local.source.User
import com.example.roomwithmvvm.domain.usecase.AddUserUseCase
import com.example.roomwithmvvm.domain.usecase.DeleteUserUseCase
import com.example.roomwithmvvm.domain.usecase.UpdateUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditViewModel @Inject constructor(private val updateUserUseCase: UpdateUserUseCase,
                                        private val deleteUserUseCase: DeleteUserUseCase
) : ViewModel() {
    fun updateUser(user: User) {
        viewModelScope.launch {
        updateUserUseCase.updateUserUseCase(user)
     }
    }

    fun deleteUser(user: User){
        viewModelScope.launch {
            deleteUserUseCase.deleteUser(user)
        }
    }
}