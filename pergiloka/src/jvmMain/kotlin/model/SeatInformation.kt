package model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SeatInformation(
    @Json(name = "layout")
    val layout: String?,
    @Json(name = "pitch")
    val pitch: String?,
    @Json(name = "type")
    val type: String?
)