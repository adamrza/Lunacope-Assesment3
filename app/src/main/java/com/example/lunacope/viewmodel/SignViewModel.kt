package com.example.lunacope.viewmodel

import androidx.lifecycle.ViewModel

class SignViewModel: ViewModel() {

  fun getSignList(): ArrayList<String> {
    var signList = ArrayList<String>()
    signList.add("Aries")
    signList.add("Taurus")
    signList.add("Gemini")
    signList.add("Cancer")
    signList.add("Leo")
    signList.add("Virgo")
    signList.add("Libra")
    signList.add("Scorpio")
    signList.add("Sagittarius")
    signList.add("Capricorn")
    signList.add("Aquarius")
    signList.add("Pisces")
    return signList
  }
}