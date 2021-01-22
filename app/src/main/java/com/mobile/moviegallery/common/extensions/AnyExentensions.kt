package com.mobile.moviegallery.common.extensions

import com.google.gson.Gson

fun Any.toJsonString(): String {
    return Gson().toJson(this)
}