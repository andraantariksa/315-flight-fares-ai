package model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Baggage(
    @Json(name = "availableToBuy")
    val availableToBuy: Boolean,
    @Json(name = "quantity")
    val quantity: String,
    @Json(name = "unitOfMeasure")
    val unitOfMeasure: String,
    @Json(name = "weight")
    val weight: String
)