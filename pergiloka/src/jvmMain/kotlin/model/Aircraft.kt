package model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Aircraft(
    @Json(name = "model")
    val model: String?,
    @Json(name = "seatInformation")
    val seatInformation: SeatInformation,
    @Json(name = "type")
    val type: Any?
)