package com.example.fujisakikyo.kotlinsample

import android.app.Application
import android.util.Log
import com.activeandroid.ActiveAndroid
import com.activeandroid.Configuration

/**
 * Created by fujisakikyo on 15/04/30.
 */
class AppController: Application() {

    companion object {
        val DB_NAME = "KotlinSample.db"
    }

    override fun onCreate() {
        super<Application>.onCreate()
        init()
    }

    override fun onTerminate() {
        super<Application>.onTerminate();
        terminate()
    }

    fun init() {
        val builder = Configuration.Builder(getBaseContext())
                    ?.setDatabaseName(DB_NAME)
                    ?.setDatabaseVersion(2)
                    ?.create()
        ActiveAndroid.initialize(builder)
    }

    fun terminate() {
        ActiveAndroid.dispose()
    }

}