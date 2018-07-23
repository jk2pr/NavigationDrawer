package com.hoppers.navigationdrawer.di.components

import com.hoppers.navigationdrawer.di.modules.AppModule
import com.hoppers.navigationdrawer.di.modules.CacheModule
import com.hoppers.navigationdrawer.di.modules.NetworkModule
import com.hoppers.navigationdrawer.ui.activities.MainActivity
import com.hoppers.navigationdrawer.ui.fragments.SummaryFragment
import com.hoppers.navigationdrawer.viewmodel.AccountViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(AppModule::class), (NetworkModule::class), (CacheModule::class)])
interface AppComponent {
    fun inject(auth: MainActivity)
    fun inject(dataFragment: SummaryFragment)
    //  fun inject(dataFragment: UserProfileActivity)
    fun inject(networkModule: NetworkModule)

    fun inject(userViewModel: AccountViewModel)


}