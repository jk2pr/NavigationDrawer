package com.hoppers.navigationdrawer.model

import com.google.gson.annotations.SerializedName



data class Json4Kotlin_Base (

        @SerializedName("accounts") val accounts : List<Accounts>
)

 data class Accounts(
        @SerializedName("accountNumber") val accountNumber: String,
        @SerializedName("accountBsb") val accountBsb: String,
        @SerializedName("accountLabel") val accountLabel: String,
        @SerializedName("currentBalance") val currentBalance: String,
        @SerializedName("availableBalance") val availableBalance: String,
        @SerializedName("transactions") val transactions: String
)