package com.arioch.ariochnycschoolsdemo.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.arioch.ariochnycschoolsdemo.model.entity.NYCSchools2022Entity
import com.arioch.ariochnycschoolsdemo.model.entity.NYC_SCHOOLS_2022_TABLE_NAME
import kotlinx.coroutines.flow.Flow

/**
 * Provides access to the NYCSchools2022 table.
 *
 * @author Arioch
 */
@Dao
interface NYCSchools2022DAO {
    /**
     * Returns all the values in the NYCSchools 2022 Entity.
     * @see Query
     *
     * @see NYC_SCHOOLS_2022_TABLE_NAME
     * @return A list of all the values in the NYCSchools 2022 Entity.
     * @see NYCSchools2022Entity
     * @see Flow
     * @see List
     */
    @Query("SELECT * from $NYC_SCHOOLS_2022_TABLE_NAME")
    fun getAllNYCSchools(): Flow<List<NYCSchools2022Entity>>

    /**
     * Returns a list of values that are in the list provided.
     * @see Query
     *
     * @param dbns A list of dbns to search for.
     * @see String
     *
     * @return A list of values that are in the list provided.
     * @see NYCSchools2022Entity
     * @see Flow
     * @see List
     *
     */
    @Query("SELECT * from $NYC_SCHOOLS_2022_TABLE_NAME where dbn IN (:dbns)")
    fun getNYCSchoolsWithDBNs(dbns:List<String>): Flow<List<NYCSchools2022Entity>>

    /**
     * Returns the value of the NYC School that matches the dbn provided.
     * @see Query
     *
     * @param dbn The dbn to search for.
     * @see String
     *
     * @return The value of the NYC School that matches the dbn provided.
     * @see NYCSchools2022Entity
     * @see Flow
     */
    @Query("SELECT * from $NYC_SCHOOLS_2022_TABLE_NAME where dbn=:dbn")
    fun getNYCSchoolWithDBN(dbn: String): Flow<NYCSchools2022Entity>

    /**
     * Inserts all the Schools into the NYC Schools 2022 table.
     * @see Insert
     *
     * @param nycSchools2022 A list of NYCSchools2022Entity to insert.
     * @see NYCSchools2022Entity
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg nycSchools2022: NYCSchools2022Entity)
}