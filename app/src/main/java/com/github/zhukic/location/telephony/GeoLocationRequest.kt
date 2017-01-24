package com.github.zhukic.location.telephony

/**
 * Created by Vladislav Zhukov on 24.01.2017.
 */
data class GeoLocationRequest(val homeMobileCountryCode: Int,
                              val homeNetworkCountryCode: Int,
                              val radioType: String,
                              val carrier: String,
                              val considerIp: Boolean,
                              val cellTowers: List<CellTower>,
                              val wifiAccessPoints: List<WifiAccessPoint>)
