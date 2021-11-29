package com.example.myapplication.ui.joboffers

import androidx.lifecycle.*
import com.example.myapplication.models.database.jobOffer.JobOfferRepository
import com.example.myapplication.models.database.user.UserRepository
import com.example.myapplication.ui.data.model.LoggedInUser
import com.example.myapplication.ui.profile.MyProfileViewModel
import java.lang.IllegalArgumentException

class JobOfferViewModel(
    private val userRepository: UserRepository,
    private val jobOfferRepository: JobOfferRepository
) : ViewModel() {
    val user: LoggedInUser? = userRepository.currentUserFlow.value

    val allJobOffers = jobOfferRepository.allJobOffers.asLiveData()

    val companyJobOffers = jobOfferRepository.companyJobOffers.asLiveData()
}

class JobOfferViewModelFactory(
    private val userRepository: UserRepository,
    private val jobOfferRepository: JobOfferRepository
) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MyProfileViewModel::class.java)) {
            return JobOfferViewModel(userRepository, jobOfferRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}