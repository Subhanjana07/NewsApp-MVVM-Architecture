package com.subhanjana.newsapp.data.model

import com.google.gson.annotations.SerializedName

data class SourceResponse (
    @SerializedName("status")
    val status : String = "",
    @SerializedName("sources")
    val sources : List<Source> = ArrayList()
)