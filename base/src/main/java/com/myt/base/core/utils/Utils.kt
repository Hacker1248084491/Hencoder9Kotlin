package com.myt.base.core.utils

import android.content.res.Resources
import android.util.TypedValue
import android.widget.Toast
import com.myt.base.core.BaseApplication

private val displayMetrics = Resources.getSystem().displayMetrics
fun Float.dp2px() = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this, displayMetrics)

object Utils {
    fun toast(string: String, duration: Int = Toast.LENGTH_SHORT) = Toast.makeText(BaseApplication.currentApplication, string, duration).show()
}