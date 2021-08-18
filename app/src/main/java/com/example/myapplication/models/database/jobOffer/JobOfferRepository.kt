package com.example.myapplication.models.database.jobOffer

import androidx.annotation.WorkerThread
import com.example.myapplication.models.entities.JobOffer
import kotlinx.coroutines.flow.Flow

class JobOfferRepository(private val jobOfferDao: JobOfferDao) {
    @WorkerThread
    suspend fun insertJobOffer(jobOffer: JobOffer) {
        jobOfferDao.insertJobOffer(jobOffer)
    }

    val allJobOffers: Flow<List<JobOffer>> = jobOfferDao.getJobOffers()
    //TODO: Replace hardcoded ID with actual ID filtering
    val companyJobOffers: Flow<List<JobOffer>> = jobOfferDao.getJobOffersForCompany(0)
}