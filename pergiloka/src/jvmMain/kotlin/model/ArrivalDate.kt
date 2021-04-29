package model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ArrivalDate(
    @Json(name = "day")
    val day: String,
    @Json(name = "month")
    val month: String,
    @Json(name = "year")
    val year: String
)