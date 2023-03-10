package com.arioch.ariochnycschoolsdemo.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

const val NYC_SCHOOLS_2022_TABLE_NAME = "NYCSchools2022"

/**
 * Represents the NYCSchools2022 table in the DB.
 *
 * @param dbn The unique identifier for the school.
 * @param numberOfSATTestTakers The number of students that took the SAT.
 * @param satCriticalReadingAvgScore The average score for the critical reading section of the SAT.
 * @param satMathAvgScore The average score for the math section of the SAT.
 * @param satWritingAvgScore The average score for the writing section of the SAT.
 *
 * @author Arioch
 */
@Entity(tableName = NYC_SCHOOLS_2022_TABLE_NAME)
data class NYCSchools2022Entity(
    @PrimaryKey
    val dbn: String,
    val numberOfSATTestTakers: String,
    val satCriticalReadingAvgScore: String,
    val satMathAvgScore: String,
    val satWritingAvgScore: String
)
