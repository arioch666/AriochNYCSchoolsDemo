package com.arioch.ariochnycschoolsdemo.network

import com.arioch.ariochnycschoolsdemo.network.data.NetworkResult
import com.arioch.ariochnycschoolsdemo.network.data.NetworkState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

/**
 * Handles all the network requests for the application. Provides a central place for error handling
 * as well as providing accurate network state.
 */
class NetworkHandler private constructor() {
    private val mutableNetworkStateFlow = MutableStateFlow<NetworkState>(NetworkState.Idle)
    private val mutableNetworkResultFlow = MutableStateFlow<NetworkResult>(NetworkResult.NetworkSilent)
    val networkStateFlow: Flow<NetworkState> = mutableNetworkStateFlow
    val networkResultFlow: Flow<NetworkResult> = mutableNetworkResultFlow

    /**
     * Makes the network requests passed in.
     *
     * Central place for handling error cases
     */
    private suspend inline fun <T> makeNetworkRequest(isNetworkConnected: Boolean, request: ()-> Response<T>) {
        if (isNetworkConnected.not()) {
            mutableNetworkStateFlow.emit(NetworkState.NotConnected)
        } else {
            try {
                val response = request()
                mutableNetworkStateFlow.emit(NetworkState.Loading)

                if (response.isSuccessful) {
                    mutableNetworkResultFlow.emit(NetworkResult.NetworkSuccess(response.body()))
                    mutableNetworkStateFlow.emit(NetworkState.Idle)
                } else {
                    mutableNetworkResultFlow.emit(
                        NetworkResult.NetworkFailure(
                            HttpException(
                                response
                            )
                        )
                    )
                    mutableNetworkStateFlow.emit(NetworkState.Idle)
                }
            } catch (e: IOException) {
                // Handling IOException and HttpException separately in case we want to log something
                // here.
                mutableNetworkResultFlow.emit(NetworkResult.NetworkFailure(e))
                mutableNetworkStateFlow.emit(NetworkState.Idle)
            } catch (e: HttpException) {
                mutableNetworkResultFlow.emit(NetworkResult.NetworkFailure(e))
                mutableNetworkStateFlow.emit(NetworkState.Idle)
            }
        }
    }

    /**
     * Makes the network request for NYC Schools 2018 data.
     */
    suspend fun getNYCSchools2018Info(isNetworkConnected: Boolean) {
        makeNetworkRequest(isNetworkConnected) {
            NYCSchoolsAccess.nycSchoolsApiService.getNYCSchools2018Info()
        }
    }

    /**
     * Makes the network reuqest for NYC Schools 2022 SAT Data.
     */
    suspend fun getNYCSchools2022Info(isNetworkConnected: Boolean) {
        makeNetworkRequest(isNetworkConnected = isNetworkConnected) {
            NYCSchoolsAccess.nycSchoolsApiService.getNYCSchools2022SATDetails()
        }
    }

    companion object {
        // Making this a singleton since all network requests can go thought a single object.
        // Ideally use dependency injection with qualifiers for fakes. Helps with testing.
        private var INSTANCE: NetworkHandler? = null
        fun getNetworkHandler(): NetworkHandler {
            return INSTANCE ?: synchronized(this) {
                val instance = NetworkHandler()
                INSTANCE = instance
                instance
            }
        }
    }
}