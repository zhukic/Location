package com.github.zhukic.location.api

import com.github.zhukic.location.response.GoogleResponse
import com.github.zhukic.location.telephony.GeoLocationRequest
import io.reactivex.Flowable
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * Created by Vladislav Zhukov on 24.01.2017.
 */
interface Api {

    @POST("geolocate")
    fun getLocation(@Query("key") key: String, @Body request: GeoLocationRequest) : Flowable<GoogleResponse>

    companion object {
        val BASE_URL: String = "https://www.googleapis.com/geolocation/v1/"
        val API_KEY = "AIzaSyCSx_L8pToUYTK5xbzn6bAqK3QjXET65WM"
    }
}