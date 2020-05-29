package com.th3pl4gu3.locky_offline.repository.network

import com.squareup.moshi.Json

data class WebsiteLogo(
    val name: String,
    val domain: String,
    @Json(name = "logo") val logoUrl: String
)