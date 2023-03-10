package com.arioch.ariochnycschoolsdemo.network.data

/**
 * Used to communicate the state of the network.
 *
 * @see Idle
 * @see Loading
 * @see NotConnected
 *
 * @author Arioch
 */
sealed interface NetworkState {
    /**
     * Identifies the network state when the app initially launches.
     */
    object Idle: NetworkState

    /**
     * Used when the network is fetching data.
     */
    object Loading: NetworkState

    /**
     * If there is no internet connection.
     */
    object NotConnected: NetworkState
}