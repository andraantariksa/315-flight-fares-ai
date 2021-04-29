package model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Airline(
    @Json(name = "airlineId")
    val airlineId: String,
    @Json(name = "iataCode")
    val iataCode: String,
    @Json(name = "iconUrl")
    val iconUrl: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "shortName")
    val shortName: String
)