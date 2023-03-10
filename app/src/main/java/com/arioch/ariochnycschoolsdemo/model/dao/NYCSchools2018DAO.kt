package com.arioch.ariochnycschoolsdemo.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.arioch.ariochnycschoolsdemo.model.entity.NYCSchools2018Entity
import kotlinx.coroutines.flow.Flow

/**
 * Provides access to the NYCSchools2018 table.
 *
 * @author Arioch
 */
@Dao
interface NYCSchools2018DAO {

    /**
     * Returns all the values in the NYCSchools 2018 Entity.
     * @see Query
     *
     * @return A list of all the values in the NYCSchools 2018 Entity.
     * @see NYCSchools2018Entity
     * @see Flow
     * @see List
     */
    @Query("SELECT * from NYCSchools2018")
    fun getAllNYCSchools(): Flow<List<NYCSchools2018Entity>>

    /**
     * Returns a list of values that are in the list provided.
     * @see Query
     *
     * @param dbns A list of dbns to search for.
     *
     * @return A list of values that are in the list provided.
     * @see NYCSchools2018Entity
     * @see Flow
     * @see List
     */
    @Query("SELECT * from NYCSchools2018 where dbn IN (:dbns)")
    fun getNYCSchoolsWithDBNs(dbns:List<String>): Flow<List<NYCSchools2018Entity>>

    /**
     * Returns the value of the NYC School that matches the dbn provided.
     * @see Query
     *
     * @param dbn The dbn to search for.
     * @see String
     *
     * @return The value of the NYC School that matches the dbn provided.
     * @see NYCSchools2018Entity
     * @see Flow
     */
    @Query("SELECT * from NYCSchools2018 where dbn=:dbn")
    fun getNYCSchoolWithDBN(dbn: String): Flow<NYCSchools2018Entity>

    /**
     * Inserts all the Schools into the NYC Schools 2018 table.
     * @see Insert
     *
     * @param nycSchools2018 A list of NYCSchools2018Entity to insert.
     * @see NYCSchools2018Entity
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg nycSchools2018: NYCSchools2018Entity)
}