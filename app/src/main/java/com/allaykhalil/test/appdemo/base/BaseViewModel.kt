package com.technerds.tmboowner.apiNetwork.base

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel

abstract class BaseViewModel<View> : ViewModel() {
    private var view: View? = null

    open fun attachView(view: View, lifecycleOwner: LifecycleOwner) {
        this.view = view
    }

    protected fun getView(): View {
        if (view == null)
            throw NullPointerException("View is null please call attach method 1st")
        return view!!
    }

    override fun onCleared() {
        view = null
    }
}