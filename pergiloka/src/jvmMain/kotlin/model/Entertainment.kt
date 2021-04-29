package model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Entertainment(
    @Json(name = "available")
    val available: Boolean,
    @Json(name = "copyText")
    val copyText: String,
    @Json(name = "iconUrl")
    val iconUrl: String
)