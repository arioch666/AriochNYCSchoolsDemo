package com.arioch.ariochnycschoolsdemo.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

const val NYC_SCHOOLS_2018_TABLE_NAME = "NYCSchools2018"

/**
 * Represents the NYCSchools2018 table in the DB.
 *
 * @param boro The borough code for the school.
 * @param borough The borough name for the school.
 * @param city The city name for the school.
 * @param dbn The unique identifier for the school.
 * @param latitude The latitude of the school.
 * @param location The location of the school.
 * @param longitude The longitude of the school.
 * @param neighborhood The neighborhood of the school.
 * @param phoneNumber The phone number of the school.
 * @param schoolName The name of the school.
 * @param startTime The start time of the school.
 * @param stateCode The state code of the school.
 * @param totalStudents The total number of students at the school.
 * @param website The website of the school.
 * @param zip The zip code of the school.
 *
 * @author Arioch
 */
@Entity(tableName = NYC_SCHOOLS_2018_TABLE_NAME)
data class NYCSchools2018Entity(
    val boro: String?,
    val borough: String?,
    val city: String?,
    @PrimaryKey
    val dbn: String,
    val latitude: String?,
    val location: String?,
    val longitude: String?,
    val neighborhood: String?,
    val phoneNumber: String?,
    val schoolName: String,
    val startTime: String?,
    val stateCode: String?,
    val totalStudents: String?,
    val website: String?,
    val zip: String?
)

