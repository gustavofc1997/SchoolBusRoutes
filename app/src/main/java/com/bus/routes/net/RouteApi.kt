package com.bus.routes.net

import android.telecom.Call
import com.bus.routes.net.responses.RoutesResponse
import io.reactivex.Observable
import retrofit2.http.GET

interface RouteApi {

    @GET
    fun getRoutes():Observable<RoutesResponse>
}