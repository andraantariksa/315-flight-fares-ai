package model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Airport(
    @Json(name = "airportId")
    val airportId: String,
    @Json(name = "localName")
    val localName: String,
    @Json(name = "city")
    val city: String,
    @Json(name = "country")
    val country: String,
)