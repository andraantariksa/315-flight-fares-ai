package model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Wifi(
    @Json(name = "available")
    val available: Boolean,
    @Json(name = "chance")
    val chance: String,
    @Json(name = "copyText")
    val copyText: String,
    @Json(name = "cost")
    val cost: String,
    @Json(name = "iconUrl")
    val iconUrl: String
)