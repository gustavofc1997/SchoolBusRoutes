package com.bus.routes.net

import android.telecom.Call
import com.bus.routes.net.responses.RoutesResponse
import com.bus.routes.net.responses.StopsResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Url

interface RouteApi {

    @GET("10yg1t")
    fun getRoutes(): Observable<RoutesResponse>

    @GET
    fun getStops(@Url url: String): Observable<StopsResponse>
}