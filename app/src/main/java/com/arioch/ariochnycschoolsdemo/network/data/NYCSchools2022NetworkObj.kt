package com.arioch.ariochnycschoolsdemo.network.data

import com.arioch.ariochnycschoolsdemo.model.entity.NYCSchools2022Entity
import com.google.gson.annotations.SerializedName


/**
 * Represents the NYCSchools2022NetworkObj data received from the endpoint.
 *
 * This object will only be used in the network layer and the other layers will use different
 * objects.
 *
 * Creating separation of layers with translating objects between layers allows easy replacement of
 * existing components if there is a need in the future.
 *
 * @param dbn The unique identifier for the school.
 * @param numberOfSATTestTakers The number of students who took the SAT.
 * @param satCriticalReadingAvgScore The average score for the critical reading section of the SAT.
 * @param satMathAvgScore The average score for the math section of the SAT.
 * @param satWritingAvgScore The average score for the writing section of the SAT.
 *
 * @author Arioch
 */
data class NYCSchools2022NetworkObj(
    val dbn: String,
    @SerializedName(value = "num_of_sat_test_takers")
    val numberOfSATTestTakers: String,
    @SerializedName(value = "sat_critical_reading_avg_score")
    val satCriticalReadingAvgScore: String,
    @SerializedName(value = "sat_math_avg_score")
    val satMathAvgScore: String,
    @SerializedName(value = "sat_writing_avg_score")
    val satWritingAvgScore: String
)

/**
 * Converts the NYCSchools2022NetworkObj into the NYCSchools2022Entity for storage.
 */
fun NYCSchools2022NetworkObj.toNYCSchools2022Entity() = NYCSchools2022Entity(
    dbn,
    numberOfSATTestTakers,
    satCriticalReadingAvgScore,
    satMathAvgScore,
    satWritingAvgScore
)

fun List<NYCSchools2022NetworkObj>.toNYCSchools2022EntityList() = map {nycSchools2022NetworkObj ->
    nycSchools2022NetworkObj.toNYCSchools2022Entity()
}