package model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DepartureDate(
    @Json(name = "day")
    val day: String,
    @Json(name = "month")
    val month: String,
    @Json(name = "year")
    val year: String
)