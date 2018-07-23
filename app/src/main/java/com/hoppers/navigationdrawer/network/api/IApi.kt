package com.hoppers.navigationdrawer.network.api

import com.hoppers.navigationdrawer.model.Accounts
import com.hoppers.navigationdrawer.model.Json4Kotlin_Base
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET


interface IApi {


    @GET("v2/5abb1042350000580073a7ea")
    fun getAccount(): Observable<Response<Json4Kotlin_Base>>


}