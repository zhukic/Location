package com.github.zhukic.location

import android.net.wifi.ScanResult
import android.net.wifi.WifiManager
import android.telephony.TelephonyManager
import com.github.zhukic.location.telephony.CellTower
import com.github.zhukic.location.telephony.GeoLocationRequest
import com.github.zhukic.location.telephony.WifiAccessPoint

/**
 * Created by Vladislav Zhukov on 24.01.2017.
 */
class TelephonyMapper(val telephonyManager: TelephonyManager, val wifiManager: WifiManager, val scanResult: List<ScanResult>) {

    fun getGeoLocationRequest(): GeoLocationRequest {
        return GeoLocationRequest(getHomeMobileCountryCode(),
                getHomeNetworkCountryCode(),
                getRadioType(),
                getCarrier(),
                isConsiderIp(),
                getCellTowers(),
                getWifiAccessPoints())
    }

    private fun getHomeMobileCountryCode(): Int {
        //return telephonyManager.simCountryIso
        return 112;
    }

    private fun getHomeNetworkCountryCode(): Int {
        return 112;
    }

    private fun getRadioType(): String {
        when(telephonyManager.phoneType) {
            TelephonyManager.PHONE_TYPE_GSM -> return "gsm"
            TelephonyManager.PHONE_TYPE_CDMA -> return "cdma"
            TelephonyManager.PHONE_TYPE_SIP -> return "sip"
            TelephonyManager.PHONE_TYPE_NONE -> return "none"
            else -> return "none"
        }
    }

    private fun getCarrier(): String {
        return telephonyManager.simOperator
    }

    private fun isConsiderIp(): Boolean {
        return true
    }

    private fun getCellTowers(): List<CellTower> {
        val cellTowers = mutableListOf<CellTower>()
/*        val cellLocation = telephonyManager.cellLocation as GsmCellLocation
        cellTowers.add(CellTower(cellLocation.cid, cellLocation.lac, 112, 112, 0, 0, 0))*/
        return cellTowers
    }

    private fun getWifiAccessPoints(): List<WifiAccessPoint> {
        val wifiAccessPoints = mutableListOf<WifiAccessPoint>()
        scanResult.forEach {
            wifiAccessPoints.add(WifiAccessPoint(it.BSSID, it.level, 0, CHANNELS_FREQUENCY.indexOf(it.frequency), 0))
        }
        return wifiAccessPoints
    }

    companion object {
        private val CHANNELS_FREQUENCY = listOf(0, 2412, 2417, 2422, 2427, 2432, 2437, 2442, 2447,
                2452, 2457, 2462, 2467, 2472, 2484)
    }

}