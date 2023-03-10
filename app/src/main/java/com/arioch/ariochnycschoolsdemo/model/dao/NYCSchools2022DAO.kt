package com.arioch.ariochnycschoolsdemo.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.arioch.ariochnycschoolsdemo.model.entity.NYCSchools2018Entity
import kotlinx.coroutines.flow.Flow

@Dao
interface NYCSchools2022DAO {
    /**
     * Returns all the values in the NYCSchools 2018 Entity.
     */
    @Query("SELECT * from NYCSchools2018")
    fun getAllNYCSchools(): Flow<List<NYCSchools2018Entity>>

    /**
     * Returns a list of values that are in the list provided.
     */
    @Query("SELECT * from NYCSchools2018 where dbn IN (:dbns)")
    fun getNYCSchoolsWithDBNs(dbns:List<String>): Flow<List<NYCSchools2018Entity>>

    /**
     * Returns the value of the NYC School that matches the dbn provided.
     */
    @Query("SELECT * from NYCSchools2018 where dbn=:dbn")

    fun getNYCSchoolWithDBN(dbn: String): Flow<NYCSchools2018Entity>

    /**
     * Inserts all the Schools into the NYC Schools 2018 table.
     */
    @Insert
    fun insertAll(vararg nycSchools2018: NYCSchools2018Entity)
}