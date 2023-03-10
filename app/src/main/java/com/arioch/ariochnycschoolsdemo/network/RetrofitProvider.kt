package com.arioch.ariochnycschoolsdemo.network

import com.arioch.ariochnycschoolsdemo.network.API.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Provides the Retrofit instance.
 *
 * @see Retrofit
 * @see GsonConverterFactory
 *
 * @author Arioch
 */
object RetrofitProvider {
    internal var retrofit = Retrofit
        .Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}