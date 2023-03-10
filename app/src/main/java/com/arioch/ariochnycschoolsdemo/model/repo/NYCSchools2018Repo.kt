package com.arioch.ariochnycschoolsdemo.model.repo

import com.arioch.ariochnycschoolsdemo.model.dao.NYCSchools2018DAO
import com.arioch.ariochnycschoolsdemo.model.entity.NYCSchools2018Entity
import com.arioch.ariochnycschoolsdemo.network.data.NetworkResult
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

/**
 * Provides data access to the NYCSchools2018 data.
 *
 * Includes methods that will make network calls and DB interactions.
 *
 * Handles determination of network vs local data fetch.
 *
 * @param nycSchools2018DAO The DAO for the NYCSchools2018 table.
 * @see NYCSchools2018DAO
 *
 * @author Arioch
 */
class NYCSchools2018Repo(private val nycSchools2018DAO: NYCSchools2018DAO) {

    private val networkRepo = NetworkRepo.getInstance()
    private val nycSchools2018NetworkDataFlow = networkRepo
                                                .networkResultFlow
                                                .filterIsInstance<NetworkResult.NetworkSuccess<*>>()

    val nycSchools2018EntityDataFlow = nycSchools2018DAO.getAllNYCSchools()



    /**
     * Inserts the [NYCSchools2018Entity] object passed in into the DB table for NYCSchools2018.
     *
     * Since room runs all queries on a different thread there is no need to make this a
     * suspend function.
     *
     * @param nycSchools2018Entities The NYCSchools2018Entity objects to insert into the DB.
     */
    suspend fun insertIntoDBNYCSchools2018Data(vararg nycSchools2018Entities: NYCSchools2018Entity) {
        mutex.withLock {
            nycSchools2018DAO.insertAll(*nycSchools2018Entities)
        }
    }

    /**
     * Triggers a network request for the NYCSchools2018Data
     * @param forceNetworkFetch If true, will trigger a network request for the NYCSchools2018Data.
     * @param isNetworkConnected If false, will prevent a network request.
     */
    suspend fun fetchNYCSchools2018Data(forceNetworkFetch: Boolean, isNetworkConnected: Boolean) {
        if (forceNetworkFetch) {
            requestNYC2018DataFromNetwork(isNetworkConnected)
        }
        nycSchools2018DAO.getAllNYCSchools()
    }

    /**
     * Passes on the request for NYCSchools2018Data to the [networkRepo].
     *
     * @param isNetworkConnected If false, will prevent a network request.
     */
    private suspend fun requestNYC2018DataFromNetwork(isNetworkConnected: Boolean) {
        networkRepo.getNYCSchools2018(isNetworkConnected)
    }

    companion object {
        /**
         * mutex used for insertion to make sure there is only 1 insertion being queued at a time.
         */
        private val mutex = Mutex()
    }
}