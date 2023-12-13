package com.example.roomwithmvvm.data.local

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.roomwithmvvm.data.local.source.User
import com.example.roomwithmvvm.data.local.source.UserDao
import com.example.roomwithmvvm.data.local.source.UserDatabase
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject
import javax.inject.Named

//@RunWith(AndroidJUnit4::class) removed because we specify our own HiltTestRunner
@SmallTest
@HiltAndroidTest
class UserDaoTest {

    @get:Rule
    var hiltRule= HiltAndroidRule(this)

    @Inject
    @Named("test_db")
    lateinit var database: UserDatabase //removed private modifier because we can't inject into private variables (unless they are in constructor)
    private lateinit var dao: UserDao

    @Before
    fun setup() {
        hiltRule.inject() //this inject command will make hilt inject all dependencies in this class with @Inject annotation
        dao = database.userDao()
    }

    @After
    fun cleanup() {
        database.close()
    }

    @Test
    fun insertUser() = runTest {
//        runBlocking - executes coroutine in mainThread
//        runBlocking coroutine builder is designed to create a coroutine and block the current thread until the coroutine completes
//        runBlockingTest is optimised for testing (Deprecated, should use runTest instead)
//        runTest executes testBody as a test in a new coroutine, returning TestResult ( behaves similarly to runBlocking, with the difference that the code that it runs will skip delays.)
        val user = User(1, "Nika", "Morchiladze", 24)
        dao.addUser(user)

        val allUsers: List<User> = dao.readAllData().first()

        assertThat(allUsers).contains(user)

    }

    @Test
    fun deleteUser() = runTest {
        val user = User(1, "Nika", "Morchiladze", 24)
        dao.addUser(user)

        dao.delete(user)
        val allUsers: List<User> = dao.readAllData().first()
        assertThat(allUsers).hasSize(0)
//        assertThat(allUsers).doesNotContain(user)
    }

    @Test
    fun updateUser() = runTest{
        val user = User(1, "Nika", "Morchiladze", 24)
        dao.addUser(user)
        val updatedUser = User(1, "Nika", "Rodonaia", 35)
        dao.updateUser(updatedUser)
        val allUsers: List<User> = dao.readAllData().first()
        assertThat(allUsers).contains(updatedUser)

    }

    @Test
    fun getAllUsers() = runTest{
        val user1 = User(1, "Nika", "Morchiladze", 24)
        dao.addUser(user1)
        val user2 = User(2, "Lia", "Maisuradze", 24)
        dao.addUser(user2)
        val user3 = User(3, "Anri", "Sudadze", 24)
        dao.addUser(user3)
        val user4 = User(4, "Giorgi", "Avaliani", 24)
        dao.addUser(user4)
        dao.delete(user3)

        val allUsers: List<User> = dao.readAllData().first()

        assertThat(allUsers).hasSize(3)
    }

    @Test
    fun deleteAllUsers() = runTest {
        val user1 = User(1, "Nika", "Morchiladze", 24)
        dao.addUser(user1)
        val user2 = User(2, "Lia", "Maisuradze", 24)
        dao.addUser(user2)
        val user3 = User(3, "Anri", "Sudadze", 24)
        dao.addUser(user3)
        val user4 = User(4, "Giorgi", "Avaliani", 24)
        dao.addUser(user4)

        dao.deleteAll()

        val allUsers: List<User> = dao.readAllData().first()
        assertThat(allUsers).hasSize(0)
    }
}