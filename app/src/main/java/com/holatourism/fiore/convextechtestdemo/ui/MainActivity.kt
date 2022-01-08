package com.holatourism.fiore.convextechtestdemo.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.holatourism.fiore.convextechtestdemo.adapters.AllArticlesAdapter
import com.holatourism.fiore.convextechtestdemo.allResponses.AllArticlesData
import com.holatourism.fiore.convextechtestdemo.allResponses.NYTimesMostPopularArticlesResponse
import com.holatourism.fiore.convextechtestdemo.base.InternetOnOff
import com.holatourism.fiore.convextechtestdemo.dataModels.GetAllArticlesModel
import com.holatourism.fiore.convextechtestdemo.databinding.ActivityMainBinding
import com.holatourism.fiore.convextechtestdemo.utils.GlobalData
import com.holatourism.fiore.convextechtestdemo.utils.UtilMethods
import com.holatourism.fiore.convextechtestdemo.utils.noInternetDialogs.ConnectionCallback
import com.holatourism.fiore.convextechtestdemo.utils.noInternetDialogs.NoInternetDialogSignal
import com.holatourism.fiore.convextechtestdemo.viewModels.MyAppViewModel
import com.holatourism.fiore.convextechtestdemo.views.InternetOnOffListener
import com.holatourism.fiore.convextechtestdemo.views.MyAppView
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : InternetOnOff(), InternetOnOffListener, MyAppView,
    AllArticlesAdapter.OnItemClickListener {
    private var isInternetConnectedNow: String? = null

    // No Internet Dialog: Signal
    private var noInternetDialogSignal: NoInternetDialogSignal? = null

    private val myAppViewModel: MyAppViewModel by viewModel()
    lateinit var binding: ActivityMainBinding

    private var listAllArticles: ArrayList<AllArticlesData> = ArrayList()
    private lateinit var adapter: AllArticlesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        buildNoInternetSignalDialog()
        setContentView(binding.root)
        myAppViewModel.attachView(this, this)

        handleThisScreen()

    }


    private fun handleThisScreen() {
        if (UtilMethods.isConnectedToInternet(this@MainActivity)) {
            UtilMethods.showLoadingKHud(this)

            val getAllArticlesModel = GetAllArticlesModel(
                GetAllArticlesModel.AllArticlesModel(
                    "s9NGzyfDL1t3UgiRKQB8zqnjmAE0ALZ0"
                )
            )

            myAppViewModel.getAllArticlesModel("7", getAllArticlesModel)
        } else {
            UtilMethods.showInternetNotFoundDialog(this@MainActivity)

        }
    }

    override fun onInternetChange(InternetState: String?) {
        isInternetConnectedNow = InternetState
    }

    private fun buildNoInternetSignalDialog() {
        noInternetDialogSignal = NoInternetDialogSignal.Builder(
            this,
            lifecycle
        ).apply {
            dialogProperties.apply {
                connectionCallback = object : ConnectionCallback { // Optional
                    override fun hasActiveConnection(hasActiveConnection: Boolean) {
                        // ...
                    }
                }

                cancelable = false // Optional
                noInternetConnectionTitle = "No Internet" // Optional
                noInternetConnectionMessage =
                    "Check your Internet connection and try again." // Optional
                showInternetOnButtons = true // Optional
                pleaseTurnOnText = "Please turn on" // Optional
                wifiOnButtonText = "Wifi" // Optional
                mobileDataOnButtonText = "Mobile data" // Optional

                onAirplaneModeTitle = "No Internet" // Optional
                onAirplaneModeMessage = "You have turned on the airplane mode." // Optional
                pleaseTurnOffText = "Please turn off" // Optional
                airplaneModeOffButtonText = "Airplane mode" // Optional
                showAirplaneModeOffButtons = true // Optional
            }
        }.build()
    }


    override fun onNYTimesMostPopularArticlesResult(response: NYTimesMostPopularArticlesResponse?) {


        Log.d("=>", "onAPIResult: $response")
        if (response == null) {
            UtilMethods.showInfoToast(this@MainActivity, "Internal Server Error")
            return
        }

        if (!response.allArticlesList.isNullOrEmpty()) {
            showDataToView(response.allArticlesList)

        } else {
            UtilMethods.showInfoToast(this@MainActivity, "Articles list is empty")
        }
        //  val resultOK:String="ok"

        UtilMethods.hideLoadingKHud()
    }

    private fun showDataToView(list: ArrayList<AllArticlesData>) {
        binding.recyclerViewAllArticles.layoutManager =
            LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)

        listAllArticles = list
        adapter =
            AllArticlesAdapter(
                this@MainActivity,
                listAllArticles,
                this

            )
        binding.recyclerViewAllArticles.adapter = adapter
        if (listAllArticles.size > 0) {
            binding.layoutEmpty.visibility = View.GONE

        } else {
            binding.layoutEmpty.visibility = View.VISIBLE
        }


    }

    override fun onItemClickListener(position: Int, allArticlesData: AllArticlesData) {
        GlobalData.selectedArticlesData = allArticlesData
         val intent = Intent(this@MainActivity, SelectedArticleDetailActivity::class.java)
        startActivity(intent)
        finish()
    }
}