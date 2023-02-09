package com.example.ginitask.model

import com.google.gson.annotations.SerializedName

data class Gini(
    @SerializedName("numbers")
    val list: List<GiniNumber>
)
data class GiniNumber(
    @SerializedName("number")
    val value: Int
)
