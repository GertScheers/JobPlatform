package com.example.myapplication.models.entities

import android.os.Parcelable
import androidx.room.*
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "job_platform_users")
data class User(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "first_name") val firstName: String,
    @ColumnInfo(name = "last_name") val lastName: String,
    @ColumnInfo val type: Int,
    @ColumnInfo val resume: String,
    @ColumnInfo val email: String,
    @ColumnInfo val password: String
) : Parcelable

enum class UserType {
    Company,
    Candidate
}

class UserTypeIntConverter {
    @TypeConverter
    fun IntToUserType(int: Int): UserType {
        when (int) {
            0 -> return UserType.Company
            1 -> return UserType.Candidate
        }
        return UserType.Candidate
    }

    @TypeConverter
    fun UserTypeToInt(userType: UserType): Int {
        when (userType) {
            UserType.Company -> return 0
            UserType.Candidate -> return 1
        }
    }
}