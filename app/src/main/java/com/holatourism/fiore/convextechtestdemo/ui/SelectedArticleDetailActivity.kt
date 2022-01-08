package com.holatourism.fiore.convextechtestdemo.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.holatourism.fiore.convextechtestdemo.adapters.SelectedArticlePicturesAdapter
import com.holatourism.fiore.convextechtestdemo.allResponses.MediaMeatDataObj
import com.holatourism.fiore.convextechtestdemo.databinding.ActivitySelectedArticleDetailBinding
import com.holatourism.fiore.convextechtestdemo.utils.GlobalData
import com.holatourism.fiore.convextechtestdemo.utils.UtilMethods
import java.lang.StringBuilder

class SelectedArticleDetailActivity : AppCompatActivity(),
    SelectedArticlePicturesAdapter.OnItemClickListener {
    lateinit var binding: ActivitySelectedArticleDetailBinding
    private lateinit var builderDesFacet: StringBuilder
    private lateinit var builderOrgFacet: StringBuilder
    private lateinit var builderPerFacet: StringBuilder
    private lateinit var builderGeoFacet: StringBuilder

    private val mediaMetaImagesList: ArrayList<MediaMeatDataObj> = ArrayList()
    private lateinit var selectedArticlePicturesAdapter: SelectedArticlePicturesAdapter
    // private val allMediaList: ArrayList<ArrayList<MediaMeatDataObj>> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectedArticleDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        handleThisActivity()
    }

    private fun handleThisActivity() {

        if (GlobalData.selectedArticlesData != null) {
            /******************Title***************/
            if (GlobalData.selectedArticlesData!!.title.toString() != "null") {
                binding.articleTitle.text = GlobalData.selectedArticlesData!!.title.toString()
            }
            /******************Uri***************/

            if (GlobalData.selectedArticlesData!!.uri.toString() != "null") {
                binding.articleUri.text = GlobalData.selectedArticlesData!!.uri.toString()
            }


            /******************Url***************/

            if (GlobalData.selectedArticlesData!!.url.toString() != "null") {
                binding.articleUrl.text = GlobalData.selectedArticlesData!!.url.toString()
            }

            /******************Source***************/

            if (GlobalData.selectedArticlesData!!.source.toString() != "null") {
                binding.articleSource.text = GlobalData.selectedArticlesData!!.source.toString()
            }


            /******************Source***************/

            if (GlobalData.selectedArticlesData!!.source.toString() != "null") {
                binding.articleSource.text = GlobalData.selectedArticlesData!!.source.toString()
            }


            /******************Published Date***************/

            if (GlobalData.selectedArticlesData!!.published_date.toString() != "null") {
                binding.articleSource.text =
                    GlobalData.selectedArticlesData!!.published_date.toString()
            }


            /******************Section***************/

            if (GlobalData.selectedArticlesData!!.section.toString() != "null") {
                binding.articleSection.text = GlobalData.selectedArticlesData!!.section.toString()
            }


            /******************Nytdsection***************/

            if (GlobalData.selectedArticlesData!!.nytdsection.toString() != "null") {
                binding.articleNytdsection.text =
                    GlobalData.selectedArticlesData!!.nytdsection.toString()
            }


            /******************ByLine***************/

            if (GlobalData.selectedArticlesData!!.byline.toString() != "null") {
                binding.articlebyline.text = GlobalData.selectedArticlesData!!.byline.toString()
            }


            /******************Type***************/

            if (GlobalData.selectedArticlesData!!.type.toString() != "null") {
                binding.articleType.text = GlobalData.selectedArticlesData!!.type.toString()
            }


            /******************Abstract***************/

            if (GlobalData.selectedArticlesData!!.abstract.toString() != "null") {
                binding.articleAbstract.text = GlobalData.selectedArticlesData!!.abstract.toString()
            }


            /******************Des_fact***************/

            if (!GlobalData.selectedArticlesData!!.desFacet.isNullOrEmpty()) {
                builderDesFacet = StringBuilder()
                for (item in GlobalData.selectedArticlesData!!.desFacet!!) {
                    builderDesFacet.append(item).append(",")
                }
                binding.articleDxKeywords.text =
                    builderDesFacet.toString()
            } else {
                binding.articleDxKeywords.text = "N/A"

            }

            /******************org_fact***************/

            if (!GlobalData.selectedArticlesData!!.orgFacet.isNullOrEmpty()) {
                builderOrgFacet = StringBuilder()
                for (item in GlobalData.selectedArticlesData!!.orgFacet!!) {
                    builderOrgFacet.append(item).append(",")
                }
                binding.articleOrgFacet.text =
                    builderOrgFacet.toString()
            } else {
                binding.articleOrgFacet.text = "N/A"

            }

            /******************per_fact***************/

            if (!GlobalData.selectedArticlesData!!.per_facet.isNullOrEmpty()) {
                builderPerFacet = StringBuilder()
                for (item in GlobalData.selectedArticlesData!!.per_facet!!) {
                    builderPerFacet.append(item).append(",")
                }
                binding.articlePerFacet.text =
                    builderPerFacet.toString()
            } else {
                binding.articlePerFacet.text = "N/A"

            }

            /******************geo_fact***************/

            if (!GlobalData.selectedArticlesData!!.geo_facet.isNullOrEmpty()) {
                builderGeoFacet = StringBuilder()
                for (item in GlobalData.selectedArticlesData!!.geo_facet!!) {
                    builderGeoFacet.append(item).append(",")
                }
                binding.articleGeoFacet.text =
                    builderGeoFacet.toString()
            } else {
                binding.articleGeoFacet.text = "N/A"

            }

            /**************************Add Boat Pictures Photos***********************/
            if (!GlobalData.selectedArticlesData!!.mediaList.isNullOrEmpty()) {
                mediaMetaImagesList.clear()
                GlobalData.selectedArticlesData!!.mediaList?.let {
                    for (p2 in it) {

                        p2.mediaMetaDataList?.let {
                            for (p3 in it) {
                                mediaMetaImagesList.add(p3)
                            }
                        }

                    }
                }


            }
            addPicsDataToView()
        }

    }

    private fun addPicsDataToView() {

        binding.recyclerViewArticlesPhotos.layoutManager =
            LinearLayoutManager(
                this@SelectedArticleDetailActivity,
                LinearLayoutManager.HORIZONTAL,
                false
            )

        selectedArticlePicturesAdapter =
            SelectedArticlePicturesAdapter(
                this@SelectedArticleDetailActivity,
                mediaMetaImagesList,
                this
            )

        binding.recyclerViewArticlesPhotos.adapter = selectedArticlePicturesAdapter

        if (mediaMetaImagesList.size > 0) {
            binding.layoutEmpty.visibility = View.GONE

        } else {
            binding.layoutEmpty.visibility = View.VISIBLE
        }


    }

    override fun onItemClickListener(position: Int, mediaObj: MediaMeatDataObj) {
        UtilMethods.showInfoToast(this@SelectedArticleDetailActivity, "Do your required action")
    }

    override fun onBackPressed() {
        goBack()
    }

    private fun goBack() {
        val intent = Intent(this@SelectedArticleDetailActivity, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}