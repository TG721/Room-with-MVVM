package com.example.roomwithmvvm.data.local.source

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "user_table")
data class User (
        @PrimaryKey(autoGenerate = true) val  id: Int?,
        @ColumnInfo(name = "first_name")  val firstName: String,
        @ColumnInfo(name = "last_name") val lastname: String,
        val age: Int
        ) : Parcelable