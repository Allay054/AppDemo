package com.allaykhalil.test.appdemo.viewModels

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.allaykhalil.test.appdemo.allResponses.NYTimesMostPopularArticlesResponse
import com.allaykhalil.test.appdemo.dataModels.GetAllArticlesModel
import com.allaykhalil.test.appdemo.views.MyAppView
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