package com.example.roomwithmvvm.di

import android.content.Context
import androidx.room.Room
import com.example.roomwithmvvm.data.local.source.UserDao
import com.example.roomwithmvvm.data.local.source.UserDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) : UserDatabase {
        return Room.databaseBuilder(
            context,
            UserDatabase::class.java, "user_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideUserDao(database: UserDatabase) : UserDao {
        return database.userDao()
    }


}