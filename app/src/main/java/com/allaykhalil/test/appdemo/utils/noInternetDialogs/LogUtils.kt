package com.allaykhalil.test.appdemo.utils.noInternetDialogs

import android.util.Log
import androidx.databinding.ktx.BuildConfig

internal object LogUtils {
    fun d(tag: String, msg: String) {
        if (BuildConfig.DEBUG) {
            Log.d(tag, msg)
        }
    }

    fun e(tag: String, msg: String) {
        if (BuildConfig.DEBUG) {
            Log.e(tag, msg)
        }
    }
}