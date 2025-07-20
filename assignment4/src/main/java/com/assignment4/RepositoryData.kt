package com.assignment4

import com.squareup.moshi.Json

data class RepositoryData(
    val id: String,
    val author: String,
    val url: String,
    @Json(name = "download_url")
    val downloadUrl: String
)
