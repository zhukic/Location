package com.github.zhukic.location.telephony

/**
 * Created by Vladislav Zhukov on 24.01.2017.
 */
data class CellTower(val cellId: Int,
                     val locationAreaCode: Int,
                     val mobileCountryCode: Int,
                     val networkCountryCode: Int,
                     val age: Int,
                     val signalStrength: Int,
                     val timingAdvance: Int)