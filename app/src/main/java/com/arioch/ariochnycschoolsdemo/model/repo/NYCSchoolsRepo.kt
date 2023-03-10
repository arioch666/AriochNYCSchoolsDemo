package com.arioch.ariochnycschoolsdemo.model.repo

import com.arioch.ariochnycschoolsdemo.model.dao.NYCSchoolsViewDAO
import com.arioch.ariochnycschoolsdemo.model.view.toNYCSchoolsListUiObjList
import com.arioch.ariochnycschoolsdemo.network.data.NYCSchools2018NetworkObj
import com.arioch.ariochnycschoolsdemo.network.data.NYCSchools2022NetworkObj
import com.arioch.ariochnycschoolsdemo.network.data.NetworkResult
import com.arioch.ariochnycschoolsdemo.network.data.toNYCSchools2018EntityList
import com.arioch.ariochnycschoolsdemo.network.data.toNYCSchools2022EntityList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus
import kotlinx.coroutines.withContext

/**
 * Repository for the NYC Schools data.
 *
 * provides access to the data from the DAOs and the Network.
 *
 * @param nycSchoolsViewDAO DAO for the NYC Schools View.
 * @see NYCSchoolsViewDAO
 *
 * @param nycSchools2018Repo Repository for the 2018 NYC Schools data.
 * @see NYCSchools2018Repo
 *
 * @param nycSchools2022Repo Repository for the 2022 NYC Schools data.
 * @see NYCSchools2022Repo
 *
 * @param networkRepo Repository for the network.
 * @see NetworkRepo
 *
 * @param coroutineScope CoroutineScope for the repository.
 * @see CoroutineScope
 *
 * @constructor Creates a new instance of the repository.
 *
 * @property networkStateFlow Flow of the NetworkState.
 * @property allNYCSchoolsListUiObjFlow Flow<[List]<NYCSchoolsListUiObj>> - recycler view item.
 *
 *
 * @author Arioch
 */
class NYCSchoolsRepo(private val nycSchoolsViewDAO: NYCSchoolsViewDAO,
                     private val nycSchools2018Repo: NYCSchools2018Repo,
                     private val nycSchools2022Repo: NYCSchools2022Repo,
                     private val networkRepo: NetworkRepo,
                     private val coroutineScope: CoroutineScope) {

    /**
     * Exposes the network state flow to the ViewModel.
     */
    val networkStateFlow = networkRepo.networkStateFlow

    /**
     * Flow of UI List objects for use in a recycler view.
     */
    val allNYCSchoolsListUiObjFlow = nycSchoolsViewDAO
                                        .getAllNYCSchools()
                                        .map { nycSchoolsViewList ->
                                            nycSchoolsViewList.toNYCSchoolsListUiObjList()
                                        }
                                        .onStart { emit(emptyList()) }


    init {
        /**
         * Collects the network result flow and inserts the data into the database.
         *
         * Filters the network result flow to only collect the NetworkSuccess objects.
         */
        (coroutineScope + SupervisorJob()).launch {
            networkRepo
                .networkResultFlow
                .filterIsInstance<NetworkResult.NetworkSuccess<ArrayList<*>>>()
                .collectLatest { networkResult ->
                    val networkResultBody = networkResult.networkResultBody
                    if (networkResultBody.isNotEmpty()) {
                        parseNetworkResultAndInsert(networkResultBody)
                    }
                }
         }
    }

    /**
     * Parses the network result and inserts the data into the database.
     *
     * Calls the appropriate insert function based on the type of data in the network result.
     */
    private suspend fun parseNetworkResultAndInsert(networkResultBody: ArrayList<*>) {
        when (networkResultBody[0]) {
            is NYCSchools2018NetworkObj -> {
                    @Suppress("UNCHECKED_CAST")
                    insertNYCSchools2018Data(networkResultBody as List<NYCSchools2018NetworkObj>)
            }

            is NYCSchools2022NetworkObj -> {
                    @Suppress("UNCHECKED_CAST")
                    insertNYCSchools2022Data(networkResultBody as List<NYCSchools2022NetworkObj>)
            }
        }
    }

    /**
     * Converts the list of NYCSchools2022NetworkObj to a list of NYCSchools2022Entity and inserts
     * the list into the database.
     */
    private suspend fun insertNYCSchools2022Data(nycSchools2022NetworkObjList:
                                                    List<NYCSchools2022NetworkObj>) {
        withContext(Dispatchers.IO) {
            nycSchools2022Repo
                .insertIntoDBNYCSchools2022Data(
                    *(nycSchools2022NetworkObjList
                        .toNYCSchools2022EntityList())
                        .toTypedArray()
                )
        }
    }

    /**
     * Converts the list of NYCSchools2018NetworkObj to a list of NYCSchools2018Entity and inserts
     * the list into the database.
     */
    private suspend fun insertNYCSchools2018Data(nycSchools2018NetworkObjList:
                                                    List<NYCSchools2018NetworkObj>) {
        withContext(Dispatchers.IO) {
            nycSchools2018Repo
                .insertIntoDBNYCSchools2018Data(
                    *(nycSchools2018NetworkObjList
                        .toNYCSchools2018EntityList())
                        .toTypedArray()
                )
        }
    }

    /**
     * Fetches the NYC Schools data from the network.
     */
    suspend fun fetchNYCSchoolsData(forceNetworkRefresh: Boolean, networkConnected: Boolean) {
        nycSchools2018Repo.fetchNYCSchools2018Data(forceNetworkRefresh, networkConnected)
        nycSchools2022Repo.fetchNYCSchools2022Data(forceNetworkRefresh, networkConnected)
    }
}