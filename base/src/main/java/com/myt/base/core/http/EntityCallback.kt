package com.myt.base.core.http

interface EntityCallback<T> {
    fun onSuccess(entity: T)
    fun onFailure(message: String?)
}