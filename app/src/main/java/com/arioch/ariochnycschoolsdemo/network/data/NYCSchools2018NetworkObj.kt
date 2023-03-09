package com.arioch.ariochnycschoolsdemo.network.data

import com.google.gson.annotations.SerializedName

data class NYCSchools2018NetworkObj(
    val boro: String,
    val borough: String,
    val city: String,
    val dbn: String,
    val latitude: String,
    val location: String,
    val longitude: String,
    val neighborhood: String,
    @SerializedName(value = "phone_number")
    val phoneNumber: String,
    @SerializedName(value = "school_name")
    val schoolName: String,
    @SerializedName(value = "start_time")
    val startTime: String,
    @SerializedName(value = "state_code")
    val stateCode: String,
    @SerializedName(value = "total_students")
    val totalStudents: String,
    val website: String,
    val zip: String
)