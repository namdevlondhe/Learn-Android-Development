package com.learn.androiddevelopment.util

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import android.widget.Toast
import java.io.IOException
import java.nio.charset.Charset
import java.util.*

class AppFunctions {

    companion object {

        fun getRandomNumber(): Int {
            val min = 1
            val max = 5
            val random: Int = Random().nextInt(max - min + 1) + min
            Log.d("RandomNumber: ", "Number->" + random)
            return random;
        }

        fun loadJSONFromAsset(context: Context): String? {
            var json: String? = null
            json = try {
                val `is` = context.assets.open("android_development.json")
                val size = `is`.available()
                val buffer = ByteArray(size)
                `is`.read(buffer)
                `is`.close()
                String(buffer, Charset.forName("UTF-8"))
            } catch (ex: IOException) {
                ex.printStackTrace()
                return null
            }
            return json
        }

        fun isNetWorkAvailable(context: Context): Boolean {
            val connMgr = context
                .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

            val networkInfo = connMgr.activeNetworkInfo

            if (networkInfo != null && networkInfo.isConnected) {
                return true
            }
            return false
        }
    }
}