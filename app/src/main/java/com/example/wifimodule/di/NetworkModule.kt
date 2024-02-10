package com.example.wifimodule.di

import com.example.wifimodule.BuildConfig
import com.example.wifimodule.base.common.RequestInterceptor
import com.example.wifimodule.base.common.SharedPrefs
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.migration.DisableInstallInCheck
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Url
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton

/**
 * Network module for handling network requests and providing necessary dependencies.
 * @constructor Create an instance of the Network module.
 */
@Module
@DisableInstallInCheck
object NetworkModule {
    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class RetrofitOne

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class RetrofitTwo
    /**
     * Provides a Retrofit instance for making network requests.
     * @param okHttp The OkHttpClient instance used for network communication.
     * @return Retrofit instance.
     */
    @Singleton
    @Provides
    @RetrofitOne
    fun provideBackendRetrofit(okHttp: OkHttpClient): Retrofit {
        val gson = GsonBuilder().create()
        return Retrofit.Builder().apply {
            addConverterFactory(GsonConverterFactory.create(gson))
            client(okHttp)
            baseUrl("${BuildConfig.API_BASE_URL}${BuildConfig.API_VERSION}")
        }.build()
    }


    @Singleton
    @Provides
    @RetrofitTwo
    fun provideMLRetrofit(@Url url:String, okHttp: OkHttpClient): Retrofit {
        val gson = GsonBuilder().create()
        return Retrofit.Builder().apply {
            addConverterFactory(GsonConverterFactory.create(gson))
            client(okHttp)
            baseUrl("${BuildConfig.API_ML_BASE_URL}${BuildConfig.API_VERSION}")
        }.build()
    }

    /**
     * Provides an OkHttpClient instance for network communication.
     * @param requestInterceptor The RequestInterceptor instance for intercepting and modifying network requests.
     * @return OkHttpClient instance.
     */
    @Singleton
    @Provides
    fun provideOkHttp(requestInterceptor: RequestInterceptor): OkHttpClient {
        return OkHttpClient.Builder().apply {
            addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            connectTimeout(60, TimeUnit.SECONDS)
            readTimeout(60, TimeUnit.SECONDS)
            writeTimeout(60, TimeUnit.SECONDS)
            addInterceptor(requestInterceptor)
        }.build()
    }

    /**
     * Provides a RequestInterceptor instance for intercepting and modifying network requests.
     * @param prefs The SharedPrefs instance for accessing shared preferences.
     * @return RequestInterceptor instance.
     */
    @Provides
    fun provideRequestInterceptor(prefs: SharedPrefs): RequestInterceptor {
        return RequestInterceptor(prefs)
    }
}