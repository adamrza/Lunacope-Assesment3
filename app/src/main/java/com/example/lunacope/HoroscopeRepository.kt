package com.example.lunacope

import com.example.lunacope.model.HoroscopeResponse
import com.example.lunacope.network.ILunacopeApi
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class HoroscopeRepository @Inject constructor(private val apiService: ILunacopeApi) {

  fun getHoroscope(sign: String, date: String): Single<HoroscopeResponse> {
    return apiService.getHoroscope(sign, date)
      .subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread())
  }
}