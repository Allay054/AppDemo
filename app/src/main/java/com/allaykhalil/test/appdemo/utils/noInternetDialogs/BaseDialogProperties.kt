package com.allaykhalil.test.appdemo.utils.noInternetDialogs


abstract class BaseDialogProperties(
    var cancelable: Boolean = false,
    var connectionCallback: ConnectionCallback? = null
)