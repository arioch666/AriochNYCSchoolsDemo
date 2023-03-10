package com.arioch.ariochnycschoolsdemo.model.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.arioch.ariochnycschoolsdemo.model.dao.NYCSchools2018DAO
import com.arioch.ariochnycschoolsdemo.model.dao.NYCSchools2022DAO
import com.arioch.ariochnycschoolsdemo.model.entity.NYCSchools2018Entity
import com.arioch.ariochnycschoolsdemo.model.entity.NYCSchools2022Entity

/**
 * Defines the database for use in the application.
 *
 * exportSchema is set to false as this is a demo app. Set that to true before releasing the prod.
 */
@Database(entities = [NYCSchools2018Entity::class, NYCSchools2022Entity::class], version = 1, exportSchema = false
)
abstract class AriochNYCSchoolsDemoDB: RoomDatabase() {

    /**
     * Provides access to the [NYCSchools2018DAO].
     */
    abstract fun getNYCSchools2018DAO(): NYCSchools2018DAO

    /**
     * Provides access to the [NYCSchools2022DAO]
     */
    abstract fun getNYCSchools2022DAO(): NYCSchools2022DAO

    companion object {

        @Volatile
        private var INSTANCE: AriochNYCSchoolsDemoDB? = null

        private const val DBName = "arioch_nyc_schools_db"

        fun getDatabase(context: Context): AriochNYCSchoolsDemoDB {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(context = context, AriochNYCSchoolsDemoDB::class.java, DBName).build()
                INSTANCE = instance
                instance
            }
        }
    }
}