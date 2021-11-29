package com.example.myapplication.models.database.jobOffer

import androidx.annotation.WorkerThread
import com.example.myapplication.models.database.user.UserRepository
import com.example.myapplication.models.entities.JobOffer
import kotlinx.coroutines.flow.Flow

class JobOfferRepository(private val jobOfferDao: JobOfferDao,
private val userRepository: UserRepository) {
    @WorkerThread
    suspend fun insertJobOffer(jobOffer: JobOffer) {
        jobOfferDao.insertJobOffer(jobOffer)
    }

    val allJobOffers: Flow<List<JobOffer>> = jobOfferDao.getJobOffers()
    val companyJobOffers: Flow<List<JobOffer>> = jobOfferDao.getJobOffersForCompany(userRepository.currentUserFlow.value!!.userId)
}