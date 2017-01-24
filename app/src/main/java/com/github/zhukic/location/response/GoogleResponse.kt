package com.github.zhukic.location.response

import com.google.gson.annotations.SerializedName

/**
 * Created by Vladislav Zhukov on 24.01.2017.
 */
data class GoogleResponse(@SerializedName("location") var location: Location,
                     @SerializedName("accuracy") var accuracy: Double = 0.0)