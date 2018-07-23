package com.hoppers.navigationdrawer.di.modules

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.GsonBuilder
import com.hoppers.navigationdrawer.MyApplication.Companion.appComponent
import com.hoppers.navigationdrawer.network.networkutils.UnsafeOkHttpClient
import com.hoppers.navigationdrawer.network.api.IApi
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

@Module
class NetworkModule {
    @Inject
    lateinit var pref: SharedPreferences
    @Inject
    lateinit var app: Context
    @Inject
    lateinit var cache: Cache

    @Provides
    @Singleton
    fun getRetrofit(): IApi {
        appComponent.inject(this)
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY


        val client = UnsafeOkHttpClient.unsafeOkHttpClient
                .addInterceptor(logging)
                .cache(cache)
                .build()
        val gSon = GsonBuilder()
                .setLenient()
                .create()

        return Retrofit.Builder()
                .baseUrl("http://mocky.io/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gSon))
                .client(client)
                .build()
                .create(IApi::class.java)
    }
}