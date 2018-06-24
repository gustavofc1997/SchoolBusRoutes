package com.bus.routes.net

import android.telecom.Call
import com.bus.routes.net.responses.RoutesResponse
import io.reactivex.Observable
import retrofit2.http.GET

interface RouteApi {

    @GET("10yg1t")
    fun getRoutes():Observable<RoutesResponse>
}