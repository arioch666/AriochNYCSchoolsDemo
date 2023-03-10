package com.arioch.ariochnycschoolsdemo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.arioch.ariochnycschoolsdemo.model.repo.NYCSchoolsRepo
import kotlinx.coroutines.launch

/**
 * ViewModel for the List of NYC Schools
 *
 * @param nycSchoolsRepo The repository for the NYC Schools
 * @see NYCSchoolsRepo
 *
 * @constructor Creates a new instance of the ViewModel
 *
 * @property nycSchoolsListUiObjFlow [Flow]<[List]<NYCSchoolsListUiObj>> - recycler view item.
 * @property networkStatus [Flow]<[NetworkState]> - network status.
 *
 * @author Arioch
 */
class NYCSchoolsViewModel(private val nycSchoolsRepo: NYCSchoolsRepo): ViewModel() {
    val nycSchoolsListUiObjFlow = nycSchoolsRepo
                                    .allNYCSchoolsListUiObjFlow
                                    .asLiveData(viewModelScope.coroutineContext)

    val networkStatus = nycSchoolsRepo
                        .networkStateFlow
                        .asLiveData(viewModelScope.coroutineContext)


    /**
     * Fetches the NYC Schools data from the network.
     *
     * @param forceNetworkRefresh Boolean - force a network refresh.
     * @param isNetworkConnected Boolean - is the network connected.
     *
     * @see NYCSchoolsRepo.fetchNYCSchoolsData
     */
    fun fetchNYCSchoolsData(forceNetworkRefresh: Boolean = false, isNetworkConnected: Boolean) {
        viewModelScope.launch {
            nycSchoolsRepo.fetchNYCSchoolsData(forceNetworkRefresh, isNetworkConnected)
        }
    }

}