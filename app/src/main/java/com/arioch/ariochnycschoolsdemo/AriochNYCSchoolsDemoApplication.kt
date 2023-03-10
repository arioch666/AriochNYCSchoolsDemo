package com.arioch.ariochnycschoolsdemo

import android.app.Application
import com.arioch.ariochnycschoolsdemo.model.db.AriochNYCSchoolsDemoDB
import com.arioch.ariochnycschoolsdemo.model.repo.NYCSchools2018Repo
import com.arioch.ariochnycschoolsdemo.model.repo.NYCSchools2022Repo
import com.arioch.ariochnycschoolsdemo.model.repo.NYCSchoolsRepo
import com.arioch.ariochnycschoolsdemo.model.repo.NetworkRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel

class AriochNYCSchoolsDemoApplication: Application() {
    private val schoolsDb by lazy { AriochNYCSchoolsDemoDB.getDatabase(this) }

    private val nycSchools2018Repo by lazy { NYCSchools2018Repo(schoolsDb.getNYCSchools2018DAO()) }
    private val nycSchools2022Repo by lazy { NYCSchools2022Repo(schoolsDb.getNYCSchools2022DAO()) }

    val applicationCoroutineScope = CoroutineScope(Dispatchers.IO)

    val nycSchoolsRepository by lazy {
        NYCSchoolsRepo(schoolsDb.getNYCSchoolsViewDAO(),
            nycSchools2018Repo,
            nycSchools2022Repo,
            NetworkRepo.getInstance(),
            applicationCoroutineScope)
    }
    override fun onCreate() {
        super.onCreate()
    }

    override fun onTerminate() {
        applicationCoroutineScope.cancel("Application Terminated")
        super.onTerminate()
    }
}