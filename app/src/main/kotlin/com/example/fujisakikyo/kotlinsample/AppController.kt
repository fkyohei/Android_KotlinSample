package com.example.fujisakikyo.kotlinsample

import android.app.Application
import android.util.Log
import com.activeandroid.ActiveAndroid

/**
 * Created by fujisakikyo on 15/04/30.
 */
class AppController: Application() {

    override fun onCreate() {
        super<Application>.onCreate()
        init()
    }

    override fun onTerminate() {
        terminate()
    }

    fun init() {
        Log.d("AppController", "init")
        ActiveAndroid.initialize(this)
    }

    fun terminate() {
        ActiveAndroid.dispose()
    }
}