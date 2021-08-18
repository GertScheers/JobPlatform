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
    @ColumnInfo(defaultValue = "Open") val status: InterviewStatus,
    @ColumnInfo val feedBack: String?
    ) : Parcelable

enum class InterviewStatus {
    Open,
    Confirmed,
    Rejected,
    Closed
}