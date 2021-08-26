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
import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import androidx.sqlite.db.SupportSQLiteDatabase


@Database(entities = [User::class, JobOffer::class, Interview::class], version = 1)
@TypeConverters(StatusToIntConverter::class, UserTypeIntConverter::class, DateConverter::class)
abstract class JobHuntRoomDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun jobOfferDao(): JobOfferDao
    abstract fun interviewDao(): InterviewDao

    companion object {
        @Volatile
        private var INSTANCE: JobHuntRoomDatabase? = null
        private val mutex = Mutex()

        suspend fun getDatabase(context: Context): JobHuntRoomDatabase {
            //IF THE INSTANCE IS NOT NULL, RETURN IT
            //IF IT IS NULL, CREATE THE DATABASE
            return INSTANCE ?: mutex.withLock {
                var dbCallback: Callback = object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        MainScope().launch(Dispatchers.IO) {
                            seedData()
                        }
                    }
                }
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    JobHuntRoomDatabase::class.java,
                    "jobhunt_database"
                )
                    .addCallback(dbCallback)
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance

                instance
            }
        }

        private suspend fun seedData() {
            //region seeding.users
            INSTANCE!!.userDao().insertUser(
                User(
                    0,
                    "first",
                    "user",
                    "",
                    "Lommel",
                    null,
                    0,
                    "",
                    "first@user.be",
                    "test"
                )
            )

            INSTANCE!!.userDao().insertUser(
                User(
                    0,
                    "second",
                    "user",
                    "",
                    "Pelt",
                    null,
                    0,
                    "",
                    "second@user.be",
                    "test"
                )
            )

            INSTANCE!!.userDao().insertUser(
                User(
                    0,
                    "third",
                    "user",
                    "",
                    "Mol",
                    null,
                    0,
                    "",
                    "third@user.be",
                    "test"
                )
            )

            INSTANCE!!.userDao().insertUser(
                User(
                    0,
                    "fourth",
                    "user",
                    "",
                    "Geel",
                    null,
                    0,
                    "",
                    "fourth@user.be",
                    "test"
                )
            )

            INSTANCE!!.userDao().insertUser(
                User(
                    0,
                    "",
                    "",
                    "Pepsi",
                    "Hasselt",
                    null,
                    1,
                    "",
                    "Pepsi@Hasselt.be",
                    "pepsi"
                )
            )

            INSTANCE!!.userDao().insertUser(
                User(
                    0,
                    "",
                    "",
                    "Cola",
                    "Hasselt",
                    null,
                    1,
                    "",
                    "Cola@Hasselt.be",
                    "cola"
                )
            )

            INSTANCE!!.userDao().insertUser(
                User(
                    0,
                    "",
                    "",
                    "IKEA",
                    "Hasselt",
                    null,
                    1,
                    "",
                    "Ikea@Hasselt.be",
                    "ikea"
                )
            )

            //endregion seeding.users
        }
    }
}