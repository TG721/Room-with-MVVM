package com.example.roomwithmvvm.ui.viewmodel

import com.example.roomwithmvvm.MainCoroutineRule
import com.example.roomwithmvvm.data.local.repository.FakeUserRepository
import com.example.roomwithmvvm.data.local.repository.RepositoryMethod
import com.example.roomwithmvvm.data.local.source.User
import com.example.roomwithmvvm.domain.usecase.AddUserUseCase
import com.example.roomwithmvvm.domain.usecase.DeleteUserUseCase
import com.example.roomwithmvvm.domain.usecase.GetAllUsersUseCase
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ListViewModelTest {
    private lateinit var repository: FakeUserRepository
    private lateinit var viewModel: ListViewModel

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setup(){
        repository = FakeUserRepository()
        viewModel = ListViewModel(GetAllUsersUseCase(repository), DeleteUserUseCase(repository), AddUserUseCase(repository))
    }

    @Test
    fun `GetAllUsers method gets called`(){
        runTest {
            viewModel.getAllUsers()
        }
        assertThat(repository.hasMethodBeenCalled(RepositoryMethod.GET_USERS)).isTrue()
    }

    @Test
    fun `DeleteUser method gets called`(){
        runTest  {
            viewModel.addUser(User(1, "Goga", "Gioshvili", 27))
            viewModel.deleteUser(User(1, "Goga", "Gioshvili", 27))
        }
        assertThat(repository.hasMethodBeenCalled(RepositoryMethod.DELETE_USER)).isTrue()
    }

    @Test
    fun `AddUser method gets called`(){
        runTest  {
            viewModel.addUser(User(1, "Goga", "Gioshvili", 27))
        }
        assertThat(repository.hasMethodBeenCalled(RepositoryMethod.ADD_USER)).isTrue()
    }


}