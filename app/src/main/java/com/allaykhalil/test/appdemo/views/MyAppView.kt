package com.allaykhalil.test.appdemo.views

import com.allaykhalil.test.appdemo.allResponses.NYTimesMostPopularArticlesResponse

interface MyAppView {
    fun onNYTimesMostPopularArticlesResult(response: NYTimesMostPopularArticlesResponse?) {}
}