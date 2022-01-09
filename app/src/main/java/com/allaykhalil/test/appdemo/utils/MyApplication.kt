package com.allaykhalil.test.appdemo.utils

import android.app.Application
import com.allaykhalil.test.appdemo.viewModels.AppRepository
import com.allaykhalil.test.appdemo.viewModels.MyAppViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class MyApplication : Application() {


    override fun onCreate() {
        super.onCreate()
        instance = this
        initKoin()
    }


    companion object {
        val TAG = MyApplication::class.java
            .simpleName
        var instance: MyApplication? = null
            set
    }

    private fun initKoin() {
        val mvvmModule = module {
            viewModel {
                MyAppViewModel(get())
            }

            single {
                AppRepository.getInstance()
            }

        }

        startKoin {
            androidContext(applicationContext)
            modules(mvvmModule)
        }
    }
}