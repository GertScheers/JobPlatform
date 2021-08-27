package com.example.myapplication.models.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import androidx.room.*


@Parcelize
@Entity(tableName = "interviews")
data class Interview(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "candidate_id") val candidateId: Int,
    @ColumnInfo(name = "company_id") val companyId: Int,
    @ColumnInfo(name = "job_offer_id") val jobOfferId: Int,
    @ColumnInfo(defaultValue = "0") val status: Int,
    @ColumnInfo val feedBack: String?
    ) : Parcelable

enum class InterviewStatus {
    Open,
    Confirmed,
    Rejected,
    Closed
}

class StatusToIntConverter {
    @TypeConverter
    fun intToStatus(int: Int): InterviewStatus {
        when (int) {
            0 -> return InterviewStatus.Open
            1 -> return InterviewStatus.Confirmed
            2 -> return InterviewStatus.Rejected
            3 -> return InterviewStatus.Closed
        }
        return InterviewStatus.Open
    }

    @TypeConverter
    fun statusToInt(status: InterviewStatus): Int {
        return when (status) {
            InterviewStatus.Open -> 0
            InterviewStatus.Confirmed -> 1
            InterviewStatus.Rejected -> 2
            InterviewStatus.Closed -> 3
        }
    }
}