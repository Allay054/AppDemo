package com.allaykhalil.test.appdemo.dataModels


class GetAllArticlesModel(val articlesModel: AllArticlesModel) {


    class AllArticlesModel(


        private val strKey: String


    ) {
        fun toQueryMap(): HashMap<String, String> {
            val params = HashMap<String, String>()

            params["api-key"] = strKey
            return params
        }
    }

}