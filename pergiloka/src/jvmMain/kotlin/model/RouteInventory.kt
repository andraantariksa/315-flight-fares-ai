package model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RouteInventory(
    @Json(name = "numSeatLeft")
    val numSeatLeft: String,
    @Json(name = "seatClass")
    val seatClass: Any?,
    @Json(name = "seatPublishedClass")
    val seatPublishedClass: String,
    @Json(name = "seatsLeftVisibility")
    val seatsLeftVisibility: Boolean
)