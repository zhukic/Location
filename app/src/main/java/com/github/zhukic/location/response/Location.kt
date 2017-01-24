package com.github.zhukic.location.response

import com.google.gson.annotations.SerializedName

/**
 * Created by Vladislav Zhukov on 24.01.2017.
 */
data class Location(@SerializedName("lat") var lat: Double = 0.0,
                    @SerializedName("lng") var lng: Double = 0.0)