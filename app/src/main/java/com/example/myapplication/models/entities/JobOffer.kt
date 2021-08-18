package com.example.myapplication.models.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "job_offers")
data class JobOffer(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "company_id") val companyId: Int,
    @ColumnInfo val city: String,
    @ColumnInfo val address: String,
    @ColumnInfo val closed: Boolean
): Parcelable