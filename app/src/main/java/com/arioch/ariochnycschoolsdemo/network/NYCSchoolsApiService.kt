package com.arioch.ariochnycschoolsdemo.network

import com.arioch.ariochnycschoolsdemo.network.data.NYCSchools2018NetworkObj
import com.arioch.ariochnycschoolsdemo.network.data.NYCSchools2022NetworkObj
import retrofit2.Response
import retrofit2.http.GET

/**
 * Retrofit service that will provide access to the NYC Schools data.
 *
 * @see RetrofitProvider
 *
 * @author Arioch
 */
interface NYCSchoolsApiService {
    /**
     * Given more time, this would be a paging query based since the endpoint supports it.
     * However, since that would require setting up an API key and then adding security around it,
     * storing the entire data set locally in SQL is easier.
     */
    @GET(API.NYCSchools)
    suspend fun getNYCSchools2018Info(): Response<List<NYCSchools2018NetworkObj>>

    /**
     * Same as the comment above.
     * https://dev.socrata.com/docs/queries/
     * https://dev.socrata.com/docs/paging.html
     * There is also an SDK available with a sample for list and map fragments.
     * If this was a production level application, we should store the API key using the NDK.
     * https://bapspatil.medium.com/store-your-api-keys-more-securely-using-cmake-kotlin-f46cf1b1033
     * provides a good instruction set.
     *       *****************************************************************************
     * NOTE:
     * There is a disparity in the data sets between the 2 endpoints.
     * The SAT Data set was updated in 2022 and does not have all the schools from the 2018 data
     * set.
     *
     * The plan is to insert both data sets into the DB and then filter the list of items shown
     * based on an intersections.
     */
    @GET(API.NYCSchoolsSATDetails)
    suspend fun getNYCSchools2022SATDetails(): Response<List<NYCSchools2022NetworkObj>>
}

/**
 * Singleton object for the service. Ideally this would be part of a Dependency injection graph
 * where a fake is injected for testing.
 */
object NYCSchoolsAccess{
    internal val nycSchoolsApiService: NYCSchoolsApiService by lazy {
        RetrofitProvider.retrofit.create(NYCSchoolsApiService::class.java)
    }
}