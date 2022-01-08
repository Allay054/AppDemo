package com.holatourism.fiore.convextechtestdemo.views

import com.holatourism.fiore.convextechtestdemo.allResponses.NYTimesMostPopularArticlesResponse
import org.json.JSONObject

interface MyAppView {
    fun onNYTimesMostPopularArticlesResult(response: NYTimesMostPopularArticlesResponse?) {}
}