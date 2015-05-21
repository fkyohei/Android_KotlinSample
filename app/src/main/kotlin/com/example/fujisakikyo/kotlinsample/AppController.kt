package com.example.fujisakikyo.kotlinsample

import android.app.Application
import android.util.Log
import com.activeandroid.ActiveAndroid
import com.activeandroid.Configuration

/**
 * Created by fujisakikyo on 15/04/30.
 */
class AppController: Application() {

    override fun onCreate() {
        super<Application>.onCreate()
        init()
    }

    override fun onTerminate() {
        super<Application>.onTerminate();
        terminate()
    }

    fun init() {
        Log.d("AppController", "init")
        var builder: Configuration.Builder = Configuration.Builder(getBaseContext());
        builder.setCacheSize(1024*1024*4);
        builder.setDatabaseName("KotlinSample.db");
        builder.setDatabaseVersion(1);
        ActiveAndroid.initialize(builder.create(), true);
        var mInstance = this;
    }

    fun terminate() {
        ActiveAndroid.dispose()
    }
}