package model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SegmentInventory(
    @Json(name = "fareBasis")
    val fareBasis: Any?,
    @Json(name = "numSeatsLeft")
    val numSeatsLeft: Any?,
    @Json(name = "publishedClass")
    val publishedClass: String,
    @Json(name = "refundable")
    val refundable: String,
    @Json(name = "seatClass")
    val seatClass: Any?,
    @Json(name = "seatsLeftVisibility")
    val seatsLeftVisibility: Boolean,
    @Json(name = "subclassInfo")
    val subclassInfo: Any?
)