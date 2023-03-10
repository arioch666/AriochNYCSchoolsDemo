package com.arioch.ariochnycschoolsdemo.model.repo

import com.arioch.ariochnycschoolsdemo.model.dao.NYCSchools2022DAO
import com.arioch.ariochnycschoolsdemo.model.entity.NYCSchools2022Entity
import com.arioch.ariochnycschoolsdemo.network.data.NetworkResult
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

/**
 * Provides data access to the NYCSchools2022 data.
 *
 * Includes methods that will make network calls and DB interactions.
 * 
 * Handles determination of network vs local data fetch.
 *
 * @param nycSchools2022DAO The DAO for the NYCSchools2022 table.
 * @see NYCSchools2022DAO
 *
 * @author Arioch
 */
class NYCSchools2022Repo(private val nycSchools2022DAO: NYCSchools2022DAO) {
    private val networkRepo = NetworkRepo.getInstance()
    private val nycSchools2022EntityDataFlow = nycSchools2022DAO.getAllNYCSchools()
    private val nycSchools2022NetworkDataFlow = networkRepo.networkResultFlow.filterIsInstance<NetworkResult.NetworkSuccess<*>>()
    
    
    /**
     * Inserts the [NYCSchools2022Entity] object passed in into the DB table for NYCSchools2022.
     *
     * Since room runs all queries on a different thread there is no need to make this a
     * suspend function.
     *
     * @param nycSchools2022Entities The NYCSchools2022Entity objects to insert into the DB.
     * @see NYCSchools2022Entity
     */
    suspend fun insertIntoDBNYCSchools2022Data(vararg nycSchools2022Entities: NYCSchools2022Entity) {
        mutex.withLock {
            nycSchools2022DAO.insertAll(*nycSchools2022Entities)
        }
    }

    /**
     * Triggers a network request for the NYCSchools2022Data from the network if the
     * @param forceNetworkFetch is true.
     * @param isNetworkConnected If false, will prevent a network request.
     * @see NetworkRepo.getNYCSchools2022
     */
    suspend fun fetchNYCSchools2022Data(forceNetworkFetch: Boolean, isNetworkConnected: Boolean) {
        if (forceNetworkFetch) {
            requestNYC2022DataFromNetwork(isNetworkConnected)
        }
        nycSchools2022DAO.getAllNYCSchools()
    }

    /**
     * Passes on the request for NYCSchools2022Data to the [networkRepo].
     *
     * @param isNetworkConnected If false, will prevent a network request.
     * @see NetworkRepo.getNYCSchools2022
     */
    private suspend fun requestNYC2022DataFromNetwork(isNetworkConnected: Boolean) {
        networkRepo.getNYCSchools2022(isNetworkConnected)
    }

    companion object {
        /**
         * mutex used for insertion to make sure there is only 1 insertion being queued at a time.
         */
        private val mutex = Mutex()
    }
}