package com.myt.base.core

import android.view.View
import android.widget.TextView
import androidx.annotation.IdRes
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    private val viewHashMap = hashMapOf<Int, View>()

    protected fun <T: View> getView(@IdRes id: Int): T{
        val view = viewHashMap[id].takeIf {
            it != null
        } ?: {
            val newView = itemView.findViewById<T>(id)
            viewHashMap[id] = newView
            newView
        }
        return view as T
    }

    protected fun setText(@IdRes id: Int, text: String?){
        getView<TextView>(id).text = text
    }

}