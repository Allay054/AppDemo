package com.holatourism.fiore.convextechtestdemo.viewModels

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.holatourism.fiore.convextechtestdemo.allResponses.NYTimesMostPopularArticlesResponse
import com.holatourism.fiore.convextechtestdemo.dataModels.GetAllArticlesModel
import com.holatourism.fiore.convextechtestdemo.views.MyAppView
import com.technerds.tmboowner.apiNetwork.base.BaseViewModel

class MyAppViewModel constructor(private val repository: AppRepository) :
    BaseViewModel<MyAppView>() {

    /*Get All Articles*/
    private val mutableLiveDataAllArticles = MutableLiveData<NYTimesMostPopularArticlesResponse>()
    private val allArticlesObserver: Observer<in NYTimesMostPopularArticlesResponse> = Observer {
        getView().onNYTimesMostPopularArticlesResult(it)
    }

    fun getAllArticlesModel(strValue:String,articlesModel: GetAllArticlesModel) {
        repository.getAllArticlesData(strValue,articlesModel) {
            mutableLiveDataAllArticles.postValue(it)
        }
    }


    override fun attachView(view: MyAppView, lifecycleOwner: LifecycleOwner) {
        super.attachView(view, lifecycleOwner)
        mutableLiveDataAllArticles.observe(lifecycleOwner, allArticlesObserver)



    }

    override fun onCleared() {
        super.onCleared()
        mutableLiveDataAllArticles.removeObserver(allArticlesObserver)


    }
}