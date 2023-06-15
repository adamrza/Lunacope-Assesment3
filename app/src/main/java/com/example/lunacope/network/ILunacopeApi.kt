package com.example.lunacope.network

import com.example.lunacope.model.HoroscopeResponse
import com.example.lunacope.model.Sign
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ILunacopeApi {
  @GET("list")
  fun getSignList(): Single<ArrayList<Sign>>

  @GET("{sign}/")
  fun getHoroscope(@Path("sign") sign: String, @Query("date") date: String): Single<HoroscopeResponse>
}