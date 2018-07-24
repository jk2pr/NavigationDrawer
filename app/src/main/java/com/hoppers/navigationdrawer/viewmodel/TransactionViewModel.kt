package com.hoppers.navigationdrawer.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.hoppers.navigationdrawer.MyApplication
import com.hoppers.navigationdrawer.model.Transactions
import com.hoppers.navigationdrawer.network.api.IApi
import io.reactivex.Observable
import retrofit2.Response
import javax.inject.Inject


class TransactionViewModel : ViewModel() {

    var liveData = MutableLiveData<Transactions>()
    @Inject
    lateinit var api: IApi

    init {
        MyApplication.appComponent.inject(this)
    }


    fun getAllTransactions(url:String): Observable<Response<Transactions>> {
        return api.getAllTransactions(url)

    }

}