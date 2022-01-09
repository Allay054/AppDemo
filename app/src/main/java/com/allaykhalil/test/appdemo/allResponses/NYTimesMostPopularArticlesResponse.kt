package com.allaykhalil.test.appdemo.allResponses

import com.google.gson.annotations.SerializedName

data class NYTimesMostPopularArticlesResponse(

    @SerializedName("status") val status: String? = null,
    @SerializedName("copyright") val copyright: String? = null,
    @SerializedName("num_results") val num_results: Int? = null,
    @SerializedName("results") val allArticlesList: ArrayList<AllArticlesData>? = null

)

data class AllArticlesData(
    @SerializedName("uri") val uri: String? = null,
    @SerializedName("url") val url: String? = null,
    @SerializedName("id") val id: String? = null,
    @SerializedName("asset_id") val asset_id: String? = null,
    @SerializedName("source") val source: String? = null,
    @SerializedName("published_date") val published_date: String? = null,
    @SerializedName("updated") val updated: String? = null,
    @SerializedName("section") val section: String? = null,
    @SerializedName("subsection") val subsection: String? = null,
    @SerializedName("nytdsection") val nytdsection: String? = null,
    @SerializedName("adx_keywords") val adx_keywords: String? = null,
    @SerializedName("column") val column: String? = null,
    @SerializedName("byline") val byline: String? = null,
    @SerializedName("type") val type: String? = null,
    @SerializedName("title") val title: String? = null,
    @SerializedName("abstract") val abstract: String? = null,
    @SerializedName("des_facet") val desFacet: ArrayList<String>? = null,
    @SerializedName("org_facet") val orgFacet: ArrayList<String>? = null,
    @SerializedName("per_facet") val per_facet: ArrayList<String>? = null,
    @SerializedName("geo_facet") val geo_facet: ArrayList<String>? = null,
    @SerializedName("media") val mediaList: ArrayList<MediaObj>? = null,
    @SerializedName("eta_id") val eta_id: Int? = null
)

data class MediaObj(
    @SerializedName("type") val type: String? = null,
    @SerializedName("subtype") val subtype: String? = null,
    @SerializedName("caption") val caption: String? = null,
    @SerializedName("copyright") val copyright: String? = null,
    @SerializedName("approved_for_syndication") val approved_for_syndication: Int? = null,
    @SerializedName("media-metadata") val mediaMetaDataList: ArrayList<MediaMeatDataObj>? = null,

    )



data class MediaMeatDataObj(
    @SerializedName("url") val url: String? = null,
    @SerializedName("format") val format: String? = null,
    @SerializedName("height") val height: Int? = null,
    @SerializedName("width") val width: Int? = null
)

