package com.arioch.ariochnycschoolsdemo.network.data

/**
 * Result of the network request.
 *
 * @see NetworkFailure
 * @see NetworkSilent
 * @see NetworkSuccess
 *
 * @author Arioch
 */
sealed interface NetworkResult {
    /**
     * The failure case will propagate the Exception for handling on the UI.
     *
     * @param e The exception that was thrown.
     */
    data class NetworkFailure(val e: Exception): NetworkResult

    /**
     * No Result, Default for the state flow initialization.
     */
    object NetworkSilent: NetworkResult

    /**
     * The Success case will return the network response.
     *
     * @param networkResultBody The network response.
     */
    data class  NetworkSuccess<T>(val networkResultBody: T): NetworkResult
}