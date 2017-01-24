package com.github.zhukic.location.telephony

/**
 * Created by Vladislav Zhukov on 24.01.2017.
 */
data class WifiAccessPoint(val macAddress: String,
                           val singalStrength: Int,
                           val age: Int,
                           val channel: Int,
                           val signalToNoiseRatio: Int)