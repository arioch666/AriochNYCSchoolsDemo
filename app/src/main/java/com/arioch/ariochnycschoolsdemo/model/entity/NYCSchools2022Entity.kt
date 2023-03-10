package com.arioch.ariochnycschoolsdemo.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

const val NYC_SCHOOLS_2022_TABLE_NAME = "NYCSchools2022"

@Entity(tableName = NYC_SCHOOLS_2022_TABLE_NAME)
data class NYCSchools2022Entity(
    @PrimaryKey
    val dbn: String,
    val numberOfSATTestTakers: String,
    val satCriticalReadingAvgScore: String,
    val satMathAvgScore: String,
    val satWritingAvgScore: String
)
