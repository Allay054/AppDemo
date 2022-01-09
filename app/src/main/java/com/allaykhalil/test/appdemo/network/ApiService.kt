package com.allaykhalil.test.appdemo.network

import com.allaykhalil.test.appdemo.utils.Constants
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.Retrofit


object ApiService {
    // post User builder
    fun postUserDataCall() = Retrofit.Builder()
        .baseUrl(Constants.API_BASE_PATH)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(ApiWorker.gsonConverter)
        .client(ApiWorker.client)
        .build()
        .create(APIServices::class.java)


}