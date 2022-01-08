package  com.holatourism.fiore.convextechtestdemo.utils

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Build
import android.text.TextUtils
import android.util.Patterns
import android.view.Window
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowInsetsControllerCompat
import com.kaopiz.kprogresshud.KProgressHUD
import com.thecode.aestheticdialogs.AestheticDialog
import com.thecode.aestheticdialogs.DialogStyle
import com.thecode.aestheticdialogs.DialogType
import com.thecode.aestheticdialogs.OnDialogClickListener
import es.dmoral.toasty.Toasty

object UtilMethods {
    private lateinit var hud: KProgressHUD

    private val TAG: String = "---UtilMethods"


    fun showInfoToast(context: Context, message: String) {
        Toasty.info(context, message, Toast.LENGTH_SHORT, true).show();

    }


    fun showSuccessToast(context: Context, message: String) {
        Toasty.success(context, message, Toast.LENGTH_SHORT, true).show()
    }

    fun showErrorToast(context: Context, message: String) {
        Toasty.error(context, message, Toast.LENGTH_SHORT, true).show()
    }


    fun showLoadingKHud(context: Context) {
        //  Toast.makeText(this@LoginActivity,"Here",Toast.LENGTH_LONG).show()
        hud = KProgressHUD.create(context)
            .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
            .setDimAmount(0.5f)
        hud.show()
    }


    fun hideLoadingKHud() {
        hud.dismiss()
    }


    fun setCustomizeStatusBar(window: Window, statusBarColor: Int, statusTextColor: Boolean) {
        /* statusTextColor is true if only you
        want black color of status bar text*/
        val wic = WindowInsetsControllerCompat(window, window.decorView)
        wic.isAppearanceLightStatusBars = statusTextColor
        window.statusBarColor = statusBarColor
    }


    fun showInternetNotFoundDialog(activity: Context) {
        AestheticDialog.Builder(activity as Activity, DialogStyle.CONNECTIFY, DialogType.ERROR)
            .setTitle("Network unavailable")
            .setMessage("No internet connection")
            /*.setDuration(2000)*/
            .setOnClickListener(object : OnDialogClickListener {
                override fun onClick(dialog: AestheticDialog.Builder) {
                    dialog.dismiss()
                }
            })
            .show()
    }

    fun showSuccessDialog(activity: AppCompatActivity) {
        AestheticDialog.Builder(activity, DialogStyle.CONNECTIFY, DialogType.SUCCESS)
            .setTitle("Network found")
            .setMessage("Internet connection established")
            .setCancelable(false)
            .setDuration(2000)
            .setDarkMode(true)
            .setOnClickListener(object : OnDialogClickListener {
                override fun onClick(dialog: AestheticDialog.Builder) {
                    dialog.dismiss()
                }
            })
            .show()
    }

    fun showToasterError(activity: AppCompatActivity, errorTitle: String, errorMessage: String) {
        AestheticDialog.Builder(activity, DialogStyle.TOASTER, DialogType.ERROR)
            .setTitle(errorTitle)
            .setMessage(errorMessage)
            .setDarkMode(false)
            .setCancelable(false)

            .setOnClickListener(object : OnDialogClickListener {
                override fun onClick(dialog: AestheticDialog.Builder) {
                    dialog.dismiss()
                }
            })
            .show()
    }

    fun showToasterSuccess(
        activity: AppCompatActivity,
        successTitle: String,
        successMessage: String
    ) {
        AestheticDialog.Builder(activity, DialogStyle.TOASTER, DialogType.SUCCESS)
            .setTitle(successTitle)
            .setMessage(successMessage)
            .setDarkMode(false)
            .setCancelable(false)
            .setOnClickListener(object : OnDialogClickListener {
                override fun onClick(dialog: AestheticDialog.Builder) {
                    dialog.dismiss()
                }
            })
            .show()
    }

    fun showToasterWarning(
        activity: AppCompatActivity,
        warningTitle: String,
        warningMessage: String
    ) {
        AestheticDialog.Builder(activity, DialogStyle.TOASTER, DialogType.WARNING)
            .setTitle(warningTitle)
            .setMessage(warningMessage)
            .setDarkMode(false)
            .setCancelable(false)
            .setOnClickListener(object : OnDialogClickListener {
                override fun onClick(dialog: AestheticDialog.Builder) {
                    dialog.dismiss()
                }
            })
            .show()
    }

    fun showToasterInfo(
        activity: Context,
        infoTitle: String,
        infoMessage: String
    ) {
        AestheticDialog.Builder(activity as Activity, DialogStyle.TOASTER, DialogType.INFO)
            .setTitle(infoTitle)
            .setMessage(infoMessage)
            .setDarkMode(false)
            .setCancelable(false)
            .setOnClickListener(object : OnDialogClickListener {
                override fun onClick(dialog: AestheticDialog.Builder) {
                    dialog.dismiss()
                }
            })
            .show()
    }


    fun isConnectedToInternet(context: Context): Boolean {
        val manager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
        val allNetworks = manager?.allNetworks?.let { it } ?: return false
        allNetworks.forEach { network ->
            val info = manager.getNetworkInfo(network)
            if (info!!.state == NetworkInfo.State.CONNECTED) return true
        }
        return false
    }


    fun isValidEmail(target: String): Boolean {
        return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches()
    }
}