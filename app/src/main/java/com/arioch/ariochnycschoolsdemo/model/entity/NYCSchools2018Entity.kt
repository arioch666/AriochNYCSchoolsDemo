package com.arioch.ariochnycschoolsdemo.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

const val NYC_SCHOOLS_2018_TABLE_NAME = "NYCSchools2018"

/**
 * Represents the NYCSchools2018 table in the DB.
 *
 * This object will eventually translate into a UI object.
 */
@Entity(tableName = NYC_SCHOOLS_2018_TABLE_NAME)
data class NYCSchools2018Entity(
    val boro: String,
    val borough: String,
    val city: String,
    @PrimaryKey
    val dbn: String,
    val latitude: String,
    val location: String,
    val longitude: String,
    val neighborhood: String,
    val phoneNumber: String,
    val schoolName: String,
    val startTime: String,
    val stateCode: String,
    val totalStudents: String,
    val website: String,
    val zip: String
)
