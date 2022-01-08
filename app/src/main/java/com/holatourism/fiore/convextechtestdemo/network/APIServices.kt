package com.holatourism.fiore.convextechtestdemo.network

import com.holatourism.fiore.convextechtestdemo.allResponses.NYTimesMostPopularArticlesResponse
import io.reactivex.Observable
import retrofit2.http.*

interface APIServices {


    //    @POST("mostviewed/all-sections/7.json")
    @POST("mostviewed/all-sections/{param}.json")
    fun getMostViewsArticles(
        @Path("param") param: String,
        @QueryMap params: HashMap<String, String>

    ): Observable<NYTimesMostPopularArticlesResponse>


}