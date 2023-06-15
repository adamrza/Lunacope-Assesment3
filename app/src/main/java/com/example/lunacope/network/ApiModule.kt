package com.example.lunacope.network

import com.example.lunacope.HoroscopeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {
  @Singleton
  @Provides
  fun provideLunacopeApi(@Named("retrofit") retrofit: Retrofit): ILunacopeApi {
    return retrofit.create(ILunacopeApi::class.java)
  }

  @Singleton
  @Provides
  fun provideHoroscopeRepository(apiService: ILunacopeApi): HoroscopeRepository {
    return HoroscopeRepository(apiService)
  }
}