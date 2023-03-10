package com.arioch.ariochnycschoolsdemo.model.repo

import com.arioch.ariochnycschoolsdemo.network.NetworkHandler
import com.arioch.ariochnycschoolsdemo.network.data.NetworkResult
import com.arioch.ariochnycschoolsdemo.network.data.NetworkState
import kotlinx.coroutines.flow.Flow

/**
 * Provides access to the network.
 *
 * There are flows for the network state and the networkResult.
 *
 * @author Arioch
 */
class NetworkRepo private constructor() {
    private val networkHandler = NetworkHandler.getNetworkHandler()
    val networkStateFlow: Flow<NetworkState> = networkHandler.networkStateFlow
    val networkResultFlow: Flow<NetworkResult> = networkHandler.networkResultFlow

    /**
     * Passes on the request to the [networkHandler] requesting the NYC 2018 data.
     *
     * @param isNetworkConnected Boolean - true if the network is connected.
     */
    suspend fun getNYCSchools2018(isNetworkConnected: Boolean) {
        networkHandler.getNYCSchools2018Info(isNetworkConnected)
    }

    /**
     * Requests the NYC 2022 SAT data through the [networkHandler]
     *
     * @param isNetworkConnected Boolean - true if the network is connected.
     */
    suspend fun getNYCSchools2022(isNetworkConnected: Boolean) {
        networkHandler.getNYCSchools2022Info(isNetworkConnected)
    }

    companion object {
        private var INSTANCE: NetworkRepo? = null

        /**
         * Making this a singleton to make sure there are no additional instances created.
         *
         * Ideally use dependency injection to handle singletons.
         */
        fun getInstance(): NetworkRepo {
            return INSTANCE ?: synchronized(this) {
                val instance = NetworkRepo()
                INSTANCE = instance
                instance
            }
        }
    }
}