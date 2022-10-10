package com.example.roomwithmvvm.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomwithmvvm.data.local.source.User
import com.example.roomwithmvvm.domain.usecase.AddUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddViewModel @Inject constructor(private val addUseCase: AddUserUseCase) : ViewModel() {

    fun addUser(user: User){
        viewModelScope.launch {
            addUseCase.addUser(user)
        }
    }

}