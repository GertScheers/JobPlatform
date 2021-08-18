package com.example.myapplication.models.database.interview

import androidx.annotation.WorkerThread
import com.example.myapplication.models.entities.Interview
import kotlinx.coroutines.flow.Flow

class InterviewRepository(private val interviewDao: InterviewDao) {
    @WorkerThread
    suspend fun insertInterview(interview: Interview){
        interviewDao.insertInterview(interview)
    }

    //TODO: Replace hardcoded ID with actual ID filtering
    val candidateInterviews: Flow<List<Interview>> = interviewDao.getInterviewsForCandidate(0)
    val companyInterviews: Flow<List<Interview>> = interviewDao.getInterviewsForCompany(0)
}