package com.myt.base.core

import android.app.Application
import android.content.Context

class BaseApplication: Application(){

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        currentApplication = this
    }

    companion object{
        @get:JvmName("currentApplication")
        lateinit var currentApplication: Context
            private set
    }

}