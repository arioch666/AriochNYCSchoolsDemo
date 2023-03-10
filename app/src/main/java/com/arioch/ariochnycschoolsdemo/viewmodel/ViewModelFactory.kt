package com.arioch.ariochnycschoolsdemo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.arioch.ariochnycschoolsdemo.model.repo.NYCSchoolsRepo

/**
 * Factory for the View Models.
 * This is used to create the View Models.
 * Stores the viewmodel instances in a hashmap.
 */
class ViewModelFactory(private val nycSchoolsRepo: NYCSchoolsRepo):
    ViewModelProvider.Factory {
    /**
     * Updated this method with new view models if there are more added in the future.
     *
     * @param modelClass Class<T> - the class of the view model.
     * @return T - the view model.
     * @throws IllegalArgumentException - if the view model is not supported.
     *
     */
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(NYCSchoolsViewModel::class.java)) {
            val canonicalName = NYCSchoolsViewModel::class.java.canonicalName ?:
            NYCSchoolsViewModel::class.java.simpleName

            if(!hashMap.containsKey(canonicalName)) {
                hashMap[canonicalName] = NYCSchoolsViewModel(nycSchoolsRepo)
            }
            @Suppress("UNCHECKED_CAST")
            return hashMap[canonicalName] as T
        }
        throw IllegalArgumentException("Unsupported View Model requested.")
    }

    companion object {
        private val hashMap: MutableMap<String, ViewModel> = mutableMapOf()
    }
}