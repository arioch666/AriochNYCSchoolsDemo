package com.arioch.ariochnycschoolsdemo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.arioch.ariochnycschoolsdemo.databinding.FragmentFirstBinding
import com.arioch.ariochnycschoolsdemo.network.NetworkConnectivityHelper
import com.arioch.ariochnycschoolsdemo.network.data.NetworkState
import com.arioch.ariochnycschoolsdemo.ui.data.NYCSchoolsListUiObj
import com.arioch.ariochnycschoolsdemo.ui.schools.NYCSchoolsRecyclerViewAdapter
import com.arioch.ariochnycschoolsdemo.viewmodel.NYCSchoolsViewModel
import com.arioch.ariochnycschoolsdemo.viewmodel.ViewModelFactory


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private var viewModel: NYCSchoolsViewModel? = null
    private var adapter: NYCSchoolsRecyclerViewAdapter? = null
    private var networkConnectivityHelper: NetworkConnectivityHelper? = null
    private var schoolsList: List<NYCSchoolsListUiObj> = mutableListOf(NYCSchoolsListUiObj(dbn = "awefghaywfeg", schoolName = "awuiefkuiyawefkaywgefa"))

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)

        initializeNetworkConnectivityHelper()

        val application = inflater
            .context
            .applicationContext as AriochNYCSchoolsDemoApplication

        viewModel = ViewModelFactory(application.nycSchoolsRepository)
            .create(NYCSchoolsViewModel::class.java)

        addNetworkStateObserver()

        if (adapter == null) {
            adapter = NYCSchoolsRecyclerViewAdapter(nycSchoolsList = schoolsList)
        }
        binding.recyclerViewNycSchools.adapter = adapter

        addSchoolsListObserver()

        viewModel?.fetchNYCSchoolsData(forceNetworkRefresh = false, isNetworkConnected = networkConnectivityHelper?.isConnected() ?: false)


        return binding.root

    }

    private fun initializeNetworkConnectivityHelper() {
        if (networkConnectivityHelper == null) {
            networkConnectivityHelper = context?.let { nonNullContext ->
                NetworkConnectivityHelper(nonNullContext)
            }
        }
    }

    private fun addNetworkStateObserver() {
        /**
         * TODO fix the network state. It has not been completely setup.
         */
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel?.networkStatus?.observe(this@FirstFragment.viewLifecycleOwner) { networkState ->
                when (networkState) {
                    is NetworkState.NotConnected -> {
                        val activity: MainActivity? = activity as? MainActivity
                        activity?.showNetworkDisconnectedSnackBar()
                    }

                    NetworkState.Idle -> binding.linearProgressIndicator.visibility = View.INVISIBLE
                    NetworkState.Loading -> binding.linearProgressIndicator.visibility =
                        View.VISIBLE

                    else -> {}
                }
            }
        }
    }

    private fun addRetryClickListener() {
        binding.buttonFetch.setOnClickListener {
            viewModel?.
            fetchNYCSchoolsData(forceNetworkRefresh = true,
                networkConnectivityHelper?.isConnected()?: false)
        }
    }

    /**
     * Observer the countries list in the view model.
     */
    private fun addSchoolsListObserver() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel
                ?.nycSchoolsListUiObjFlow
                ?.observe(this@FirstFragment.viewLifecycleOwner) { nycSchoolsUiObjList ->
                if (nycSchoolsUiObjList.isEmpty()) {
                    binding.recyclerViewNycSchools.visibility = View.INVISIBLE
                    binding.textViewErrorOrEmpty.text = getString(R.string.schools_list_empty)
                    binding.frameLayoutErrorOrEmpty.visibility = View.VISIBLE
                } else {
                    schoolsList = nycSchoolsUiObjList
                    (adapter as NYCSchoolsRecyclerViewAdapter).submitList(schoolsList)
                    binding.recyclerViewNycSchools.visibility = View.VISIBLE
                    binding.frameLayoutErrorOrEmpty.visibility = View.GONE
                }
            }
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addRetryClickListener()

//        binding.buttonFirst.setOnClickListener {
//            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
//        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}