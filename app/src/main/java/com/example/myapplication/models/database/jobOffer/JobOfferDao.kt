package com.example.myapplication.models.database.jobOffer

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.myapplication.models.entities.JobOffer
import kotlinx.coroutines.flow.Flow

@Dao
interface JobOfferDao {
    @Insert
    suspend fun insertJobOffer(jobOffer: JobOffer)

    @Query("SELECT * FROM job_offers WHERE NOT closed")
    fun getJobOffers() : Flow<List<JobOffer>>

    @Query("SELECT * FROM job_offers WHERE company_id = :companyId")
    fun getJobOffersForCompany(companyId: Int) : Flow<List<JobOffer>>
}