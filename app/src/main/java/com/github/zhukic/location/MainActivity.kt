package com.github.zhukic.location

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.wifi.WifiManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.telephony.TelephonyManager
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.github.zhukic.location.api.Api
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    lateinit var retrofit: Retrofit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createRetrofit()

        val telephonyManager = getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        val wifiManager = getSystemService(Context.WIFI_SERVICE) as WifiManager

        wifiManager.startScan()

        registerReceiver(object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                val mapper = TelephonyMapper(telephonyManager, wifiManager, wifiManager.scanResults)
                retrofit.create(Api::class.java).getLocation(Api.API_KEY, mapper.getGeoLocationRequest()).observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                { googleResponse ->
                                    cellText.text = googleResponse.toString()
                                },
                                { error ->
                                    cellText.text = error.message
                                })
            }

        }, IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION))
    }

    fun createRetrofit() {
        val okhttp = OkHttpClient.Builder()
                .addInterceptor(StethoInterceptor())
                .build()
        retrofit = Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .addConverterFactory(GsonConverterFactory.create())
                .client(okhttp)
                .build()
    }
}
