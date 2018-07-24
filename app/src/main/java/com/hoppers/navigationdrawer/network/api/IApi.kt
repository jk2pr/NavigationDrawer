package com.hoppers.navigationdrawer.network.api

import com.hoppers.navigationdrawer.model.Json4Kotlin_Base
import com.hoppers.navigationdrawer.model.Transactions
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url


interface IApi {


    @GET("v2/5abb1042350000580073a7ea")
    fun getAccount(): Observable<Response<Json4Kotlin_Base>>

    @GET()
    fun getAllTransactions(@Url url: String): Observable<Response<Transactions>>


}