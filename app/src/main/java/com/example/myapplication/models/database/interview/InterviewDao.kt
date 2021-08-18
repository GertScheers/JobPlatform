package com.example.myapplication.models.database.interview

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.myapplication.models.entities.Interview
import kotlinx.coroutines.flow.Flow

@Dao
interface InterviewDao {
    @Insert
    suspend fun insertInterview(interview: Interview)

    @Query("SELECT * FROM INTERVIEWS WHERE candidate_id = :candidateId")
    fun getInterviewsForCandidate(candidateId: Int) : Flow<List<Interview>>

    @Query("SELECT * FROM interviews WHERE company_id = :companyId")
    fun getInterviewsForCompany(companyId: Int) : Flow<List<Interview>>
}