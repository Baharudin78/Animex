package com.baharudin.animex.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

object ApiState {

    fun hasInternetConnection(context: Context?) : Boolean {
        if (context == null)
            return false
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE)
            as ConnectivityManager

        val activityNetWork = connectivityManager.activeNetwork ?:return false
        val networkCapabilities = connectivityManager.getNetworkCapabilities(activityNetWork) ?: return false

        return when{
            networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true

            else -> false
        }
    }
}