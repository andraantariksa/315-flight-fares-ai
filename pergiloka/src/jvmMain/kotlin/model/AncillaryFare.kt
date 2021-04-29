package model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AncillaryFare(
    @Json(name = "addOnFares")
    val addOnFares: List<Any>,
    @Json(name = "serviceFares")
    val serviceFares: List<Any>
)