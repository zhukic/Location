package com.github.zhukic.location

import android.app.Application
import com.facebook.stetho.Stetho

/**
 * Created by Vladislav Zhukov on 24.01.2017.
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()

        Stetho.initializeWithDefaults(this)
    }
}