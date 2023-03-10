package com.arioch.ariochnycschoolsdemo.model.dao

import androidx.room.Dao
import androidx.room.Query
import com.arioch.ariochnycschoolsdemo.model.view.NYCSchoolsView
import kotlinx.coroutines.flow.Flow

/**
 * Provides Read operations on the "NYCSchoolsView" table.
 *
 * @author Arioch
 */
@Dao
interface NYCSchoolsViewDAO {
    /**
     * Gets all the values in the "NYCSchoolsView" table.
     * @see Query
     *
     * @return A list of all the values in the "NYCSchoolsView" table.
     * @see NYCSchoolsView
     * @see Flow
     * @see List
     *
     */
    @Query("SELECT * FROM NYCSchoolsView")
    fun getAllNYCSchools(): Flow<List<NYCSchoolsView>>
}