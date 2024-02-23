package com.myt.base.core.utils

import android.annotation.SuppressLint
import android.content.Context
import com.myt.base.R
import com.myt.base.core.BaseApplication

@SuppressLint("StaticFieldLeak")
object CacheUtils {

    private val context = BaseApplication.currentApplication

    private val SP = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)

    fun save(key: String, value: String) = SP.edit().putString(key, value).apply()

    fun get(key: String) = SP.getString(key, null)

}