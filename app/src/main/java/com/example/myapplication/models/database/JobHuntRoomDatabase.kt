package com.example.myapplication.models.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.myapplication.models.database.interview.InterviewDao
import com.example.myapplication.models.database.jobOffer.JobOfferDao
import com.example.myapplication.models.database.user.UserDao
import com.example.myapplication.models.entities.*

@Database(entities = [User::class, JobOffer::class, Interview::class], version = 1)
@TypeConverters(StatusToIntConverter::class, UserTypeIntConverter::class)
abstract class JobHuntRoomDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun jobOfferDao(): JobOfferDao
    abstract fun interviewDao(): InterviewDao

    companion object {
        @Volatile
        private var INSTANCE: JobHuntRoomDatabase? = null

        fun getDatabase(context: Context): JobHuntRoomDatabase {
            //IF THE INSTANCE IS NOT NULL, RETURN IT
            //IF IT IS NULL, CREATE THE DATABASE
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    JobHuntRoomDatabase::class.java,
                    "fav_dish_database"
                ).fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}