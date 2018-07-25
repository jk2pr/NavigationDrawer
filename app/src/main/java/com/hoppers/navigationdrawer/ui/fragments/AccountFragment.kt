package com.hoppers.navigationdrawer.ui.fragments


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import com.hoppers.navigationdrawer.R
import com.hoppers.navigationdrawer.constants.INTENT_KEYS
import com.hoppers.navigationdrawer.model.Accounts
import com.hoppers.navigationdrawer.model.Json4Kotlin_Base
import com.hoppers.navigationdrawer.model.Transactions
import com.hoppers.navigationdrawer.ui.adapters.TransactionRecyclerViewAdapter
import com.hoppers.navigationdrawer.viewmodel.TransactionViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.expanded_item.*


class AccountFragment : Fragment(), Observer<Transactions> {
    override fun onChanged(t: Transactions?) {
        updateUi(t)
    }

    private val model: TransactionViewModel by lazy { ViewModelProviders.of(this).get(TransactionViewModel::class.java) }
    private val data: Json4Kotlin_Base by lazy {
        Gson().fromJson(arguments?.getString(INTENT_KEYS.data), Json4Kotlin_Base::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return layoutInflater.inflate(R.layout.expanded_item, container, false)

    }

    private fun updateUi(t: Transactions?) {
        showLoader(false)
        val tra = t?.transactions?.groupBy {
            it.date
        }

        recycler_expanded.apply {
            layoutManager =
                    LinearLayoutManager(context)
            isNestedScrollingEnabled = false
            adapter = TransactionRecyclerViewAdapter(tra, null)
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        model.liveData.observe(this, this)

    }

    fun fetchData(index: Accounts?) {
        getAllTransactions(data.accounts.indexOf(index))
    }

    private fun showLoader(isVisible: Boolean) {
        no_ac_selected.visibility = View.GONE
        if (isVisible) {
            loader.visibility = View.VISIBLE
            main_content.visibility = View.GONE
        } else {
            loader.visibility = View.GONE
            main_content.visibility = View.VISIBLE
        }

    }

    private fun getAllTransactions(index: Int) {
        showLoader(true)
        val u = model.getAllTransactions(data.accounts[index].transactions).subscribeOn(Schedulers.io())?.observeOn(AndroidSchedulers.mainThread())?.subscribe({
            val item = data.accounts[index]
            accountLabel.text = item.accountLabel
            currentBalance.text = item.currentBalance
            availableBalance.text = String.format(getString(R.string.available, item.availableBalance))
            accountNumber.text = String.format(getString(R.string.current, item.accountNumber))
            model.liveData.value = it.body()
        }, { it ->
            print(it)
        }, {

        })
        //   subscriptions.add(u)
    }
}



