package com.csw.android.thespeedreading.app

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex

class MyApplication : Application() {
    companion object {
        lateinit var instance: MyApplication
    }
    override fun onCreate() {
        super.onCreate()
        MyApplication.instance = this
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

}