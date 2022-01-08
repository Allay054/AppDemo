package com.holatourism.fiore.convextechtestdemo.viewModels

import android.annotation.SuppressLint
import android.util.Log
import com.holatourism.fiore.convextechtestdemo.allResponses.NYTimesMostPopularArticlesResponse
import com.holatourism.fiore.convextechtestdemo.dataModels.GetAllArticlesModel
import com.holatourism.fiore.convextechtestdemo.network.ApiService
import com.technerds.tmboowner.apiNetwork.base.BaseRepository
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AppRepository private constructor() : BaseRepository() {


    /* GET Request */
    // Get AllArticles Data request
    @SuppressLint("CheckResult")
    fun getAllArticlesData(
        strValue:String,
        getAllArticlesModel: GetAllArticlesModel,
        onResult: (NYTimesMostPopularArticlesResponse?) -> Unit
    ) {
        val observable =
            ApiService.postUserDataCall()
                .getMostViewsArticles(strValue,getAllArticlesModel.articlesModel.toQueryMap())

        observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { postResponse ->
                    Log.d("=>", "ApiResult:$postResponse ")
                    onResult(postResponse)
                },


                { error ->


                    if (error is HttpException) {
                        // FBToast.
                        Log.d("=>", "error:$error ")
                        onResult(null)
                    }
                },
            )
    }


    companion object {
        private var _instance: AppRepository? = null
        fun getInstance(): AppRepository {
            if (_instance == null)
                _instance = AppRepository()
            return _instance!!
        }
    }
}