package com.example.roomwithmvvm.ui.viewmodel

import com.example.roomwithmvvm.MainCoroutineRule
import com.example.roomwithmvvm.data.local.repository.FakeUserRepository
import com.example.roomwithmvvm.data.local.repository.RepositoryMethod
import com.example.roomwithmvvm.data.local.source.User
import com.example.roomwithmvvm.domain.usecase.DeleteUserUseCase
import com.example.roomwithmvvm.domain.usecase.UpdateUserUseCase
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class EditViewModelTest {
    private lateinit var viewModel: EditViewModel
    private lateinit var fakeRepository: FakeUserRepository

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setup() {
        // Create a fake repository for testing
        fakeRepository = FakeUserRepository()
        viewModel =
            EditViewModel(UpdateUserUseCase(fakeRepository), DeleteUserUseCase(fakeRepository))
    }

    @Test
    fun `delete method gets called`() {
        val user = User(1, "Nika", "Lomidze", 25)
        runTest {
            fakeRepository.addUser(user)

            viewModel.deleteUser(user)
        }
        assertThat(fakeRepository.hasMethodBeenCalled(RepositoryMethod.DELETE_USER)).isTrue()

    }

    @Test
    fun `update method gets called`() {
        runTest {
            fakeRepository.addUser(User(1, "Nika", "Lomidze", 25))

            viewModel.updateUser(User(1, "Nika", "Asanidze", 29))

        }
        assertThat(fakeRepository.hasMethodBeenCalled(RepositoryMethod.UPDATE_USER)).isTrue()


    }

}