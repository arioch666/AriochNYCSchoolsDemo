package com.arioch.ariochnycschoolsdemo.ui.data

/**
 * Used to display the data in the UI.
 *
 * @param dbn The unique identifier for the school.
 * @param city The city name for the school.
 * @param borough The borough name for the school.
 * @param neighborhood The neighborhood of the school.
 * @param schoolName The name of the school.
 * @param totalStudents The total number of students at the school.
 * @param zip The zip code of the school.
 * @param numberOfSATTestTakers The number of students that took the SAT.
 * @param satCriticalReadingAvgScore The average score for the critical reading section of the SAT.
 * @param satMathAvgScore The average score for the math section of the SAT.
 * @param satWritingAvgScore The average score for the writing section of the SAT.
 *
 * @author Arioch
 */
data class NYCSchoolsListUiObj(
    val dbn: String,
    val city: String? = null,
    val borough: String? = null,
    val neighborhood: String? = null,
    val schoolName: String,
    val totalStudents: String? = null,
    val zip: String? = null,
    val numberOfSATTestTakers: String? = null,
    val satCriticalReadingAvgScore: String? = null,
    val satMathAvgScore: String? = null,
    val satWritingAvgScore: String? = null
)

