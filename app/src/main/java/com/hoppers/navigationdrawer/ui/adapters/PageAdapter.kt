package com.hoppers.navigationdrawer.ui.adapters

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.PagerAdapter
import android.util.SparseArray
import android.view.ViewGroup
import com.google.gson.Gson
import com.hoppers.navigationdrawer.constants.INTENT_KEYS
import com.hoppers.navigationdrawer.model.Json4Kotlin_Base
import com.hoppers.navigationdrawer.ui.fragments.AccountFragment
import com.hoppers.navigationdrawer.ui.fragments.PaymentFragment
import com.hoppers.navigationdrawer.ui.fragments.SummaryFragment

class PageAdapter(fm: FragmentManager, private val data: Json4Kotlin_Base) : FragmentStatePagerAdapter(fm) {

    private var registeredFragments = SparseArray<Fragment>()

    override fun getItem(position: Int): Fragment? {
        val bundl = Bundle()
        bundl.putString(INTENT_KEYS.data, Gson().toJson(data))
        return when (position) {
            0 -> {
                val fragment = SummaryFragment()
                fragment.arguments = bundl
                return fragment
            }
            1 -> {
                val fragment = AccountFragment()
                fragment.arguments = bundl
                return fragment
            }
            2 -> {
                val fragment = PaymentFragment()
                fragment.arguments = bundl
                return fragment
            }
            else ->
                null

        }

    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val fragment = super.instantiateItem(container, position) as Fragment
        registeredFragments.put(position, fragment)
        return fragment

    }

    override fun getCount(): Int {
        return 3
    }

    override fun getItemPosition(int: Any): Int {
        return PagerAdapter.POSITION_NONE
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 ->
                "Summary"
            1 ->
                "Accounts"
            2 ->
                "Payments"
            else ->
                null

        }
    }

}