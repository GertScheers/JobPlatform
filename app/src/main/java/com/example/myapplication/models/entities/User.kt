package com.example.myapplication.models.entities

import android.os.Parcelable
import androidx.room.*
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
@Entity(tableName = "job_platform_users")
data class User(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "first_name") val firstName: String,
    @ColumnInfo(name = "last_name") val lastName: String,
    @ColumnInfo(name = "company_name") val companyName: String,
    @ColumnInfo val city: String,
    @ColumnInfo(name = "date_of_birth") val dateOfBirth: Long?,
    @ColumnInfo val type: Int,
    @ColumnInfo val resume: String,
    @ColumnInfo val email: String,
    @ColumnInfo val password: String,
) : Parcelable {
    @Ignore
    val displayName: String = if (type == 0) {
        "$firstName $lastName"
    } else companyName
}

enum class UserType {
    Company,
    Candidate
}

class UserTypeIntConverter {
    @TypeConverter
    fun intToUserType(int: Int): UserType {
        when (int) {
            0 -> return UserType.Company
            1 -> return UserType.Candidate
        }
        return UserType.Candidate
    }

    @TypeConverter
    fun userTypeToInt(userType: UserType): Int {
        return when (userType) {
            UserType.Company -> 0
            UserType.Candidate -> 1
        }
    }
}

class DateConverter {
    @TypeConverter
    fun toDate(dateLong: Long?): Date? {
        return dateLong?.let { Date(it) }
    }

    @TypeConverter
    fun fromDate(date: Date?): Long? {
        return date?.time
    }
}