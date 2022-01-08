package com.holatourism.fiore.convextechtestdemo.utils.noInternetDialogs


abstract class BaseDialogProperties(
    var cancelable: Boolean = false,
    var connectionCallback: ConnectionCallback? = null
)