package com.myt.base.core.http

import com.google.gson.Gson
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import java.lang.reflect.Type

object HttpClient: OkHttpClient(){

    private val gson by lazy { Gson() }

    private fun <T> convert(json: String, type: Type) = gson.fromJson<T>(json, type)

    fun <T> get(path: String, type: Type, entityCallback: EntityCallback<T>){
        val request = Request.Builder()
            .url("https://api.hencoder.com/$path")
            .build()

        newCall(request).enqueue(object: Callback{
            override fun onFailure(call: Call, e: IOException) {
                entityCallback.onFailure("网络异常")
            }
            override fun onResponse(call: Call, response: Response) {
                when(response.code()){
                    in 200 until 300 -> entityCallback.onSuccess(convert(response.body().toString(), type))
                    in 400 until 500 -> entityCallback.onFailure("客户端错误")
                    in 500 until 600 -> entityCallback.onFailure("服务器错误")
                    else -> entityCallback.onFailure("未知错误")
                }
            }
        })
    }

}