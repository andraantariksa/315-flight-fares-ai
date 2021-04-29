package model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DepartureTime(
    @Json(name = "hour")
    val hour: String,
    @Json(name = "minute")
    val minute: String
)