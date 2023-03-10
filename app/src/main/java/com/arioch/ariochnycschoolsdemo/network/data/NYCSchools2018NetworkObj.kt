package com.arioch.ariochnycschoolsdemo.network.data

import com.arioch.ariochnycschoolsdemo.model.entity.NYCSchools2018Entity
import com.google.gson.annotations.SerializedName

/**
 * Represents the NYCSchools2018 data received from the endpoint.
 *
 * This object will only be used in the network layer and the other layers will use different
 * objects.
 *
 * Creating separation of layers with translating objects between layers allows easy replacement of
 * existing components if there is a need in the future.
 */
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

/**
 * Converts the NYCSchools2018NetworkObj into the NYCSchools2019Entity for storage.
 */
fun NYCSchools2018NetworkObj.toNYCSchools2018Entity() = NYCSchools2018Entity(
                                                            boro = boro,
                                                            borough = borough,
                                                            city = city,
                                                            dbn = dbn,
                                                            latitude = latitude,
                                                            location = location,
                                                            longitude = longitude,
                                                            neighborhood = neighborhood,
                                                            phoneNumber = phoneNumber,
                                                            schoolName = schoolName,
                                                            startTime = startTime,
                                                            stateCode = stateCode,
                                                            totalStudents = totalStudents,
                                                            website = website,
                                                            zip = zip)