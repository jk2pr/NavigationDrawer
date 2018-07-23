package com.hoppers.navigationdrawer.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.hoppers.navigationdrawer.MyApplication.Companion.appComponent
import com.hoppers.navigationdrawer.model.Accounts
import com.hoppers.navigationdrawer.model.Json4Kotlin_Base
import com.hoppers.navigationdrawer.network.api.IApi
import io.reactivex.Observable
import retrofit2.Response
import javax.inject.Inject


class AccountViewModel : ViewModel() {

    var liveData = MutableLiveData<Json4Kotlin_Base>()
    @Inject
    lateinit var api: IApi

    init {
        appComponent.inject(this)
    }


    fun getAccount(): Observable<Response<Json4Kotlin_Base>> {
        return api.getAccount()

    }

}