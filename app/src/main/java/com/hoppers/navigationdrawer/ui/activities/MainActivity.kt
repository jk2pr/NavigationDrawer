package com.hoppers.navigationdrawer.ui.activities

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.hoppers.navigationdrawer.R
import com.hoppers.navigationdrawer.model.Accounts
import com.hoppers.navigationdrawer.model.Json4Kotlin_Base
import com.hoppers.navigationdrawer.ui.adapters.PageAdapter
import com.hoppers.navigationdrawer.ui.fragments.AccountFragment
import com.hoppers.navigationdrawer.ui.fragments.SummaryFragment

import com.hoppers.navigationdrawer.viewmodel.AccountViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, SummaryFragment.OnListFragmentInteractionListener, Observer<Json4Kotlin_Base> {


    private val model: AccountViewModel by lazy { ViewModelProviders.of(this).get(AccountViewModel::class.java) }
    private var subscriptions = CompositeDisposable()
    override fun onChanged(t: Json4Kotlin_Base?) {
        updateUi(t)
    }

    override fun onListFragmentInteraction(item: Accounts?) {
        if (container.currentItem == 0) {
            container.currentItem = 1
            ((container.adapter as PageAdapter).registeredFragments.get(1) as AccountFragment).fetchData(item)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        nav_view.setNavigationItemSelectedListener(this)
        model.liveData.observe(this, this)
        getAccountFromApi()
    }

    private fun getAccountFromApi() {
        showLoader(true)
        val u = model.getAccount().subscribeOn(Schedulers.io())?.observeOn(AndroidSchedulers.mainThread())?.subscribe({
            model.liveData.value = it.body()
        }, { it ->
            print(it)
        }, {

        })
        subscriptions.add(u)
    }


    private fun updateUi(t: Json4Kotlin_Base?) {
        showLoader(false)
        initViewPager(t)
        /*val adapter = container?.adapter as PageAdapter
        adapter.apply {
            notifyDataSetChanged()
        }*/
    }

    private fun showLoader(isVisible: Boolean) {
        if (isVisible) {
            loader.visibility = View.VISIBLE
            main_content.visibility = View.GONE
        } else {
            loader.visibility = View.GONE
            main_content.visibility = View.VISIBLE
        }

    }


    private fun initViewPager(t: Json4Kotlin_Base?) {
        container?.adapter = PageAdapter(supportFragmentManager, t!!)
        tabs?.setupWithViewPager(container)
        //  container?.offscreenPageLimit = tabs.tabCount


    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_camera -> {
                // Handle the camera action
            }
            R.id.nav_gallery -> {

            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_manage -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        subscriptions.clear()
        subscriptions.dispose()
    }
}
