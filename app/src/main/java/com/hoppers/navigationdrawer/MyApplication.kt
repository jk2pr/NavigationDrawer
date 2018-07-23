package com.hoppers.navigationdrawer

import android.app.Application
import android.content.Context
import com.hoppers.navigationdrawer.di.components.AppComponent
import com.hoppers.navigationdrawer.di.components.DaggerAppComponent
import com.hoppers.navigationdrawer.di.modules.AppModule
import com.hoppers.navigationdrawer.di.modules.CacheModule
import com.hoppers.navigationdrawer.di.modules.NetworkModule

class MyApplication : Application() {
    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MyApplication.appComponent = DaggerAppComponent.builder()
                .networkModule(NetworkModule())
                .appModule(AppModule(base))
                .cacheModule(CacheModule(base))
                .build()

    }
}