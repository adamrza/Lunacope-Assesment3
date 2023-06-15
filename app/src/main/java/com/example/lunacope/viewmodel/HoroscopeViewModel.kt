package com.example.lunacope.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lunacope.HoroscopeRepository
import com.example.lunacope.model.HoroscopeResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

@HiltViewModel
class HoroscopeViewModel @Inject constructor( private val repository: HoroscopeRepository): ViewModel() {
  private val compositeDisposable = CompositeDisposable()
  private val _horoscopeResponse = MutableLiveData<HoroscopeResponse>()
  val horoscopeResponse: LiveData<HoroscopeResponse> = _horoscopeResponse

  fun getHoroscopeRespone(sign: String, date: String) {
    compositeDisposable.add(
      repository.getHoroscope(sign, date).subscribe(
        {
          _horoscopeResponse.postValue(it)
        },
        {
          Log.e("error", it.message.toString())
        }
      )
    )
  }
  override fun onCleared() {
    super.onCleared()
    compositeDisposable.dispose()
  }
}