package com.example.roomwithmvvm.ui.viewmodel

import com.example.roomwithmvvm.MainCoroutineRule
import com.example.roomwithmvvm.data.local.repository.FakeUserRepository
import com.example.roomwithmvvm.data.local.repository.RepositoryMethod
import com.example.roomwithmvvm.data.local.source.User
import com.example.roomwithmvvm.domain.usecase.AddUserUseCase
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class AddViewModelTest {
    private lateinit var viewModel: AddViewModel
    private lateinit var fakeRepository : FakeUserRepository

@get:Rule
var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setup(){
        // Create a fake repository for testing
        fakeRepository = FakeUserRepository()
        viewModel = AddViewModel(AddUserUseCase(fakeRepository))
    }

//    inside ViewModel we use coroutine that by default uses Main dispatcher
//    in tests we don't have MainCoroutineDispatcher (which relies on MainLooper)
//    we would not have this problem if we wrote tests in AndroidTestDirectory
//    To solve this problem we need to define our own JUnit Rule
    @Test
    fun `insertUser method gets called`()  {
        runTest{viewModel.addUser(User(1, "Nika", "Lomidze", 25))}
        assertThat(fakeRepository.hasMethodBeenCalled(RepositoryMethod.ADD_USER)).isTrue()

    }
}