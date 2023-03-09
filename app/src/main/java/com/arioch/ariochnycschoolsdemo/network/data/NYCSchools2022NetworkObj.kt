package com.arioch.ariochnycschoolsdemo.network.data

import com.google.gson.annotations.SerializedName

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