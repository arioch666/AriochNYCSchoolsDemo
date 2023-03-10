package com.arioch.ariochnycschoolsdemo.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.arioch.ariochnycschoolsdemo.model.entity.NYCSchools2022Entity
import com.arioch.ariochnycschoolsdemo.model.entity.NYC_SCHOOLS_2022_TABLE_NAME
import kotlinx.coroutines.flow.Flow

@Dao
interface NYCSchools2022DAO {
    /**
     * Returns all the values in the NYCSchools 2022 Entity.
     */
    @Query("SELECT * from $NYC_SCHOOLS_2022_TABLE_NAME")
    fun getAllNYCSchools(): Flow<List<NYCSchools2022Entity>>

    /**
     * Returns a list of values that are in the list provided.
     */
    @Query("SELECT * from $NYC_SCHOOLS_2022_TABLE_NAME where dbn IN (:dbns)")
    fun getNYCSchoolsWithDBNs(dbns:List<String>): Flow<List<NYCSchools2022Entity>>

    /**
     * Returns the value of the NYC School that matches the dbn provided.
     */
    @Query("SELECT * from $NYC_SCHOOLS_2022_TABLE_NAME where dbn=:dbn")

    fun getNYCSchoolWithDBN(dbn: String): Flow<NYCSchools2022Entity>

    /**
     * Inserts all the Schools into the NYC Schools 2022 table.
     */
    @Insert
    fun insertAll(vararg nycSchools2022: NYCSchools2022Entity)
}