package com.arioch.ariochnycschoolsdemo.model.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.arioch.ariochnycschoolsdemo.model.dao.NYCSchools2018DAO
import com.arioch.ariochnycschoolsdemo.model.dao.NYCSchools2022DAO
import com.arioch.ariochnycschoolsdemo.model.dao.NYCSchoolsViewDAO
import com.arioch.ariochnycschoolsdemo.model.entity.NYCSchools2018Entity
import com.arioch.ariochnycschoolsdemo.model.entity.NYCSchools2022Entity
import com.arioch.ariochnycschoolsdemo.model.view.NYCSchoolsView

/**
 * Defines the database for use in the application.
 *
 * exportSchema is set to false as this is a demo app. Set that to true before releasing the prod.
 *
 * @see Database
 * @see RoomDatabase
 * @see NYCSchools2018DAO
 * @see NYCSchools2022DAO
 * @see NYCSchools2018Entity
 * @see NYCSchools2022Entity
 *
 * @author Arioch
 */
@Database(entities = [NYCSchools2018Entity::class,NYCSchools2022Entity::class],
    views = [NYCSchoolsView::class],
    version = 1,
    exportSchema = false
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

    /**
     * Provides access to the [NYCSchoolsViewDAO]
     */
    abstract fun getNYCSchoolsViewDAO(): NYCSchoolsViewDAO

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