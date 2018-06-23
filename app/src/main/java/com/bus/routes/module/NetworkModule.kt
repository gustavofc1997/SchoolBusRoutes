package com.bus.routes.module


import com.bus.routes.net.RouteApi
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.Route
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkModule @Inject
constructor() {
    var ApiBaseUrl = "https://api.myjson.com/bins/10yg1t"

    lateinit var mApi: RouteApi

    init {
        val okHttpClient = OkHttpClient().newBuilder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .build()
        val gson = GsonBuilder()
                .setLenient()
                .create()
        val retrofit = Retrofit.Builder().baseUrl(ApiBaseUrl).client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson)).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build()
        mApi = retrofit.create(RouteApi::class.java)

    }

}
