package com.example.roomwithmvvm.di

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.roomwithmvvm.data.local.source.UserDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object TestAppModule {

    @Provides
    @Named("test_db")
//    no @Singleton because in test cases we want to create new instance for every test case
    fun provideInMemoryDb(@ApplicationContext context: Context) = Room.inMemoryDatabaseBuilder(
        context,
        UserDatabase::class.java
    )
        .allowMainThreadQueries() //we allow access to database from main thread (all actions are executed one after another)
        .build()
    //multithreading would cause dependent test cases (threads would manipulate each other)

}


//If you use @Named in only one module and not in the other, Dagger Hilt will treat the bindings without @Named as separate from those with @Named.
//In this case I only used @Named in AppTestModule
//I could have used it in AppModule too like @Named("appDatabase")
//but in this case it does not make any difference