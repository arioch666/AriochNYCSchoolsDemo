package com.arioch.ariochnycschoolsdemo.model.view

import androidx.room.DatabaseView
import com.arioch.ariochnycschoolsdemo.ui.data.NYCSchoolsListUiObj

/**
 * DB View that will provide the latest combined data for use in the list.
 *
 * Performs a LEFT OUTER JOIN on the two tables.
 *
 * The reason for the left outer join is the data sets are mismatched as one of them is from 2018
 * and the other is from 2022. The 2018 data set has more schools than the 2022 data set.
 *
 * Testing the data shows that there are schools in the 2018 data set that do not have dbn values
 * matching the 2022 data set and vice versa.
 *
 * Since the 2018 data set is the primary list data set using this join will enable the click for
 * all elements with an empty state for the SAT data for some values.
 *
 * @param dbn The unique identifier for the school.
 * @param city The city name for the school.
 * @param borough The borough name for the school.
 * @param neighborhood The neighborhood of the school.
 * @param schoolName The name of the school.
 * @param totalStudents The total number of students at the school.
 * @param numberOfSATTestTakers The number of students that took the SAT.
 * @param satCriticalReadingAvgScore The average score for the critical reading section of the SAT.
 * @param satMathAvgScore The average score for the math section of the SAT.
 * @param satWritingAvgScore The average score for the writing section of the SAT.
 *
 * @author Arioch
 */
@DatabaseView(
    "SELECT " +
            "NYCSchools2018.dbn, " +
            "NYCSchools2018.city , " +
            "NYCSchools2018.borough, " +
            "NYCSchools2018.neighborhood, " +
            "NYCSchools2018.schoolName, " +
            "NYCSchools2018.totalStudents, " +
            "NYCSchools2018.zip, " +
            "NYCSchools2022.numberOfSATTestTakers, " +
            "NYCSchools2022.satCriticalReadingAvgScore, " +
            "NYCSchools2022.satMathAvgScore, " +
            "NYCSchools2022.satWritingAvgScore " +
            "FROM NYCSchools2018 " +
            "LEFT OUTER JOIN NYCSchools2022 " +
            "ON nycschools2018.dbn = nycschools2022.dbn"
)
data class NYCSchoolsView(
    val dbn: String,
    val city: String?,
    val borough: String?,
    val neighborhood: String?,
    val schoolName: String,
    val totalStudents: String?,
    val zip: String?,
    val numberOfSATTestTakers: String? = null,
    val satCriticalReadingAvgScore: String? = null,
    val satMathAvgScore: String? = null,
    val satWritingAvgScore: String? = null
    )

fun NYCSchoolsView.toNYCSchoolsListUiObj() =
    NYCSchoolsListUiObj(
        dbn = dbn,
        city = city,
        borough = borough,
        neighborhood = neighborhood,
        schoolName = schoolName,
        totalStudents = totalStudents,
        zip = zip,
        numberOfSATTestTakers = numberOfSATTestTakers,
        satCriticalReadingAvgScore = satCriticalReadingAvgScore,
        satMathAvgScore = satMathAvgScore,
        satWritingAvgScore = satWritingAvgScore
    )

fun List<NYCSchoolsView>.toNYCSchoolsListUiObjList() = map {nycSchoolsView -> nycSchoolsView.toNYCSchoolsListUiObj() }