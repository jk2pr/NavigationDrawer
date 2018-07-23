package com.hoppers.navigationdrawer.di.modules

import android.content.Context
import android.content.SharedPreferences
import com.hoppers.navigationdrawer.R
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(val app: Context) {

    @Provides
    @Singleton
    fun provideContext(): Context {
        return app
    }

    @Provides
    @Singleton
    fun providePreference(): SharedPreferences {
        return app.getSharedPreferences(app.getString(R.string.app_name), Context.MODE_PRIVATE)
    }

}